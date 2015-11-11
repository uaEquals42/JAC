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


import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * This loads all the smac factions... sees if any errors generated.
 * @author Gregory Jordan
 */
public class FactionTest {
   private static final String SMACX_LOCATION = "./testfiles/SMACX/";
    public FactionTest() {

        
    }
    // This just tries loading in all the different factions in the tests.
    
    @Test
    public void test_gaians(){
       try {
           Faction gaians = Faction.loadSmacFactionFile(SMACX_LOCATION+"GAIANS.TXT");
       } catch (IOException ex) {
           Logger.getLogger(FactionTest.class.getName()).log(Level.SEVERE, null, ex);
           fail("IOException");
       }
    }



    /*
    @Test
    public void test_loading_original_factions(){
       
        assertEquals("Load Gains", true, instance.loadSmacFactionFile("GAIANS.TXT"));
        assertEquals("Load Morgan", true, instance.loadSmacFactionFile("./testfiles/SMACX/MORGAN.TXT"));
        assertEquals("Load Peacekeepers", true, instance.loadSmacFactionFile("./testfiles/SMACX/PEACE.TXT"));
        assertEquals("Load Angels", true, instance.loadSmacFactionFile("./testfiles/SMACX/angels.txt"));
        assertEquals("Load Believers", true, instance.loadSmacFactionFile("./testfiles/SMACX/believe.txt"));
        assertEquals("Load Caretakers", true, instance.loadSmacFactionFile("./testfiles/SMACX/caretake.txt"));
        assertEquals("Load Cyborg", true, instance.loadSmacFactionFile("./testfiles/SMACX/cyborg.txt"));
        assertEquals("Load Fungboy", true, instance.loadSmacFactionFile("./testfiles/SMACX/fungboy.txt"));
        assertEquals("Load hive", true, instance.loadSmacFactionFile("./testfiles/SMACX/hive.txt"));
        assertEquals("Load Pirates", true, instance.loadSmacFactionFile("./testfiles/SMACX/pirates.txt"));
        assertEquals("Load Spartans", true, instance.loadSmacFactionFile("./testfiles/SMACX/spartans.txt"));
        assertEquals("Load University", true, instance.loadSmacFactionFile("./testfiles/SMACX/univ.txt"));
        assertEquals("Load Usurper", true, instance.loadSmacFactionFile("./testfiles/SMACX/usurper.txt"));
              
    }
*/
    

    
    @Test
    public void testSaveXML2() throws IOException, JAXBException {
        
        String FileName = "./testfiles/FactionsbyBlueFlux/Rome/Rome.txt";
        Faction instance = Faction.loadSmacFactionFile(FileName);
        Path result = instance.saveXML();
        
    }
    
     @Test
    public void test_load_XML() throws JAXBException {
        String FileName = "./testfiles/FactionsbyBlueFlux/Rome/Rome.txt";
        Faction instance;
       try {
           instance = Faction.loadSmacFactionFile(FileName);
           Path result = instance.saveXML();
          
            
            instance = Faction.readXML(result);
            Boolean test = instance.getCodeName().equalsIgnoreCase("ROME");
        assertEquals("Faction Data has been loaded, should be true", true, test); 
            
       } catch (IOException ex) {
           Logger.getLogger(FactionTest.class.getName()).log(Level.SEVERE, null, ex);
       }
        
        
        
        
        
    }
    
}
