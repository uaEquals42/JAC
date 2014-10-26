/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine.ruleset;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author grjordan
 */
public class rulesetTest {
    
    public rulesetTest() {
    }
    

    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of loadxml method, of class ruleset.
     */
    @Test
    public void testLoadxml() {
        System.out.println("loadxml");
        ruleset instance = new ruleset();
        boolean expResult = true;
        boolean result = instance.loadxml();
        assertEquals(expResult, result);
   
       
    }

    /**
     * Test of loadalpha_txt method, of class ruleset.
     */
    @Test
    public void testLoadalpha_txt() {
        System.out.println("loadalpha_txt");
        ruleset instance = new ruleset();
        boolean expResult = true;
        boolean result = instance.loadalpha_txt();
        assertEquals(expResult, result);
 
    }

    /**
     * Test of loadalphax_txt method, of class ruleset.
     */
    @Test
    public void testLoadalphax_txt() {
        System.out.println("loadalphax_txt");
        ruleset instance = new ruleset();
        boolean expResult = true;
        boolean result = instance.loadalphax_txt();
        assertEquals(expResult, result);

    }
    
}
