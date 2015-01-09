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
package jac.engine.ruleset;

import jac.Enum.Domain;
import java.io.IOException;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Gregory Jordan
 */
public class SMAC_Test {
    private static Logger log = LoggerFactory.getLogger(SMAC_Test.class);
    public SMAC_Test() {
        
    }
    
    static Ruleset rules;
    
    @BeforeClass
    public static void setUp() {
        
        try {
            rules = new Ruleset.Builder().loadalpha_txt("./testfiles/SMACX/alpha.txt");
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
        
        assertEquals("Number of ideologies", 16, rules.getIdeologies().size());
        
        assertEquals("Frontier Category ", "Politics", rules.getIdeologies().get(0).category);
        assertEquals("Police State Category ", "Politics", rules.getIdeologies().get(1).category);
        assertEquals("Democratic Category ", "Politics", rules.getIdeologies().get(2).category);
        assertEquals("Fundamentalist Category ", "Politics", rules.getIdeologies().get(3).category);
        
        assertEquals("Simple Category ", "Economics", rules.getIdeologies().get(4).category);
        assertEquals("Free Market Category ", "Economics", rules.getIdeologies().get(5).category);
        assertEquals("Planned Category ", "Economics", rules.getIdeologies().get(6).category);
        assertEquals("Green Category ", "Economics", rules.getIdeologies().get(7).category);
 
        assertEquals("Survival Category ", "Values", rules.getIdeologies().get(8).category);
        assertEquals("Power Category ", "Values", rules.getIdeologies().get(9).category);
        assertEquals("Knowledge Category ", "Values", rules.getIdeologies().get(10).category);
        assertEquals("Wealth Category ", "Values", rules.getIdeologies().get(11).category);
        
        assertEquals("None Category ", "Future Society", rules.getIdeologies().get(12).category);
        assertEquals("Cybernetic Category ", "Future Society", rules.getIdeologies().get(13).category);
        assertEquals("Eudaimonic Category ", "Future Society", rules.getIdeologies().get(14).category);
        assertEquals("Thought Control Category ", "Future Society", rules.getIdeologies().get(15).category);
        
        
        assertEquals("Frontier name ", "Frontier", rules.getIdeologies().get(0).name);
        assertEquals("Police State name ", "Police State", rules.getIdeologies().get(1).name);
        assertEquals("Democratic name ", "Democratic", rules.getIdeologies().get(2).name);
        assertEquals("Fundamentalist name ", "Fundamentalist", rules.getIdeologies().get(3).name);
        
        assertEquals("Simple name ", "Simple", rules.getIdeologies().get(4).name);
        assertEquals("Free Market name ", "Free Market", rules.getIdeologies().get(5).name);
        assertEquals("Planned name ", "Planned", rules.getIdeologies().get(6).name);
        assertEquals("Green name ", "Green", rules.getIdeologies().get(7).name);
 
        assertEquals("Survival name ", "Survival", rules.getIdeologies().get(8).name);
        assertEquals("Power name ", "Power", rules.getIdeologies().get(9).name);
        assertEquals("Knowledge name ", "Knowledge", rules.getIdeologies().get(10).name);
        assertEquals("Wealth name ", "Wealth", rules.getIdeologies().get(11).name);
        
        assertEquals("None name ", "None", rules.getIdeologies().get(12).name);
        assertEquals("Cybernetic name ", "Cybernetic", rules.getIdeologies().get(13).name);
        assertEquals("Eudaimonic name ", "Eudaimonic", rules.getIdeologies().get(14).name);
        assertEquals("Thought Control name ", "Thought Control", rules.getIdeologies().get(15).name);
        
        
    }
 
    @Test
    public void test_Technologies() {
        assertEquals("Number of technologies", 77, rules.getTechnologies().size());
        assertEquals("Centari Ecology name", "Centauri Ecology", rules.getTechnologies().get("#TECH6").getName(rules.getTran()));
        assertEquals("Centari Ecology Quote", "Lady Deirdre Skye", rules.getTechnologies().get("#TECH6").getQuotes(rules.getTran()).get(0).person);
        
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
        assertEquals("Infantry movment", 3, rules.getChasises().get("Infantry").getMovementPoints());
        assertEquals("Infantry cost", 1, rules.getChasises().get("Infantry").getFlatcost());
        assertEquals("Infantry Prereqs", 0, rules.getChasises().get("Infantry").getPre_requisite_technology().size());
        assertEquals("Infantry", Domain.LAND, rules.getChasises().get("Infantry").getDomain());
        
        assertEquals("Missile prereqs", "Orbital", rules.getChasises().get("Missile").getPre_requisite_technology().get(0));
        assertEquals("Missile is a missle?", true, rules.getChasises().get("Missile").isMissle());
        assertEquals("Missle is an air unit?", Domain.AIR, rules.getChasises().get("Missile").getDomain());
    }
    
    
    @Test
    public void test_Facility(){
        assertEquals("Number of facilities/secrectprojects", 71, rules.getFacilities().size());
        
        
    }
}
