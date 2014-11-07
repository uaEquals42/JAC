/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jac.engine.ruleset;

import jac.engine.ruleset.Ruleset;
import java.io.IOException;
import java.util.logging.Level;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author grjordan
 */
public class RulesetTest {
    private static Logger log = LoggerFactory.getLogger(RulesetTest.class);
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
        //System.out.println("loadxml");
        
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
        //System.out.println("loadalpha_txt");
        Ruleset instance = new Ruleset();
        
        try {
            instance.loadalpha_txt("./testfiles/SMACX/alpha.txt");
        } catch (SectionNotFoundException ex) {
            log.error(ex.toString());
            fail("Section wasn't found it alpha.txt");
        } catch (IOException ex) {
            log.error(ex.toString());
            fail("File wasn't found.");
        }
        
 
    }

    /**
     * Test of loadalphax_txt method, of class Ruleset.
     */
    @Test
    public void testLoadalphax_txt() {
        //System.out.println("loadalphax_txt");
        Ruleset instance = new Ruleset();

        try {
            instance.loadalphax_txt();
        } catch (SectionNotFoundException ex) {
            log.error(ex.toString());
            fail("Section wasn't found it alpha.txt");
        } catch (IOException ex) {
            log.error(ex.toString());
            fail("File wasn't found.");
        }
        

    }
    
}
