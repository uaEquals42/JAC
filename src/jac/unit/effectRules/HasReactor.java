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
public class HasReactor<E extends Comparable> implements EffectNode{
    private final String reactorKey;
    private final EffectNode<E> thenValue;
    private final EffectNode<E> elseValue;
    
    HasReactor(String reactorKey, EffectNode<E> thenValue, EffectNode<E> elseValue){
        this.reactorKey = reactorKey;
        this.thenValue = thenValue;
        this.elseValue = elseValue;
    }
    
    public static HasReactor<Boolean> HasItKey(String reactorKey){
        return new HasReactor<>(reactorKey, Value.True(), Value.False());
    }

    @Override
    public E result(GenericUnit unit, PlayerDetails player) {
        if(unit.getReactor().getKey().equals(reactorKey)){
            return thenValue.result(unit, player);
        }
        else{
            return elseValue.result(unit, player);
        }
    }
    
}
