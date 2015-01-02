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

import jac.Enum.MovementType;
import jac.unit.GenericUnit;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Gregory Jordan
 */
 interface Square {
    
    boolean visible();
    
    Terrainstat getBasicTerrain();
    Collection<Terrainstat> getTerrainModifiers();
    
    int nutirents(int sea_level);
    int minerals(int sea_level);
    /**
     * Gives the altitude of the square.
     * @param sea_level - The current level of the sea.
     * @return Elevation above sea level.
     */
    int elevation(int sea_level);
    int solar();
    
    /**
     * Is a base or convoy using the nutrients of this square?
     * @return 
     */
    boolean nutrients_not_used();
    
    
    boolean minerals_not_used();
    boolean solar_not_used();
    
    
    /**
     * Return units.
     * @param playerID - a filter on which units you want to receive.
     * @return Return empty if there are no units by that player in this square.
     */
    Map<Integer, GenericUnit> getUnits(int playerID);
    
    GenericUnit removeUnit(int playerID, int unitID) throws MapDesync;
    
    void addUnit(GenericUnit unit) throws MapDesync;
    
    boolean unit_exists(int playerID, int unitID);
    
    GenericUnit viewUnit(int playerID, int unitID) throws MapDesync;
    
    Set<MovementType> allowedMovementTypes(int sealevel);
}
