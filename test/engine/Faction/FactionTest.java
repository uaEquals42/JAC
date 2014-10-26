/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine.Faction;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author grjordan
 */
public class FactionTest {
    
    public FactionTest() {
    }
    // This just tries loading in all the different factions in the tests.
    
    @Test
    public void load_faction_red(){
        String FileName = "./testfiles/FactionsbyBlueFlux/red/RED.txt";
        Faction instance = new Faction();
        boolean expResult = true;
        boolean result = instance.load_alpha_fac_file(FileName);
        assertEquals(expResult, result);
    }

    /**
     * Test of load_alpha_fac_file method, of class Faction.
     */
    @Test
    public void testLoad_alpha_fac_file_rome() {
        String FileName = "./testfiles/FactionsbyBlueFlux/Rome/Rome.txt";
        Faction instance = new Faction();
        boolean expResult = true;
        boolean result = instance.load_alpha_fac_file(FileName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }
    
    @Test
    public void testLoad_alpha_fac_file_reagan() {
        String FileName = "./testfiles/FactionsbyBlueFlux/Reagan/reagan.txt";
        Faction instance = new Faction();
        boolean expResult = true;
        boolean result = instance.load_alpha_fac_file(FileName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of saveXML method, of class Faction.
     */
    @Test
    public void testSaveXML() {
        Faction instance = new Faction();
        boolean result = instance.saveXML();
        assertEquals("The faction data hasn't been set yet, should return false", false, result); // We haven't loaded any faction data.  So it should return false.
    }
    
    @Test
    public void testSaveXML2() {
        Faction instance = new Faction();
        String FileName = "./testfiles/FactionsbyBlueFlux/Rome/Rome.txt";
        instance.load_alpha_fac_file(FileName);
        boolean result = instance.saveXML();
        assertEquals("Faction Data has been loaded, should be true", true, result); // We haven't loaded any faction data.  So it should return false.
    }
    
}
