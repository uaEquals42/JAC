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

import jac.engine.PlayerDetails;
import jac.unit.GenericUnit;
import java.awt.geom.Point2D;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


public class SquareVisible implements Square {
    
    private final Point2D location;
    private final int altitude;
    
    private Map<Integer, Map<Integer, GenericUnit>> units;
    
    SquareVisible(Point2D location, int altitude, Collection<PlayerDetails> player_ids){
        this.location = location;
        this.altitude = altitude;
        
        // Initialize the unit lists for each player.
        this.units = new HashMap();
        for(PlayerDetails player : player_ids){
            units.put(player.getId(), new <Integer, GenericUnit>HashMap());
        }
    }
    
    @Override
    public boolean visible() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int nutirents(int sea_level) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int minerals(int sea_level) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int elevation(int sea_level) {
        return altitude - sea_level;
    }

    @Override
    public int solar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean nutrients_not_used() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean minerals_not_used() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean solar_not_used() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Map<Integer, GenericUnit> getUnits(int playerID) {
        return units.get(playerID);
    }

    @Override
    public void removeUnit(int playerID, int unitID) throws MapDesync {
        Map<Integer, GenericUnit> playerunits = units.get(playerID);

        GenericUnit test = playerunits.remove(unitID);
        if (test == null) {
            throw new MapDesync("No unit to remove: That unit wasn't here");
        }

    }

    @Override
    public void addUnit(GenericUnit unit) throws MapDesync{
        int playerId = unit.getId_player();
        int unit_id = unit.getId_unit();

        Map<Integer, GenericUnit> playerunits = units.get(playerId);

        GenericUnit test = playerunits.put(unit_id, unit);
        if (test != null) {
            throw new MapDesync("Unit was already in spot");
        }
        
    }
    
}
