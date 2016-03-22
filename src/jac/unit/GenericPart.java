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

import jac.Enum.Domain;
import jac.Enum.WeaponRole;
import jac.engine.HasKey;
import jac.engine.PlayerDetails;
import jac.engine.ruleset.Ruleset;
import jac.unit.effectRules.EffectNode;
import jac.unit.effectRules.HasChassis;
import jac.unit.effectRules.HasRole;
import jac.unit.effectRules.OperatorAND;
import jac.unit.effectRules.OperatorOr;
import jac.unit.effectRules.RequiredDomain;
import jac.unit.effectRules.Value;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Gregory Jordan
 */
public class GenericPart implements UnitPart, HasKey {
    
    
    private final Effect localEffects;
    private final Effect empireEffects;
    
    //private final List<Restriction> restrictions; 
    private final EffectNode<Boolean> restrict_for_display;  // What has to be true, for the player to be able to see/build this part.
    private final List<String> pre_requisite_technology;  // This also has to be true.
    private final Set<String> allowed_races;
    private final String key;
    private final int flatcost;  //TODO:  Replace this with an EffectNode after testing to make sure if then else effect values act as expected.
    
    
    public GenericPart(Builder build){
        this.localEffects = build.localEffects;
        this.empireEffects = build.empireEffects;
        this.allowed_races = build.allowed_races;
        this.restrict_for_display = build.restrict_for_display;
        
        this.pre_requisite_technology = build.pre_requisite_technology;
        this.key = build.key;
        this.flatcost = build.flatcost;
    }

    @Override
    public int getFlatcost() {
        return flatcost;
    }
    

    @Override
    public String getKey(){
        return key;
    }

    @Override
    public List<String> getPre_requisite_technology() {
        return pre_requisite_technology;
    }
    
    public static List<String> createlist(String onePreRequisite){
        List<String> pre_reqs = new ArrayList<>();
        if (!onePreRequisite.trim().equalsIgnoreCase("None")) {
            pre_reqs.add(onePreRequisite.trim());
        }
        return pre_reqs;
    }
    
    
    @Override
    public boolean visible(GenericUnit unit, PlayerDetails player){
        // Is the pre-requisite tech needs met?
        for(String tech : pre_requisite_technology){
            if(! player.getKnownTechnologies().contains(tech)){
                return false;
            }
        }
        
       if(!allowed_races.isEmpty()){
           if(!allowed_races.contains(player.getFaction().getRace())){
               return false;
           }
       }
       
       return true;  
    }
    
            
    /**
     * Is this part usable for the current configuration?  Is there a reason you can't use this part?
     * @param unit the unit this is a part of.
     * @param player
     * @return 
     */
    @Override
    public boolean available(GenericUnit unit, PlayerDetails player, Ruleset rules) {     
            return restrict_for_display.result(unit, rules);
    }

    @Override
    public Effect getLocalEffects() {
        return localEffects;
    }

    @Override
    public Effect getEmpireEffects() {
        return empireEffects;
    }

    
    public static class Builder {

      
        private final String key;
        private final int flatcost;

        private EffectNode<Boolean> restrict_for_display = Value.True();

        private Effect localEffects = new Effect.Builder().build();
        private Effect empireEffects = null;

        private List<String> pre_requisite_technology = new ArrayList<String>();  // This also has to be true.
        private Set<String> allowed_races = new LinkedHashSet<>();
       

        public Builder(String key, int flatcost) {
            
            this.key = key;
            this.flatcost = flatcost;
            this.restrict_for_display = restrict_for_display;

        }

        public Builder setRestrictionTest(EffectNode test) {
            restrict_for_display = test;
            return  this;
        }

        public Builder restrictToRace(String race) {
            allowed_races.add(race);
            return this;
        }

        public Builder addPreRequisiteTech(String techkey) {
            if (!techkey.trim().equalsIgnoreCase("None")) {
                pre_requisite_technology.add(techkey.trim());
            }
            return this;
        }

        public Builder setLocalEffects(Effect localEffects) {
            this.localEffects = localEffects;
            return this;
        }

        public Builder setEmpireEffects(Effect empireEffects) {
            this.empireEffects = empireEffects;
            return this;
        }

        
        public Builder smacAbilityFlags(String smacFlags) {
            
            
            List<EffectNode<Boolean>> mainAnds = new ArrayList<>();
            
            List<EffectNode<Boolean>> reqDomains = new LinkedList<>();
            List<EffectNode<Boolean>> reqRoles = new LinkedList<>();
           
            
            smacFlags = smacFlags.trim();
            if (smacFlags.charAt(0) == '1') {
                //#TODO: increaseLandCost = true;  
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
                // 5          00000100000 = Allowed for Noncombat units (non-terraformer)
                if (smacFlags.charAt(4) == '0') { 
                    reqRoles.add(new HasRole(WeaponRole.PROBE));

                }
                reqRoles.add(new HasRole(WeaponRole.CONVOY));
                reqRoles.add(new HasRole(WeaponRole.TRANSPORT));

            }
            
            if (smacFlags.charAt(6) == '1') {
                //6          00000010000 = Allowed for Terraformer units
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
            
            
            mainAnds.add(new OperatorOr(reqDomains));
            mainAnds.add(new OperatorOr(reqRoles));
            
             
                
            return this.setRestrictionTest(new OperatorAND(mainAnds));
        }
       
        public GenericPart build(){
            return new GenericPart(this);
        }

        

    }

}
