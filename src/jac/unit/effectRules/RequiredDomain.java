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

import jac.Enum.Domain;
import jac.engine.PlayerDetails;
import jac.unit.GenericUnit;

/**
 *
 * @author Gregory Jordan
 */
public class RequiredDomain<E extends Comparable> implements EffectValue {
    private final E valueIfTrue;
    private final E valueIfFalse;
    private final Domain domain;

    public RequiredDomain(Domain domain, E valueIfTrue, E valueIfFalse){
        this.domain = domain;
        this.valueIfTrue = valueIfTrue;
        this.valueIfFalse = valueIfFalse;
    }
    
    public static RequiredDomain<Boolean> bool(Domain domain){
        return new RequiredDomain(domain, true, false);
    }
    
    @Override
    public E result(GenericUnit unit, PlayerDetails player) {
        if(unit.getChassis().getDomain() == domain){
            return valueIfTrue;
        }
        else{
            return valueIfFalse;
        }
    }

}
