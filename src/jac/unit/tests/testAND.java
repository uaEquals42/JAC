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
import java.util.List;

/**
 *
 * @author Gregory Jordan
 */
public class testAND implements RestrictionTest{
    private final List<RestrictionTest> tests;
   
    
    testAND(List<RestrictionTest> tests){
        this.tests = tests;
    }
    
    @Override
    public boolean passes(int lifespan, GenericUnit unit, PlayerDetails player) {
        for(RestrictionTest test : tests){
            if(! test.passes(lifespan, unit, player)){
                return false;
            }
        }
        
        return true;
    }
}
