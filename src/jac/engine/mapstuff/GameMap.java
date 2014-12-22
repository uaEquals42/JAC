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

import jac.unit.GenericUnit;
import java.awt.Point;

import java.util.Collection;
import java.util.Queue;

/**
 *
 * @author Gregory Jordan
 */
public interface GameMap {
    Square viewSquare(int x, int y);
    Square viewSquare(Point location);
    
    Queue<Point> pathfind(Point start, Point goal);
    Collection<Square> generate_player_map(int PlayerID);
    
    /**
     * used strictly for new units.
     * @param x
     * @param y
     * @param unit
     * @throws MapDesync 
     */
    void addunit(int x, int y, GenericUnit unit) throws MapDesync;
        
    
    /**
     * 
     * @param playerkey - the unique key for each player.
     * @param start - starting location of the unit.   Where the unit should currently be.
     * @param unitkey - The key for the unit that should be moved.
     * @param end - the endpoint, where the unit should end up.  (if a valid location).
     * @return - the ending location of the unit for this turn.  
     * @throws MapDesync 
     */
    Point moveunit_1square(int playerkey, int unitkey, Point start, Point end) throws MapDesync;
    
}
