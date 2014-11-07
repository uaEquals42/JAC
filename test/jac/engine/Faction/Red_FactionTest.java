/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jac.engine.Faction;
import jac.engine.Faction.SocialAreas;
import jac.engine.Faction.Faction;
import java.lang.Integer;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 *
 * @author grjordan
 */
public class Red_FactionTest {
    
    public Red_FactionTest() {
    }
    static Faction instance;
    
    @BeforeClass
    public static void setUp() {
        String FileName = "./testfiles/FactionsbyBlueFlux/red/RED.txt";
        instance = new Faction();
        instance.load_alpha_fac_file(FileName);

    }
    
    
    @Test
    public void testSaveXML2() {
        boolean result = instance.saveXML();
        assertEquals("Faction Data has been saved, should be true", true, result); 
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
        assertEquals("Tech", 1, (int) instance.setting.Free_Techs.size());
        assertEquals("InfNet", true, instance.setting.Free_Techs.get(0).equals("InfNet"));
    }
    
}
