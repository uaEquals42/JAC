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

import java.io.IOException;
import org.junit.Test;
import static org.junit.Assert.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Gregory Jordan
 */
public class RulesetTest {
    private static Logger log = LoggerFactory.getLogger(RulesetTest.class);
    public RulesetTest() {
    }
    

    

    /**
     * Test of loadalpha_txt method, of class Ruleset.
     */
    @Test
    public void testLoadalpha_txt() {
        //System.out.println("loadalpha_txt");
        
        
        try {
            Ruleset instance = new Ruleset.Builder().loadalpha_txt("./testfiles/SMACX/alpha.txt");
        } catch (SectionNotFoundException ex) {
            log.error(ex.toString());
            fail("Section wasn't found it alpha.txt");
        } catch (IOException ex) {
            log.error(ex.toString());
            fail("File wasn't found.");
        }
        
 
    }

    /**
     * Test of loadalphax_txt method, of class Ruleset.
     */
    @Test
    public void testLoadalphax_txt() {
        //System.out.println("loadalphax_txt");
        Ruleset instance; 

        try {
            instance = new Ruleset.Builder().loadalphax_txt();
        } catch (SectionNotFoundException ex) {
            log.error(ex.toString());
            fail("Section wasn't found it alpha.txt");
        } catch (IOException ex) {
            log.error(ex.toString());
            fail("File wasn't found.");
        }
        

    }
    
}
