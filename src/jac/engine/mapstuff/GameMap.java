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

import java.awt.geom.Point2D;
import java.util.Collection;
import java.util.Queue;

/**
 *
 * @author Gregory Jordan
 */
public interface GameMap {
    Square viewSquare(int x, int y);
    Queue<Point2D> pathfind(Point2D start, Point2D goal);
    Collection<Square> generate_player_map(int PlayerID);
    
}
