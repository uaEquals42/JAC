/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine.Faction;

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
        Faction instance = new Faction();
        String FileName = "./testfiles/FactionsbyBlueFlux/Rome/Rome.txt";
        instance.load_alpha_fac_file(FileName);
        boolean result = instance.saveXML();
        assertEquals("Faction Data has been loaded, should be true", true, result); // We haven't loaded any faction data.  So it should return false.
    }
    
    @Test
    public void socialTest(){
        
        assertEquals("Growth",instance.setting.social.get(SocialAreas.GROWTH)==1,true);
         assertEquals("Probe",instance.setting.social.get(SocialAreas.PROBE)==-1,true);
         assertEquals("Economy",instance.setting.social.get(SocialAreas.ECONOMY)==1,true);
    }
    
    @Test
    public void test_tech(){
        assertEquals("Tech",instance.setting.Free_Techs.size()==1, true);
        assertEquals("Mobile",instance.setting.Free_Techs.get(0).equals("Mobile"), true);
    }
    
}
