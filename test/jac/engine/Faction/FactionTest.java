/*
 * JAC Copyright (C) 2014 Gregory Jordan
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
package jac.engine.Faction;


import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Gregory Jordan
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
    
    @Test
    public void test_loading_original_factions(){
        Faction instance = new Faction();
        assertEquals("Load Gains", true, instance.load_alpha_fac_file("./testfiles/SMACX/GAIANS.TXT"));
        assertEquals("Load Morgan", true, instance.load_alpha_fac_file("./testfiles/SMACX/MORGAN.TXT"));
        assertEquals("Load Peacekeepers", true, instance.load_alpha_fac_file("./testfiles/SMACX/PEACE.TXT"));
        assertEquals("Load Angels", true, instance.load_alpha_fac_file("./testfiles/SMACX/angels.txt"));
        assertEquals("Load Believers", true, instance.load_alpha_fac_file("./testfiles/SMACX/believe.txt"));
        assertEquals("Load Caretakers", true, instance.load_alpha_fac_file("./testfiles/SMACX/caretake.txt"));
        assertEquals("Load Cyborg", true, instance.load_alpha_fac_file("./testfiles/SMACX/cyborg.txt"));
        assertEquals("Load Fungboy", true, instance.load_alpha_fac_file("./testfiles/SMACX/fungboy.txt"));
        assertEquals("Load hive", true, instance.load_alpha_fac_file("./testfiles/SMACX/hive.txt"));
        assertEquals("Load Pirates", true, instance.load_alpha_fac_file("./testfiles/SMACX/pirates.txt"));
        assertEquals("Load Spartans", true, instance.load_alpha_fac_file("./testfiles/SMACX/spartans.txt"));
        assertEquals("Load University", true, instance.load_alpha_fac_file("./testfiles/SMACX/univ.txt"));
        assertEquals("Load Usurper", true, instance.load_alpha_fac_file("./testfiles/SMACX/usurper.txt"));
              
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
        assertEquals("Faction Data has been loaded, should be true", true, result); 
    }
    
     @Test
    public void test_load_XML() {
        Faction instance = new Faction();
        String FileName = "./testfiles/FactionsbyBlueFlux/Rome/Rome.txt";
        instance.load_alpha_fac_file(FileName);
        boolean result = instance.saveXML();
        assertEquals("Faction Data has been loaded, should be true", true, result); 
        
        instance = new Faction();
        result = instance.readXML("ROME");
        assertEquals("Faction Data has been loaded, should be true", true, result); 
        
    }
    
}
