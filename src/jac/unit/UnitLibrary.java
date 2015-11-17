/*
 * JAC Copyright (C) 2015 Gregory Jordan
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

import jac.Enum.BoolNames;
import jac.Enum.IntNames;
import jac.engine.ruleset.Ruleset;

/**
 * Stores code shared between Unit_Plan and GenericUnit.  
 * TODO: This probably should be changed from static to instances inside GenericUnit and PlayerData when it comes time to optimize stuff.
 * @author Gregory Jordan
 */
public class UnitLibrary {
    
    static int calculateInteger(IntNames name, Unit unit, Ruleset rules) {
        // TODO: Also have it calculate the effects of the square it is on... (if any).
        int value = 0;
        float multiplier = 1;
        for (Effect eff : unit.getLocalEffects(rules)) {
            value = value + eff.getIntValue(name, unit, rules);
            multiplier = multiplier * eff.getFloatValue(name, unit, rules);
        }

        // TODO:  Do the same for the faction wide effects (Tech, Rules, Faction Settings, Faction Rules, Secret Projects)
        return (int) (value * multiplier);
    }
    
    static boolean calculateBool(BoolNames name, Unit unit, Ruleset rules) {
        for (Effect eff : unit.getLocalEffects(rules)) {
            if (eff.getBoolValue(name, unit, rules)) {
                return true;
            }
        }

        // TODO:  Do the same for the faction wide effects (Tech, Rules, Faction Settings, Faction Rules, Secret Projects)
        return false;

    }
    
}
