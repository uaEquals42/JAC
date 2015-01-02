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

import java.awt.Point;
import java.util.Collection;

/**
 *
 * @author Gregory Jordan
 */
public class UnitMoveUpdate {
    private final int playerid;
    private final int unitid;
    private final Point location;
    private final Collection<Square> updatedsquares;
    
    
    UnitMoveUpdate(int playerid, int unitid, int x, int y, Collection<Square> updatedsquares){
        this.playerid = playerid;
        this.unitid = unitid;
        location = new Point(x,y);
        this.updatedsquares = updatedsquares;
    }
    
}
