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

import jac.Enum.IntNames;

/**
 * Stores code shared between Unit_Plan and GenericUnit.  
 * @author Gregory Jordan
 */
public class UnitLibrary {
    
    static int calculateInteger(IntNames name, Unit unit) {
        int value = 0;
        float multiplier = 1;
        for (Effect eff : unit.getLocalEffects()) {
            value = value + eff.getIntValue(name, unit);
            multiplier = multiplier * eff.getFloatValue(name, unit);
        }

        // TODO:  Do the same for the faction wide effects (Tech, Rules, Faction Settings, Faction Rules, Secret Projects)
        return (int) (value * multiplier);
    }
    
}
