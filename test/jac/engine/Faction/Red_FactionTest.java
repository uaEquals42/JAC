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
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Gregory Jordan
 */
public class Red_FactionTest {
    
    public Red_FactionTest() {
    }
    static org.slf4j.Logger log = LoggerFactory.getLogger(Red_FactionTest.class);
    static Faction instance;
    static final Path MOD_LOCATION = Paths.get("./Mods/TestFactions");
    @BeforeClass
    public static void setUp() {
        String FileName = "./testfiles/FactionsbyBlueFlux/red/RED.txt";
        
        try {
            instance = Faction.loadSmacFactionFile(FileName);
            
        } catch (IOException ex) {
            Logger.getLogger(Red_FactionTest.class.getName()).log(Level.SEVERE, null, ex);
            fail("Failed to load the test file: " + FileName);
        }

    }
    
    
    @Test
    public void testLoadJson() throws IOException{
        instance.toJson(MOD_LOCATION);  
        Faction fac = Faction.loadJson(MOD_LOCATION.resolve(Faction.FACTION_FOLDER).resolve("RED"));
        log.debug(fac.toString());
    }
    
    @Test
    public void testSaveJson() throws IOException{
        instance.toJson(Paths.get("./Mods/TestFactions"));  
    }
    
    

    @Test
    @SuppressWarnings("BoxingBoxedValue")
    public void socialTest() {
       
        assertEquals("MORALE", 1,   (int)instance.setting.social.get(SocialAreas.MORALE) );
        assertEquals("RESEARCH", 1, (int)instance.setting.social.get(SocialAreas.RESEARCH) );
        assertEquals("ECONOMY", -2, (int)instance.setting.social.get(SocialAreas.ECONOMY));
    }

    @Test
    public void test_tech() {
        assertEquals("Tech", 1, instance.setting.Free_Techs.size());
        assertEquals("InfNet", true, instance.setting.Free_Techs.get(0).equals("InfNet"));
    }
    
}
