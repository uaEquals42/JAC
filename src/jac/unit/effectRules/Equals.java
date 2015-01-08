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
import java.text.MessageFormat;


public class Equals<T1 extends Comparable, T2 extends Comparable> implements EffectValue<T2> {
    private final EffectValue<T1> value1;
    private final EffectValue<T1> value2;
    
    private final T2 valueIfTrue;
    private final T2 valueIfFalse;
    
    public Equals(EffectValue<T1> value1, EffectValue<T1> value2, T2 valueIfTrue, T2 valueIfFalse){
        this.value1 = value1;
        this.value2 = value2;
        this.valueIfTrue = valueIfTrue;
        this.valueIfFalse = valueIfFalse;
    }

    @Override
    public String toString() {
        return MessageFormat.format("if({0} == {1})'{'\n"
                + "{2} \n"
                + "else '{'\n"
                + "{3} \n"
                + "'}'", value1, value2, valueIfTrue, valueIfFalse);
        
    }
    
    @Override
    public T2 result(GenericUnit unit, PlayerDetails player) {
        if(value1.result(unit, player).compareTo(value2.result(unit, player)) == 0){
            return valueIfTrue;
        }
        else{
            return valueIfFalse;
        }
    }
    
}
