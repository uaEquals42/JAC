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
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;
import jac.unit.effectRules.*;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Gregory Jordan
 */
public class TestEffectRules {
     /**
     * Test of result method, of class Equals.
     */
    @Test
    public void testValue() {
        
        assertEquals("Value.True ", Boolean.TRUE, Value.True().result(null, null));
        assertEquals("Value.False ", Boolean.FALSE, Value.False().result(null, null));
        assertEquals("Value(True) ", Boolean.TRUE, new Value<>(true).result(null, null));
        assertEquals("Value(FALSE) ", Boolean.FALSE, new Value<>(false).result(null, null));
        
        //Now to integers.
        assertEquals("Value.zero() ", new Integer(0), Value.zero().result(null, null));
        assertEquals("Value(5) ", new Integer(5), new Value<>(5).result(null, null));
        
        
        // TODO review the generated test code and remove the default call to fail.
       
    }
    
    @Test
    public void testANDstatement(){
         assertEquals("true & true", Boolean.TRUE, new testAND(Value.True(), Value.True()).result(null, null));
         assertEquals("true & false", Boolean.FALSE, new testAND(Value.True(), Value.False()).result(null, null));
        
         List<EffectValue<Boolean>> tmp = new LinkedList<>();  // NOTE:  List has to be <EffectValue<Boolean>> for this to work!
         tmp.add(Value.True());
         tmp.add(Value.True());
         tmp.add(Value.True());
         tmp.add(Value.True());
         tmp.add(Value.True());
         tmp.add(Value.True());
         
         assertEquals("true & true & true", Boolean.TRUE, new testAND(tmp).result(null, null));
         
         tmp.add(Value.False());
         assertEquals("true & true & false", Boolean.FALSE, new testAND(tmp).result(null, null));
         
    }
}
