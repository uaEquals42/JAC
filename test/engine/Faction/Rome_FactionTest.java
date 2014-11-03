/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine.Faction;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author grjordan
 */
public class Rome_FactionTest {

    public Rome_FactionTest() {
    }
    Faction instance;

    @Before
    public void setUp() {
        String FileName = "./testfiles/FactionsbyBlueFlux/Rome/Rome.txt";
        instance = new Faction();
        instance.load_alpha_fac_file(FileName);

    }

    @Test
    public void testSaveXML2() {

        boolean result = instance.saveXML();
        assertEquals("Faction Data has been loaded, should be true", true, result); // We haven't loaded any faction data.  So it should return false.
    }
    
    

    @Test
    public void socialTest() {

        assertEquals("Growth", 1, (int) instance.setting.social.get(SocialAreas.GROWTH));
        assertEquals("Probe", -1, (int) instance.setting.social.get(SocialAreas.PROBE));
        assertEquals("Economy", 1, (int) instance.setting.social.get(SocialAreas.ECONOMY));
    }

    @Test
    public void test_tech() {
        assertEquals("Tech", 1, (int) instance.setting.Free_Techs.size());
        assertEquals("Mobile", true, instance.setting.Free_Techs.get(0).equals("Mobile"));
    }

}
