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

import jac.Enum.CombatMode;
import jac.Enum.MovementType;
import jac.engine.ruleset.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Gregory Jordan
 */
public class UnitAbility extends UnitPart {

    private final Logger log = LoggerFactory.getLogger(this.getClass().getName());

    //Required 
    private final String key;

    //Optional
    // Doing this differently.  // TODO: Figure out a better way to store the cost, make it more flexible (or clear) as to what effect we want.
    private final int cost_code;

    
    private final List<String> pre_requisite_technology;

    private final int max_speed_allowed;  // there was a not allowed for fast-moving units flag.  Need to know what speed was considered fast.  -1 means no limit.

    public UnitAbility(Builder build) {
        super(build.effects, build.restrictions);
        
        Translation tran = build.tran;
        key = build.key;
        cost_code = build.cost_code;
        pre_requisite_technology = build.pre_reqs;

        max_speed_allowed = build.max_speed_allowed;
     
    }

    
    public int calc_cost(int base_cost, Weapon wep, Chassis chas, Armor arm) {

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
        int value = 0;
        if (cost_code > 0) {
            value = cost_code;
        }
        switch (cost_code) {
            case 0:
                return 0;
            case -1:
                value = wep.getCost() / arm.getCost();
                break;
            case -2:
                value = wep.getCost() - 1;
                break;
            case -3:
                value = arm.getCost() - 1;
                break;
            case -4:
                value = chas.getCost() - 1;
                break;
            case -5:
                value = wep.getCost() + arm.getCost() - 2;
                break;
            case -6:
                value = wep.getCost() + chas.getCost() - 2;
                break;
            case -7:
                value = arm.getCost() + chas.getCost() - 2;
                break;
            default:
                log.error("Reached end of switch statement.  Invalid number supplied.");
                // This could should never be reached.  Its mostly here in cose someone accidently removes a check someplace.
                throw new IllegalArgumentException("Reached end of switch statement.  Invalid number supplied.");

        }
        return base_cost * value / 4;
    }
    
    
    
    
    public static class Builder {

        // Required
        private final String key;
        private final String[] namedescrip;
        private final Translation tran;

        // Should be used
        private List<Restriction> restrictions = new ArrayList<>();
        private List<Effect> effects = new ArrayList<>();

        // Optional
        private Set<MovementType> allowed_movements = new HashSet<>();
        private Set<CombatMode> allowed_combatModes = new HashSet<>();

        private int cost_code = 0;
        private List<String> pre_reqs = new ArrayList<>();

        int max_speed_allowed = -1;  // there was a not allowed for fast-moving units flag.  Need to know what speed was considered fast.  -1 means no limit.

        boolean cost_increased_land = false;

        
        
        
        
        
        
        
        public Builder(String key, Translation tran, String name, String code, String description) {
            this.key = key;
            this.tran = tran;
            namedescrip = new String[]{name.trim(), code.trim(), description.trim()};
        }

        public Builder setCost_code(int cost_code) {
            if (cost_code < -7) {
                throw new IllegalArgumentException("Reached end of switch statement.  Invalid number supplied.");
            }
            this.cost_code = cost_code;
            return this;
        }

        public Builder addPreReq(String pre_req) {
            if (!pre_req.trim().equalsIgnoreCase("None")) {
                pre_reqs.add(pre_req.trim());
            }
            return this;
        }

        public Builder allowedMovementTypes(Set<MovementType> movements) {
            this.allowed_movements = movements;
            return this;
        }

        public Builder allowedCombatTypes(Set<CombatMode> allowed_combatModes) {
            this.allowed_combatModes = allowed_combatModes;
            return this;
        }

        public Builder setMax_speed_allowed(int max_speed_allowed) {
            this.max_speed_allowed = max_speed_allowed;
            return this;
        }

        public Builder setCost_increased_land(boolean cost_increased_land) {
            this.cost_increased_land = cost_increased_land;
            return this;
        }

        public Builder smacAbilityFlags(String smacFlags) {
            smacFlags = smacFlags.trim();
            if (smacFlags.charAt(0) == '1') {
                cost_increased_land = true;
            }
            if (smacFlags.charAt(1) == '1') {
                max_speed_allowed = 1;
            }

            /*
             ;          00000000001 = Allowed for Land units
             ;          00000000010 = Allowed for Sea units
             ;          00000000100 = Allowed for Air units
             ;          00000001000 = Allowed for Combat units
             ;          00000010000 = Allowed for Terraformer units
             ;          00000100000 = Allowed for Noncombat units (non-terraformer)
             ;          00001000000 = Not allowed for probe teams
             ;          00010000000 = Not allowed for psi units
             ;          00100000000 = Transport units only
             ;          01000000000 = Not allowed for fast-moving units
             ;          10000000000 = Cost increased for land units
             */
            if (smacFlags.charAt(2) == '1') {
                allowed_combatModes.clear();
                allowed_combatModes.add(CombatMode.TRANSPORT);
            }
            if (smacFlags.charAt(3) == '0') {
                // PSI Unit Flag.  If 1 then not allowed. So if 0, then it is allowed.
                allowed_combatModes.add(CombatMode.PSI);
            }
               
            if (smacFlags.charAt(5) == '1') {
                allowed_combatModes.add(CombatMode.PROBE);
                allowed_combatModes.add(CombatMode.CONVOY);
                allowed_combatModes.add(CombatMode.TRANSPORT); 
            }
            
            if (smacFlags.charAt(6) == '1') {
                allowed_combatModes.add(CombatMode.TERRAFORMER);
            }
            if (smacFlags.charAt(7) == '1') {
                allowed_combatModes.add(CombatMode.ENERGY);
                allowed_combatModes.add(CombatMode.MISSILE);
                allowed_combatModes.add(CombatMode.PROJECTILE);
            }

            if (smacFlags.charAt(8) == '1') {
                allowed_movements.add(MovementType.AIR);
            }

            if (smacFlags.charAt(9) == '1') {
                allowed_movements.add(MovementType.SEA);

            }
            if (smacFlags.charAt(10) == '1') {
                allowed_movements.add(MovementType.LAND);
            }
            
            if (smacFlags.charAt(4) == '1') {              
                allowed_combatModes.remove(CombatMode.PROBE);
            }

            return this;
        }

        public UnitAbility build() {
            List<Restriction> restrictList = new ArrayList<>();

            if (allowed_movements.size() == MovementType.COUNT) {
                allowed_movements = new HashSet<>();  // Optimization.  The code for checking assumes all are true if the list is empty.
            }

            return new UnitAbility(this);
        }

    }

    

    // http://strategywiki.org/wiki/Sid_Meier%27s_Alpha_Centauri/Special_Ability
}
