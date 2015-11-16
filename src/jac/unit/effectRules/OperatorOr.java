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

import jac.unit.Unit;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gregory Jordan
 */
public class OperatorOr implements EffectNode<Boolean>{
    private final List<EffectNode<Boolean>> tests;
    
    public OperatorOr(List<EffectNode<Boolean>> tests){
        this.tests = tests;
    }
    
    public OperatorOr(EffectNode<Boolean> bool1, EffectNode<Boolean> bool2){
        tests = new ArrayList<>();
        tests.add(bool1);
        tests.add(bool2);
    }
    
     @Override
    public String toString(){
        return this.getClass().getSimpleName() + tests;
    }
    
    @Override
    public Boolean result(Unit unit) {
        for(EffectNode<Boolean> test : tests){
            if(test.result(unit)){
                return true;
            }
        }
        
        return false;
    }
    

}
