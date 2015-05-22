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
package jac.unit;


import jac.Enum.UnitActions;
import jac.engine.mapstuff.Square;
import jac.engine.PlayerDetails;
import jac.engine.mapstuff.CantMoveUnitThereException;
import jac.engine.mapstuff.GameMap;
import jac.engine.mapstuff.MapDesync;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


/**
 *
 * @author Gregory Jordan
 */
public class GenericUnit {

    private final int construction_date;

    private final int id_unit;
    private final PlayerDetails player;

    private int current_health;
    private Integer population;

    private final Unit_Plan design;

    private final Effect localEffects;
    private final Effect empireEffects;

    private Map<String, UnitAbility> unit_abilities;
    private Map<String, Facility> unit_facilities;

    private int movementPoints;
    private boolean on_hold;
    private MoveTask nextmove;

    private Square location;

    public GenericUnit(Unit_Plan design, int turn, PlayerDetails player, int id, Square location) {
        construction_date = turn;

        this.location = location;
        this.design = design;
        unit_abilities = design.getUnitAbilities();
        unit_facilities = new HashMap<>(design.getUnitFacilities());

        this.id_unit = id;
        this.player = player;

        Effect tmp = design.getArmor().getLocalEffects();
        localEffects = null; //TODO have this work!
        empireEffects = null;
        Integer population;

    }

    public void setHealthToMax() {
        current_health = getMax_health();
    }

    public boolean canUnitMoveTo(Square destination, int seaLevel) {
        //TODO:  Add exceptions here for amphibious units.  Transports, etc.
        //
        return destination.allowedDomains(seaLevel).contains(design.getChassis().getDomain());
    }

    public void set_movement_goal(Square destination, int seaLevel) throws CantMoveUnitThereException {
        if (canUnitMoveTo(destination, seaLevel)) {
            //TODO: Need to set up pathfinding and stuff here.
            nextmove = new MoveTask(destination, this);
        } else {
            throw new CantMoveUnitThereException();
        }
    }

    public List<Square> performActions(GameMap map) throws MapDesync {
        List<Square> locations = new LinkedList<>();
        locations.add(location);
        if (whatIsUnitDoing() == UnitActions.MOVING) {
            movementPoints = nextmove.workOnTask(movementPoints);
            if (nextmove.finished()) {
                map.moveUnitTo(location, this, nextmove.getDestination());
                location = nextmove.getDestination();
                locations.add(location);
                //TODO:  Have it go to the next node in the pathfinding algorithm.
                nextmove = null;
            }
        }
        return locations;
    }

    public void startOfTurn(GameMap map, int turn) throws MapDesync {
        resetMovementPoints();
        performActions(map);
    }

    public UnitActions whatIsUnitDoing() {
        if (nextmove != null) {
            return UnitActions.MOVING;
        }
        return UnitActions.WAITING;
    }

    public void resetMovementPoints() {
        movementPoints = calculateMaxMovementPoints();
    }

    int calculateMaxMovementPoints() { 
        int speed = localEffects.getSpeedBoost().result(this, player) + this.getEmpireEffects().getSpeedBoost().result(this, player);
        return speed;
    }

 

    public boolean isitabase() {
        return localEffects.getIsitabase().result(this, player) || this.getEmpireEffects().getIsitabase().result(this, player);
    }

    public int getSensorRange() {
        return 1;  // TODO: Have this be calculated based on rules and config options.
    }

    public int getConstruction_date() {
        return construction_date;
    }

    public int getMax_health() {
        return localEffects.getHealth().result(this, player) + getEmpireEffects().getHealth().result(this, player);
    }

    /**
     * Use this class so that optimizing latter will be allot easier. Plan to
     * cache the results, so that if the empire effects doesn't change, it will
     * just return the result.
     *
     * @return
     */
    private Effect getEmpireEffects() {
        //TODO: Low priority:  Make empireeffects results be cached.
        return player.getEmpireEffects();
    }

    public int getCurrent_health() {
        return current_health;
    }

    public Chassis getChassis() {
        return design.getChassis();
    }

    public Reactor getReactor() {
        return design.getReactor();
    }

    public Armor getArmor() {
        return design.getArmor();
    }

    public Weapon getWeapon() {
        return design.getWeapon();
    }

    public int getId_player() {
        return player.getId();
    }

    public Map<String, UnitAbility> getUnit_abilities() {
        return unit_abilities;
    }

    public Map<String, Facility> getUnit_facilities() {
        return unit_facilities;
    }

    public Integer getPopulation() {
        return population;
    }

    public int getId_unit() {
        return id_unit;
    }

}
