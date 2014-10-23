/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

import engine.Faction.FactionSettings;
import java.util.List;

/**
 *
 * @author Gregory
 */
public class GameEngine {
	Gameboard gameboard;
        List<FactionSettings> factions;
        
        public void GameEngine(){
            // Load all the game factions availiable
             
            
        }
        
	public void createnewmap(){
		gameboard = new Gameboard(200,200,50);
	}
        
	
}
