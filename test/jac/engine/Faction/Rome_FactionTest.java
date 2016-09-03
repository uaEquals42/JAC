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

import jac.Enum.SocialAreas;
import jac.engine.Faction.Faction;
import jac.engine.ruleset.SectionNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 *
 * @author Gregory Jordan
 */
public class Rome_FactionTest {

    public Rome_FactionTest() {
    }
    static Faction instance;

    @BeforeClass
    public static void setUp() {
        String FileName = "./testfiles/FactionsbyBlueFlux/Rome/Rome.txt";
        
        try {
            instance = new Faction.Builder().loadSmacFactionFile(FileName).build();
        } catch (IOException ex) {
            Logger.getLogger(Rome_FactionTest.class.getName()).log(Level.SEVERE, null, ex);
            fail("Failed to load the test file: " + FileName);
        } catch (SectionNotFoundException ex) {
            Logger.getLogger(Rome_FactionTest.class.getName()).log(Level.SEVERE, null, ex);
            fail("Section not found");
        }

    }


    
    @Test 
        public void testJson() throws IOException{
      
            instance.to_json(Paths.get("./Mods/TestFactions"));
            
 
    }

    @Test
    public void socialTest() {

        assertEquals("Growth", 1, (int) instance.setting.social.get(SocialAreas.GROWTH));
        assertEquals("Probe", -1, (int) instance.setting.social.get(SocialAreas.PROBE));
        assertEquals("Economy", 1, (int) instance.setting.social.get(SocialAreas.ECONOMY));
    }

    @Test
    public void test_tech() {
        assertEquals("Tech", 1, instance.setting.Free_Techs.size());
        assertEquals("Mobile", true, instance.setting.Free_Techs.get(0).equals("Mobile"));
    }

}
