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
public class OperatorNOT implements EffectNode<Boolean>{
    private final EffectNode<Boolean> test;
    public OperatorNOT(EffectNode test){
        this.test = test;
    }
    
    @Override
    public String toString(){
        return this.getClass().getSimpleName() + "(" + test +")";
    }
    
    @Override
    public Boolean result(GenericUnit unit, PlayerDetails player) {
        return ! test.result(unit, player);
    }
    
}
