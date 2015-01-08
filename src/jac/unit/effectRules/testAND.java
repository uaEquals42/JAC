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
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Gregory Jordan
 */
public class testAND implements EffectValue<Boolean>{
    private final List<EffectValue<Boolean>> tests;
   
    
    public testAND(List<EffectValue<Boolean>> tests){
        this.tests = tests;
    }
    
     
    public testAND(EffectValue<Boolean> test1, EffectValue<Boolean> test2){
        tests = new LinkedList<>();
        tests.add(test1);
        tests.add(test2);
    }
    
    @Override
    public String toString(){
        return this.getClass().getSimpleName() + tests;
    }
    
    @Override
    public Boolean result(GenericUnit unit, PlayerDetails player) {
        for(EffectValue<Boolean> test : tests){
            if(! test.result(unit, player)){
                return Boolean.FALSE;
            }
        }
        
        return Boolean.TRUE;
    }
    
   
}
