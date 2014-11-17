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
import jac.engine.ruleset.Ideology;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Gregory Jordan
 */
public class UnitPart {
    
    
    private final List<Effect> effectsList;
    private final List<Restriction> restrictions; 
    private final List<String> pre_requisite_technology;
    
    
    public UnitPart(List<Effect> effectsList, List<Restriction> restrictions, List<String> pre_requisite_technology){
        this.effectsList = effectsList;
        this.restrictions = restrictions;
        this.pre_requisite_technology = pre_requisite_technology;
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
    
    /**
     * Is this part usable for the current configuration?  Is there a reason you can't use this part?
     * @param lifespan - How long this unit has been alive.
     * @param unit the unit this is a part of.
     * @return 
     */
    public boolean available(int lifespan, GenericUnit unit, PlayerDetails player) {
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
    
}
