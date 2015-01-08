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
package jac.unit.effectRules;

import jac.engine.PlayerDetails;
import jac.unit.GenericUnit;

/**
 *
 * @author Gregory Jordan
 */
public class HasFacility<T extends Comparable>  implements EffectValue<T> {
    private final String facilityKey;
    private final T valueIfTrue;
    private final T valueIfFalse;
    
    HasFacility(String facilityKey, T valueIfTrue, T valueIfFalse){
        this.facilityKey = facilityKey;
        this.valueIfTrue = valueIfTrue;
        this.valueIfFalse = valueIfFalse;
    }

    @Override
    public T result(GenericUnit unit, PlayerDetails player) {
       if(unit.getUnit_facilities().containsKey(facilityKey)){
            return valueIfTrue;
        }
        else{
            return valueIfFalse;
        }
    }
}
