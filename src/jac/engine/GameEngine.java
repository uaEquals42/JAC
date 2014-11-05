/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jac.engine;

import jac.engine.Faction.FactionSettings;
import java.util.List;

/**
 *
 * @author Gregory
 */
public class GameEngine {

    private Gameboard gameboard;
    private List<FactionSettings> factions;

    public void GameEngine() {

    }

    public void createnewmap() {
        gameboard = new Gameboard(200, 200, 50);
    }

}
