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
package jac.engine;

import jac.engine.mapstuff.Gameboard;
import jac.engine.Faction.FactionSettings;
import java.util.List;

/**
 *
 * @author Gregory Jordan
 */
public class GameEngine {

    private Gameboard gameboard;
    private List<FactionSettings> factions;

    public void GameEngine() {

    }

    public void createnewmap() {
        gameboard = new Gameboard(200, 200, 50, this);
    }
    
    void load_ruleset(){
        
    }
    
    public boolean canbeinsamesquare(int player1id, int player2id){
        // TODO: Actually keep track of alliances.
        return false;
    }
    
    void addplayer(){
        
    }

}
