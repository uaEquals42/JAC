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
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Gregory Jordan
 */
abstract public class UnitPart {
    
    
    private final List<Effect> effectsList;
    private final List<Restriction> restrictions; // What has to be true, for the player to be able to see/build this part.
    private final List<String> pre_requisite_technology;  // This also has to be true.
    private final Set<String> allowed_races;
    private final String key;
    private final int flatcost;
    
    
    public UnitPart(Builder build){
        this.effectsList = build.effectsList;
        this.allowed_races = build.allowed_races;
        this.restrictions = build.restrictions;
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
     * @param lifespan - How long this unit has been alive.
     * @param unit the unit this is a part of.
     * @param player
     * @return 
     */
    public boolean available(int lifespan, GenericUnit unit, PlayerDetails player) {
        

        
        // Are there any other restrictions in place?
        if (restrictions.isEmpty()) {
            return true;
        }
        boolean tf = true;
        for (Restriction restrict : restrictions) {
            tf = tf && restrict.available(lifespan, unit, player);
        }
        return tf;
    }
    
    
    public List<Effect> active_effects(int lifespan, GenericUnit unit, PlayerDetails player){
        List<Effect> result = new ArrayList<>();

        if (this.available(lifespan, unit, player)) {
            for (Effect effect : effectsList) {
                if (effect.available(lifespan, unit, player)) {
                    result.add(effect);
                }
            }
        }
        return result;
    }
    
    
       abstract public static class Builder <T extends Builder>{
           private final Translation tran;
           private final String key;
           private final int flatcost;

           private List<Effect> effectsList = new ArrayList<>();
           List<Restriction> restrictions = new ArrayList<>(); // What has to be true, for the player to be able to see/build this part.
           private List<String> pre_requisite_technology = new ArrayList<>();  // This also has to be true.
           private Set<String> allowed_races = new LinkedHashSet<>();
        

           public Builder(Translation tran, String key, int flatcost) {
               this.tran = tran;
               this.key = key;
               this.flatcost = flatcost;
           }

           public T restrictToRace(String race) {
               allowed_races.add(race);
               return (T) this;
           }

           public T addPreRequisiteTech(String techkey) {
               if(!techkey.trim().equalsIgnoreCase("None")){
                   pre_requisite_technology.add(techkey.trim());
               }
               return (T) this;
           }

           public T addEffect(Effect effect) {
               effectsList.add(effect);
               return (T) this;
           }
        
        public Translation getTran() {
            return tran;
        }

        public String getKey() {
            return key;
        }
           
        
       }
    
}
