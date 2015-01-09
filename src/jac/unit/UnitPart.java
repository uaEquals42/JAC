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

import jac.engine.PlayerDetails;
import jac.engine.ruleset.Translation;
import jac.unit.effectRules.EffectNode;
import jac.unit.effectRules.Value;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Gregory Jordan
 */
abstract public class UnitPart {
    
    
    private final Effect localEffects;
    private final Effect empireEffects;
    
    //private final List<Restriction> restrictions; 
    private final EffectNode<Boolean> restrict_for_display;  // What has to be true, for the player to be able to see/build this part.
    private final List<String> pre_requisite_technology;  // This also has to be true.
    private final Set<String> allowed_races;
    private final String key;
    private final int flatcost;  //TODO:  Replace this with an EffectNode after testing to make sure if then else effect values act as expected.
    
    
    public UnitPart(Builder build){
        this.localEffects = build.localEffects;
        this.empireEffects = build.empireEffects;
        this.allowed_races = build.allowed_races;
        this.restrict_for_display = build.restrict_for_display;
        
        this.pre_requisite_technology = build.pre_requisite_technology;
        this.key = build.key;
        this.flatcost = build.flatcost;
    }

    public int getFlatcost() {
        return flatcost;
    }
    
    
    
    public String getKey(){
        return key;
    }

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
    public boolean available(GenericUnit unit, PlayerDetails player) {
        
            return restrict_for_display.result(unit, player);
        

    }

    public Effect getLocalEffects() {
        return localEffects;
    }

    public Effect getEmpireEffects() {
        return empireEffects;
    }

    
    abstract public static class Builder<T extends Builder> {

        private final Translation tran;
        private final String key;
        private final int flatcost;

        private EffectNode<Boolean> restrict_for_display = Value.True();

        private Effect localEffects = new Effect.Builder().build();
        private Effect empireEffects = null;

        private List<String> pre_requisite_technology = new ArrayList<String>();  // This also has to be true.
        private Set<String> allowed_races = new LinkedHashSet<>();
       

        public Builder(Translation tran, String key, int flatcost) {
            this.tran = tran;
            this.key = key;
            this.flatcost = flatcost;
            this.restrict_for_display = restrict_for_display;

        }

        public T setRestrictionTest(EffectNode test) {
            restrict_for_display = test;
            return (T) this;
        }

        public T restrictToRace(String race) {
            allowed_races.add(race);
            return (T) this;
        }

        public T addPreRequisiteTech(String techkey) {
            if (!techkey.trim().equalsIgnoreCase("None")) {
                pre_requisite_technology.add(techkey.trim());
            }
            return (T) this;
        }

        public void setLocalEffects(Effect localEffects) {
            this.localEffects = localEffects;
        }

        public void setEmpireEffects(Effect empireEffects) {
            this.empireEffects = empireEffects;
        }

       

        public Translation getTran() {
            return tran;
        }

        public String getKey() {
            return key;
        }

    }

}
