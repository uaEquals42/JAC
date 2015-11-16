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

import jac.unit.GenericUnit;
import jac.unit.Unit;

/**
 *
 * @author Gregory Jordan
 * @param <T> Must be Comparable.
 */
public class BasePopulationIs<T extends Comparable<T>> implements EffectNode<Integer>{
    
    
    BasePopulationIs(){
    }

    public Integer result(GenericUnit unit) {
        return unit.getPopulation();
    }
    
    @Override
    public Integer result(Unit unit) {
        throw new IllegalArgumentException();
    }
    

}
