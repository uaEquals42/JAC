/*
 * JAC Copyright (C) 2014 Gregory Jordan
 *
 * This file is part of JAC.   
 * 
 * JAC is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package jac.unit;

import jac.Enum.WeaponRole;
import jac.Enum.Domain;
import jac.engine.ruleset.*;
import jac.unit.tests.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Gregory Jordan
 */
public class UnitAbility extends UnitPart {

    private final Logger log = LoggerFactory.getLogger(this.getClass().getName());


    //Optional
    // IF 0 then use flatcost when calculating cost!.
    private final int cost_code;

    
    

  

    public UnitAbility(Builder build) {
        super(build);

        cost_code = build.cost_code;
            
    }

    
    private int convert_cost_code(Weapon weapon, Armor armor, Chassis chassis){
        int value = 0;
        if (cost_code > 0) {
            value = cost_code;
        }
        int weaponcost = weapon.getFlatcost();
        int armor_cost = armor.getFlatcost();
        int chassis_cost = chassis.getFlatcost();
        switch (cost_code) {
            case 0:
                return super.getFlatcost();
            case -1:
                value = weaponcost / armor_cost;
                break;
            case -2:
                value = weaponcost - 1;
                break;
            case -3:
                value = armor_cost - 1;
                break;
            case -4:
                value = chassis_cost - 1;
                break;
            case -5:
                value = weaponcost + armor_cost - 2;
                break;
            case -6:
                value = weaponcost + chassis_cost - 2;
                break;
            case -7:
                value = armor_cost + chassis_cost - 2;
                break;
            default:
                log.error("Reached end of switch statement.  Invalid number supplied.");
                // This could should never be reached.  Its mostly here in cose someone accidently removes a check someplace.
                throw new IllegalArgumentException("Reached end of switch statement.  Invalid number supplied.");
                
        }
        return value;
    }
    
    public int calculate_cost(int base_cost, Weapon weapon, Armor armor, Chassis chassis) {

        /*
         ; Special Unit Abilities

         ; Cost   = Cost factor of ability
         ;          1+ = Straight Cost; 25% increase per unit of cost
         ;           0 = None
         ;          -1 = Increases w/ ratio of weapon to armor: 0, 1, or 2.
         ;               Rounded DOWN. Never higher than 2.
         ;               Examples: For a W1,A2 unit, cost is 0
         ;                         For a W3,A2 unit, cost is 1 (3/2 rounded down)
         ;                         For a W6,A3 unit, cost is 2
         ;          -2 = Increases w/ weapon value
         ;          -3 = Increases w/ armor value
         ;          -4 = Increases w/ speed value
         ;          -5 = Increases w/ weapon+armor value
         ;          -6 = Increases w/ weapon+speed value
         ;          -7 = Increases w/ armor+speed value
         */
        // http://alphacentauri2.info/index.php?topic=12897.msg61597#msg61597
        /*
         Acording to Yitzi
         Ok (weapon, armor, and chassis all refer to the weapon/armor/chassis cost):
         -1 costs 0 if weapon<armor, 1 if 2*armor>weapon>=armor, and 2 if weapon>=2*armor.
         -2 has cost equal to weapon-1.
         -3 has cost equal to armor-1.
         -4 has cost equal to chassis-1.
         -5 has cost equal to weapon+armor-2.
         -6 has cost equal to weapon+chassis-2.
         -7 has cost equal to armor+chassis-2.
        
         It's used as if that were the positive ability cost, i.e. positive total_unit_cost = (unit_cost *(1+ability_cost / 4))
         */
        
        int value = convert_cost_code(weapon, armor, chassis);
        
        return base_cost * value / 4;
    }
    
   
    
    
    public static class Builder extends UnitPart.Builder<Builder>{

        // Required
        private final String[] namedescrip;

        // Optional

       

        private int cost_code = 0;

        
        boolean cost_increased_land = false;

        
        
        
        
        
        
        
        public Builder(Translation tran, String key, String name, String code, String description) {
           super(tran, key, 0);
           namedescrip = new String[]{name.trim(), code.trim(), description.trim()};
        }
        
        public Builder(Translation tran, String key, int flatcost, String name, String code, String description){
            super(tran, key, flatcost);
            namedescrip = new String[]{name.trim(), code.trim(), description.trim()};
        }

        public Builder setCost_code(int cost_code) {
            if (cost_code < -7) {
                throw new IllegalArgumentException("Reached end of switch statement.  Invalid number supplied.");
            }
            this.cost_code = cost_code;
            return this;
        }
    
        public Builder setCost_increased_land(boolean cost_increased_land) {
            this.cost_increased_land = cost_increased_land;
            return this;
        }
        
        

        public Builder smacAbilityFlags(String smacFlags) {
            
            
            List<RestrictionTest> mainAnds = new ArrayList<>();
            
            List<RestrictionTest> reqDomains = new LinkedList<>();
            List<RestrictionTest> reqRoles = new LinkedList<>();
            
            
            smacFlags = smacFlags.trim();
            if (smacFlags.charAt(0) == '1') {
                cost_increased_land = true;  
            }
            if (smacFlags.charAt(1) == '1') {
                mainAnds.add(new HasChassis("0"));
                
            }

            /*
             10          00000000001 = Allowed for Land units
              9          00000000010 = Allowed for Sea units
              8          00000000100 = Allowed for Air units
              7          00000001000 = Allowed for Combat units
              6          00000010000 = Allowed for Terraformer units
              5          00000100000 = Allowed for Noncombat units (non-terraformer)
              4          00001000000 = Not allowed for probe teams
              3          00010000000 = Not allowed for psi units
              2          00100000000 = Transport units only
              1          01000000000 = Not allowed for fast-moving units
              0          10000000000 = Cost increased for land units
             */

            if (smacFlags.charAt(3) == '0') {
                // PSI Unit Flag.  If 1 then not allowed. So if 0, then it is allowed.
                reqRoles.add(new HasRole(WeaponRole.PSI));

            }
               
            if (smacFlags.charAt(5) == '1') {
                if (smacFlags.charAt(4) == '0') { 
                    reqRoles.add(new HasRole(WeaponRole.PROBE));

                }
                reqRoles.add(new HasRole(WeaponRole.CONVOY));
                reqRoles.add(new HasRole(WeaponRole.TRANSPORT));

            }
            
            if (smacFlags.charAt(6) == '1') {
                reqRoles.add(new HasRole(WeaponRole.TERRAFORMER));

            }
            if (smacFlags.charAt(7) == '1') {
                reqRoles.add(new HasRole(WeaponRole.ENERGY));
                reqRoles.add(new HasRole(WeaponRole.MISSILE));
                reqRoles.add(new HasRole(WeaponRole.PROJECTILE));

            }
            
            
            if (smacFlags.charAt(2) == '1') {
                reqRoles.clear();
                reqRoles.add(new HasRole(WeaponRole.TRANSPORT));

            }

            
            if (smacFlags.charAt(8) == '1') {
                reqDomains.add(new RequiredDomain(Domain.AIR));
                
            }

            if (smacFlags.charAt(9) == '1') {
                reqDomains.add(new RequiredDomain(Domain.SEA));
                

            }
            if (smacFlags.charAt(10) == '1') {
                reqDomains.add(new RequiredDomain(Domain.LAND));
                
            }
            

            
            
            // Now use this data to create the restrictions.
            if (reqDomains.size() == Domain.COUNT) {
                reqDomains.clear();  // Optimization.  The code for checking assumes all are true if the list is empty.
            }
            if (reqRoles.size() == WeaponRole.COUNT){
                reqRoles.clear();  // optimization.  If all are allowed, then we don't need to have them tested.
            }
            
            
            mainAnds.add(new testOR(reqDomains));
            mainAnds.add(new testOR(reqRoles));
            
            this.setRestrictionTest(new testAND(mainAnds));
                
            return this;
        }

        public UnitAbility build() {
            return new UnitAbility(this);
        }

    }

    

    // http://strategywiki.org/wiki/Sid_Meier%27s_Alpha_Centauri/Special_Ability
}
