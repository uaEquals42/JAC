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

/**
 *
 * @author Gregory Jordan
 * @param <E>
 */
public class Value<E extends Comparable<E>> implements EffectNode<E>{

    private final E value;
    
    public Value(E value){
        this.value = value;
    }
    
    public static Value<Boolean> True(){
        return new Value<>(true);
    }
    
    public static Value<Boolean> False(){
        return new Value<>(false);
    }
    
    public static Value<Integer> Zero(){
        return new Value<>(0);
    }
    

    public static Value<Float> One(){
        return new Value<Float>(1f);
    }
    
    @Override
    public String toString(){
        return this.getClass().getSimpleName() + "("+value+")";
    }
    
    @Override
    public E result(Unit unit, Ruleset rules) {
        return value;
    }
    

    
}
