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

import jac.engine.ruleset.Ruleset;
import jac.unit.Unit;
import java.text.MessageFormat;


public class OperatorEquals<T1 extends Comparable<T1>> implements EffectNode<Boolean> {
    private final EffectNode<T1> value1;
    private final EffectNode<T1> value2;
    
    
    public OperatorEquals(EffectNode<T1> value1, EffectNode<T1> value2){
        this.value1 = value1;
        this.value2 = value2;

    }

    @Override
    public String toString() {
        return MessageFormat.format("({0} == {1})", value1, value2);
        
    }
    
    @Override
    public Boolean result(Unit unit, Ruleset rules) {
        return value1.result(unit, rules)==value2.result(unit, rules);
    }
    
}
