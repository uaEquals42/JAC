/*
 * JAC Copyright (C) 2015 Gregory Jordan
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
package jac.engine;

import jac.engine.Faction.FactionSettings;
import jac.engine.ruleset.Ruleset;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Gregory Jordan
 */
public class GameEngineTest {
    
    public GameEngineTest() {
    }
    
    @Before
    public void setUp() {
    }

    /**
     * Test of GameEngine method, of class GameEngine.
     */
    @Test
    public void testGameEngine() {
        System.out.println("GameEngine");
        GameEngine instance = new GameEngine();
        instance.GameEngine();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createnewmap method, of class GameEngine.
     */
    @Test
    public void testCreatenewmap() {
        System.out.println("createnewmap");
        GameEngine instance = new GameEngine();
        instance.createnewmap();       
    }

    /**
     * Test of load_ruleset method, of class GameEngine.
     */
    @Test
    public void testLoad_ruleset() throws Exception {
        System.out.println("load_ruleset");
        String location = "";
        GameEngine instance = new GameEngine();
        instance.load_ruleset(location);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of load_factions method, of class GameEngine.
     */
    @Test
    public void testLoad_factions() throws Exception {
        System.out.println("load_factions");
        GameEngine instance = new GameEngine();
        List<FactionSettings> expResult = null;
        List<FactionSettings> result = instance.load_factions();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of canbeinsamesquare method, of class GameEngine.
     */
    @Test
    public void testCanbeinsamesquare() {
        System.out.println("canbeinsamesquare");
        int player1id = 0;
        int player2id = 0;
        GameEngine instance = new GameEngine();
        boolean expResult = false;
        boolean result = instance.canbeinsamesquare(player1id, player2id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addplayer method, of class GameEngine.
     */
    @Test
    public void testAddplayer() {
        System.out.println("addplayer");
        GameEngine instance = new GameEngine();
        instance.addplayer();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of listPlayers method, of class GameEngine.
     */
    @Test
    public void testListPlayers() {
        System.out.println("listPlayers");
        GameEngine instance = new GameEngine();
        List<PlayerDetails> expResult = null;
        List<PlayerDetails> result = instance.listPlayers();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRuleset method, of class GameEngine.
     */
    @Test
    public void testGetRuleset() {
        System.out.println("getRuleset");
        GameEngine instance = new GameEngine();
        Ruleset expResult = null;
        Ruleset result = instance.getRuleset();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
