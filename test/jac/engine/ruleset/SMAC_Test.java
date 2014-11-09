/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jac.engine.ruleset;

import java.io.IOException;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author grjordan
 */
public class SMAC_Test {
    private static Logger log = LoggerFactory.getLogger(SMAC_Test.class);
    public SMAC_Test() {
        
    }
    
    static Ruleset rules;
    
    @BeforeClass
    public static void setUp() {
        rules = new Ruleset();
        try {
            rules.loadalpha_txt("./testfiles/SMACX/alpha.txt");
        } catch (SectionNotFoundException ex) {
            log.error(ex.toString());
            fail("Section wasn't found it alpha.txt");
        } catch (IOException ex) {
            log.error(ex.toString());
            fail("File wasn't found.");
        }
    }
    

    @Test
    public void checkidologies(){
        
        assertEquals("Number of ideologies", 16, rules.ideologies.size());
        
        assertEquals("Frontier Category ", "Politics", rules.ideologies.get(0).category);
        assertEquals("Police State Category ", "Politics", rules.ideologies.get(1).category);
        assertEquals("Democratic Category ", "Politics", rules.ideologies.get(2).category);
        assertEquals("Fundamentalist Category ", "Politics", rules.ideologies.get(3).category);
        
        assertEquals("Simple Category ", "Economics", rules.ideologies.get(4).category);
        assertEquals("Free Market Category ", "Economics", rules.ideologies.get(5).category);
        assertEquals("Planned Category ", "Economics", rules.ideologies.get(6).category);
        assertEquals("Green Category ", "Economics", rules.ideologies.get(7).category);
 
        assertEquals("Survival Category ", "Values", rules.ideologies.get(8).category);
        assertEquals("Power Category ", "Values", rules.ideologies.get(9).category);
        assertEquals("Knowledge Category ", "Values", rules.ideologies.get(10).category);
        assertEquals("Wealth Category ", "Values", rules.ideologies.get(11).category);
        
        assertEquals("None Category ", "Future Society", rules.ideologies.get(12).category);
        assertEquals("Cybernetic Category ", "Future Society", rules.ideologies.get(13).category);
        assertEquals("Eudaimonic Category ", "Future Society", rules.ideologies.get(14).category);
        assertEquals("Thought Control Category ", "Future Society", rules.ideologies.get(15).category);
        
        
        assertEquals("Frontier name ", "Frontier", rules.ideologies.get(0).name);
        assertEquals("Police State name ", "Police State", rules.ideologies.get(1).name);
        assertEquals("Democratic name ", "Democratic", rules.ideologies.get(2).name);
        assertEquals("Fundamentalist name ", "Fundamentalist", rules.ideologies.get(3).name);
        
        assertEquals("Simple name ", "Simple", rules.ideologies.get(4).name);
        assertEquals("Free Market name ", "Free Market", rules.ideologies.get(5).name);
        assertEquals("Planned name ", "Planned", rules.ideologies.get(6).name);
        assertEquals("Green name ", "Green", rules.ideologies.get(7).name);
 
        assertEquals("Survival name ", "Survival", rules.ideologies.get(8).name);
        assertEquals("Power name ", "Power", rules.ideologies.get(9).name);
        assertEquals("Knowledge name ", "Knowledge", rules.ideologies.get(10).name);
        assertEquals("Wealth name ", "Wealth", rules.ideologies.get(11).name);
        
        assertEquals("None name ", "None", rules.ideologies.get(12).name);
        assertEquals("Cybernetic name ", "Cybernetic", rules.ideologies.get(13).name);
        assertEquals("Eudaimonic name ", "Eudaimonic", rules.ideologies.get(14).name);
        assertEquals("Thought Control name ", "Thought Control", rules.ideologies.get(15).name);
        
        
    }
 
    @Test
    public void test_Technologies() {
        assertEquals("Number of technologies", 77, rules.technologies.size());
        assertEquals("Centari Ecology name", "Centauri Ecology", rules.technologies.get("#TECH6").getName());
        assertEquals("Centari Ecology Quote", "Lady Deirdre Skye", rules.technologies.get("#TECH6").getQuotes().get(0).person);
        
        assertEquals("Centari Ecology has no prerequisites", 0, rules.find_tech("#TECH6").pre_requisites_names.size());
        assertEquals("Centari Ecology flag", 1, rules.find_tech("#TECH6").fungus_nutrient_bonus);

        assertEquals("Thresh flag", 1, rules.find_tech("#TECH56").fungus_mineral_bonus);
        assertEquals("Thresh flag", 0, rules.find_tech("#TECH56").fungus_nutrient_bonus);

        assertEquals("AlphCen flag", 1, rules.find_tech("#TECH64").fungus_energy_bonus);
        assertEquals("AlphCen flag", true, rules.find_tech("#TECH64").revealmap);
        assertEquals("AlphCen flag", true, rules.find_tech("#TECH64").freetech);

        assertEquals("Viral flag", true, rules.find_tech("#TECH76").genewar_defence);
        assertEquals("Viral flag", true, rules.find_tech("#TECH76").genewar_offence);

    }

    @Test
    public void test_Chasis(){
        // Test a couple of the chasis to make sure the data is right.
        assertEquals("Infantry movment", 1, rules.chasises.get(0).getSpeed());
        assertEquals("Infantry cost", 1, rules.chasises.get(0).getCost());
        assertEquals("Infantry Prereqs", 0, rules.chasises.get(0).getPre_req_str().size());
        assertEquals("Infantry", MovementType.LAND, rules.chasises.get(0).getTriad());
        
        assertEquals("Missile prereqs", "Orbital", rules.chasises.get(8).getPre_req_str().get(0));
        assertEquals("Missile is a missle?", true, rules.chasises.get(8).isMissle());
        assertEquals("Missle is an air unit?", MovementType.AIR, rules.chasises.get(8).getTriad());
    }
    
    
    @Test
    public void test_Facility(){
        
    }
}
