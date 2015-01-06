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
package jac.unit.tests;

import jac.engine.PlayerDetails;
import jac.unit.GenericUnit;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gregory Jordan
 */
public class testOR implements RestrictionTest{
    private final List<RestrictionTest> tests;
    
    public testOR(List<RestrictionTest> tests){
        this.tests = tests;
    }
    
    
    private testOR(Builder build){
        tests = build.tests;
    }
    
    public void add(RestrictionTest test){
        tests.add(test);
    }
    
    
    @Override
    public boolean passes(GenericUnit unit, PlayerDetails player) {
        for(RestrictionTest test : tests){
            if(test.passes(unit, player)){
                return true;
            }
        }
        
        return false;
    }
    
    public static class Builder{
        private List<RestrictionTest> tests;
        public Builder(){
            tests = new ArrayList<>();
        }
        public Builder(RestrictionTest test){
            tests = new ArrayList<>();
            tests.add(test);
        }
        public Builder add(RestrictionTest test){
            tests.add(test);
            return this;
        }
            
        
        public testOR build(){
            return new testOR(this);
        }
    }
}
