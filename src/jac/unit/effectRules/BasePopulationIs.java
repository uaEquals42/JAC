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
 * @param <E> Must be Comparable.
 */
public class BasePopulationIs<E extends Comparable> implements EffectNode<E>{
    private final int basePop;
    private final EffectNode<E> valueIfTrue;
    private final EffectNode<E>valueIfFalse;
    
    BasePopulationIs(int baseSize, EffectNode<E> valueIfTrue, EffectNode<E> valueIfFalse){
        this.basePop = baseSize;
        this.valueIfTrue = valueIfTrue;
        this.valueIfFalse = valueIfFalse;
    }

    @Override
    public E result(GenericUnit unit, PlayerDetails player) {
        if(unit.getPopulation()==basePop){
            return valueIfTrue.result(unit, player);
        }
        else{
            return valueIfFalse.result(unit, player);
        }
    }
}
