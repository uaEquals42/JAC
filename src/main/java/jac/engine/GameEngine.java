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
import jac.engine.ruleset.Ruleset;
import jac.engine.ruleset.SectionNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Gregory Jordan
 */
public class GameEngine {
    private static final Logger LOG = LoggerFactory.getLogger(GameEngine.class);
    private Gameboard gameboard;
    private List<FactionSettings> factions;
    private List<PlayerDetails> players;
    private Ruleset rules;

    public GameEngine() {
        // load ins
    }
    
    /**
     * Using this for the test case.  Don't use it for players.  Players should be pushed only what they should see.  Not the entire gamemap.
     * @return 
     */
    Gameboard getGameboard() {
        return gameboard;
    }



    public void createnewmap() {
        gameboard = new Gameboard(200, 200, 50, this);
    }
    
    void load_ruleset(Path location) throws SectionNotFoundException, IOException{
        //TODO: have a trigger and load from xml the rulesets.
        rules = new Ruleset.Builder().loadalpha_txt(location);
    }
    
    List<FactionSettings> load_factions() throws IOException{
        // First we need to generate a list of available factions.
        List<Path> faction_settings = FileHelpers.listFiles(Paths.get("./Factions"), "*settings.xml");
        LOG.debug(faction_settings.toString());
        for(Path pp : faction_settings){
            
        }
    return null;
    }
    public boolean canbeinsamesquare(int player1id, int player2id){
        // TODO: Actually keep track of alliances.
        return false;
    }
    
    void addplayer(){
        
    }
    
    public List<PlayerDetails> listPlayers(){
        return players;
    }
    
    public Ruleset getRuleset(){
        return rules;
    }

}
