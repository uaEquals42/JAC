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
package jac.engine.mapstuff;

import jac.unit.MoveTask;
import jac.engine.GameEngine;
import jac.unit.GenericUnit;
import java.awt.Point;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 *
 * @author Gregory Jordan
 */
public class Gameboard implements GameMap{

    private int width;
    private int height;
    private Square[][] map;
    private int seaLevel = 10000; // meters, everything is in meters for height.
    //  Terrain generated above this level will be above water, terrain below will be underwater.
    
    private final GameEngine gameEngine;

    public Gameboard(int width, int height, int percent_land, GameEngine game) {
        this.width = width;
        this.height = height;
        map = new Square[width][height];
        generateTestMap();
        this.gameEngine = game;
    }

    public void generateTestMap() {
        for (int ww = 0; ww < width; ww++) {
            for (int hh = 0; ww < height; hh++) {
                map[ww][hh] = new SquareVisible.Builder(ww, hh, 1000, gameEngine.getRuleset().getBasicTerrainStates().get("dirt"), gameEngine.listPlayers()).build();
            }
        }
    }

    public void generateRadomMap() {

    }

    @Override
    public Square viewSquare(int x, int y) {
        return map[x][y];
    }
    
      @Override
    public Square viewSquare(Point location) {
        return map[location.x][location.y];
    }
    

    @Override
    public Queue<Point> pathFind(Point start, Point goal) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<Square> generatePlayerMap(int PlayerID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addUnit(int x, int y, GenericUnit unit) throws MapDesync{
        map[x][y].addUnit(unit);
    }

    @Override
    public void moveUnitTo(Square begining, GenericUnit unit, Square destination) throws MapDesync {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    
  

}
