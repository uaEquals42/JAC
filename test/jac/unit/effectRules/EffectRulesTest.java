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
import org.junit.Test;
import jac.unit.effectRules.*;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Gregory Jordan
 */
public class EffectRulesTest {
     /**
     * Test of result method, of class OperatorEquals.
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
         assertEquals("true & true", Boolean.TRUE, new OperatorAND(Value.True(), Value.True()).result(null, null));
         assertEquals("true & false", Boolean.FALSE, new OperatorAND(Value.True(), Value.False()).result(null, null));
        
         List<EffectNode<Boolean>> tmp = new LinkedList<>();  // NOTE:  List has to be <EffectValue<Boolean>> for this to work!
         tmp.add(Value.True());
         tmp.add(Value.True());
         tmp.add(Value.True());
         tmp.add(Value.True());
         tmp.add(Value.True());
         tmp.add(Value.True());
         
         assertEquals("true & true & true", Boolean.TRUE, new OperatorAND(tmp).result(null, null));
         
         tmp.add(Value.False());
         assertEquals("true & true & false", Boolean.FALSE, new OperatorAND(tmp).result(null, null));
         
         
         tmp = new LinkedList<>();         
         tmp.add(Value.True());
         tmp.add(new OperatorAND(Value.True(), Value.True()));
         assertEquals("true & (true & true)", Boolean.TRUE, new OperatorAND(tmp).result(null, null));
         
         
         tmp = new LinkedList<>();
         tmp.add(Value.True());
         tmp.add(new OperatorAND(Value.True(), Value.False()));
         assertEquals("true & (true & false)", Boolean.FALSE, new OperatorAND(tmp).result(null, null));
         
    }
    
    @Test
    public void testORstatement(){
        assertEquals("False or False", Boolean.FALSE, new OperatorOr(Value.False(), Value.False()).result(null, null));
        assertEquals("True or False", Boolean.TRUE, new OperatorOr(Value.True(), Value.False()).result(null, null));
        
    }
    
    @Test
    public void testNotstatement(){
        assertEquals("Not True", Boolean.FALSE, new OperatorNOT(Value.True()).result(null, null));
        assertEquals("Not False", Boolean.TRUE, new OperatorNOT(Value.False()).result(null, null));
        
        assertEquals("Not (True & False)", Boolean.TRUE, new OperatorNOT(new OperatorAND(Value.True(), Value.False())).result(null, null));
    }
    
    @Test
    public void testADD(){
        EffectNode<Integer> tmp = new OperatorAdd(new Value<>(1), new Value<>(1));
        assertEquals("1 + 1", 2, (int)tmp.result(null, null));
        
        assertEquals("1 - 1", 0, (int) new OperatorAdd(new Value<>(1), new Value<>(-1)).result(null, null));
    }
    
    @Test
    public void testEquals(){
        assertEquals("IF True = True return 1 else 5", 1, new OperatorEquals(Value.True(),Value.True(),new Value<>(1), new Value<>(5)).result(null, null));
        System.out.print(new OperatorEquals<>(Value.True(),Value.True(),new Value<>(1), new Value<>(5)));
    }
}
