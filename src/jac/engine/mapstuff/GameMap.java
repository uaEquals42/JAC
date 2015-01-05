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
    
    Queue<Point> pathFind(Point start, Point goal);
    Collection<Square> generatePlayerMap(int PlayerID);
    
    /**
     * used strictly for new units.
     * @param x
     * @param y
     * @param unit
     * @throws MapDesync 
     */
    void addUnit(int x, int y, GenericUnit unit) throws MapDesync;
        
    public void moveUnitTo(Square begining, GenericUnit unit, Square destination) throws MapDesync;
    

    
}
