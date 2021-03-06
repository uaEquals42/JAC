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

import jac.Enum.Domain;
import jac.unit.GenericUnit;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Gregory Jordan
 */
public class AgeSquare implements Square{

    private final int created_on_turn;
    private final Square square;
    
    AgeSquare(int turn, Square square){
        created_on_turn = turn;
        this.square = square;
    }
    
    public int age(int turn){
        return turn - created_on_turn;
    }
    
    @Override
    public boolean visible() {
        return square.visible();
    }

    @Override
    public int nutirents(int sea_level) {
        return square.nutirents(sea_level);
    }

    @Override
    public int minerals(int sea_level) {
        return square.minerals(sea_level);
    }

    @Override
    public int elevation(int sea_level) {
        return square.elevation(sea_level);
    }

    @Override
    public int solar() {
        return square.solar();
    }

    @Override
    public boolean nutrients_not_used() {
        return square.nutrients_not_used();
    }

    @Override
    public boolean minerals_not_used() {
        return square.minerals_not_used();
    }

    @Override
    public boolean solar_not_used() {
        return square.solar_not_used();
    }

    @Override
    public Map<Integer, GenericUnit> getUnits(int playerID) {
       return square.getUnits(playerID);
    }


    @Override
    public void addUnit(GenericUnit unit) throws MapDesync{
        square.addUnit(unit);
    }

    @Override
    public GenericUnit removeUnit(int playerID, int unitID) throws MapDesync {
        return square.removeUnit(playerID, unitID);
    }

    @Override
    public boolean unitExists(int playerID, int unitID) {
       return square.unitExists(playerID, unitID);
    }

    @Override
    public GenericUnit viewUnit(int playerID, int unitID) throws MapDesync {
        return square.viewUnit(playerID, unitID);
    }

    @Override
    public Set<Domain> allowedDomains(int sealevel) {
        return square.allowedDomains(sealevel);
    }

    @Override
    public Terrainstat getBasicTerrain() {
        return square.getBasicTerrain();
    }

    @Override
    public Collection<Terrainstat> getTerrainModifiers() {
        return square.getTerrainModifiers();
    }

    @Override
    public void removeUnit(GenericUnit unit) throws MapDesync {
        square.removeUnit(unit);
    }

    @Override
    public int calculateMovementPointCost(GenericUnit unit) {
        return square.calculateMovementPointCost(unit);
    }

    @Override
    public int getX() {
        return square.getX();
    }

    @Override
    public int getY() {
        return square.getY();
    }
    
}
