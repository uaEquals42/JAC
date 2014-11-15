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

import jac.engine.ruleset.Ideology;
import jac.engine.ruleset.Weapon;
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
    
    
    public UnitPart(List<Effect> effectsList, List<Restriction> restrictions){
        this.effectsList = effectsList;
        this.restrictions = restrictions;
    }
    
    
    /**
     * Is this part usable for the current configuration?  Is there a reason you can't use this part?
     * @param lifespan - How long this unit has been alive.
     * @param chassis
     * @param wep
     * @param ideologys
     * @param base_size
     * @param fac
     * @return 
     */
    public boolean available(int lifespan, Chassis chassis, Weapon wep, Map<String, Ideology> ideologys, int base_size, Map<String, Facility> fac) {
        if (restrictions.isEmpty()) {
            return true;
        }
        boolean tf = true;
        for (Restriction restrict : restrictions) {
            tf = tf && restrict.available(lifespan, chassis, wep, ideologys, base_size, fac);
        }
        return tf;
    }
    
    
    public List<Effect> active_effects(int turn, GenericUnit unit, Map<String, Ideology> current_ideologies){
        List<Effect> result = new ArrayList<>();
        for(Effect effect : effectsList){
            if(effect.available(turn, unit, current_ideologies)){
                result.add(effect);
            }
        }
        return result;
    }
    
}
