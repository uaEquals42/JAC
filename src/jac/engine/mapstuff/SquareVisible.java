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
import jac.engine.PlayerDetails;
import jac.unit.GenericUnit;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class SquareVisible implements Square {
    
    private final Point location;
    private final int altitude;
    private final Terrainstat basicTerrain;
    private List<Terrainstat> terrainModifiers;
    
    private Map<Integer, Map<Integer, GenericUnit>> units;
    
    SquareVisible(Builder build){
        this.location = build.location;
        this.altitude = build.altitude;
        this.basicTerrain = build.basicTerrain;
        this.terrainModifiers = build.terrainModifiers;
        
        // Initialize the unit lists for each player.
        this.units = new HashMap<>();
        for(PlayerDetails player : build.players){
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
    public void removeUnit(GenericUnit unit) throws MapDesync {
        this.removeUnit(unit.getId_player(), unit.getId_unit());
    }
    
    @Override
    public GenericUnit removeUnit(int playerID, int unitID) throws MapDesync {
        Map<Integer, GenericUnit> playerunits = units.get(playerID);

        GenericUnit test = playerunits.remove(unitID);
        if (test == null) {
            throw new MapDesync("No unit to remove: That unit wasn't here");
        }
        
        return test;

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

    @Override
    public boolean unitExists(int playerID, int unitID) {
        return units.get(playerID).containsKey(unitID);
    }

    @Override
    public GenericUnit viewUnit(int playerID, int unitID) throws MapDesync {
        GenericUnit unit = units.get(playerID).get(unitID);
        if(unit==null){
            throw new MapDesync("Unit wasn't there to view");
        }
        return unit;
    }

    @Override
    public Set<MovementType> allowedMovementTypes(int sealevel) {
        Set<MovementType> allowedtypes = new HashSet<>();
        allowedtypes.add(MovementType.AIR);
        if(elevation(sealevel)>0){
            allowedtypes.add(MovementType.LAND);
        }
        else{
            allowedtypes.add(MovementType.LAND);
        }
        return allowedtypes;
    }

    @Override
    public Terrainstat getBasicTerrain() {
        return basicTerrain;
    }

    @Override
    public Collection<Terrainstat> getTerrainModifiers() {
        return terrainModifiers;
    }



    @Override
    public int calculateMovementPointCost(GenericUnit unit) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getX() {
        return location.x;
    }

    @Override
    public int getY() {
        return location.y;
    }

 
    static class Builder {
        private final Point location;
        private final int altitude;
        private final Terrainstat basicTerrain;       
        private final Collection<PlayerDetails> players;
        
        private List<Terrainstat> terrainModifiers = new ArrayList<>();
        
        Builder(int x, int y, int absHeight, Terrainstat terrainType, Collection<PlayerDetails> players){
            this.location = new Point(x,y);
            this.altitude = absHeight;
            this.basicTerrain = terrainType;
            this.players = players;
        }
        
        Builder addTerrainModifier(Terrainstat modifier){
            terrainModifiers.add(modifier);
            return this;
        }
        
        SquareVisible build(){
            return new SquareVisible(this);
        }
    }
    
}
