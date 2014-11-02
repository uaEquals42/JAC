/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine.ruleset;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author grjordan
 */
public class RulesetTest {
    
    public RulesetTest() {
    }
    

    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of loadxml method, of class Ruleset.
     */
    @Test
    public void testLoadxml() {
        System.out.println("loadxml");
        Ruleset instance = new Ruleset();
        boolean expResult = true;
        boolean result = instance.loadxml();
        assertEquals(expResult, result);
   
       
    }

    /**
     * Test of loadalpha_txt method, of class Ruleset.
     */
    @Test
    public void testLoadalpha_txt() {
        System.out.println("loadalpha_txt");
        Ruleset instance = new Ruleset();
        boolean expResult = true;
        boolean result = instance.loadalpha_txt("./testfiles/SMACX/alpha.txt");
        assertEquals(expResult, result);
 
    }

    /**
     * Test of loadalphax_txt method, of class Ruleset.
     */
    @Test
    public void testLoadalphax_txt() {
        System.out.println("loadalphax_txt");
        Ruleset instance = new Ruleset();
        boolean expResult = true;
        boolean result = instance.loadalphax_txt();
        assertEquals(expResult, result);

    }
    
}
