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

import jac.Enum.BoolNames;
import jac.Enum.IntNames;
import jac.Enum.UnitActions;
import jac.engine.PlayerDetails;
import jac.engine.mapstuff.CantMoveUnitThereException;
import jac.engine.mapstuff.GameMap;
import jac.engine.mapstuff.MapDesync;
import jac.engine.mapstuff.Square;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Gregory Jordan
 */
public class GenericUnit implements Unit{

    private final int construction_date;

    private final int id_unit;
    private final PlayerDetails player;

    private int current_health;
    private Integer population;

    private final Unit_Plan design;

    private final List<Effect> localEffects;
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
        localEffects = design.getEffects(); //TODO have this work!
        empireEffects = null;
        Integer population;

    }

    public void setHealthToMax() {
        current_health = calculateInteger(IntNames.HEALTH);
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
        movementPoints = calculateInteger(IntNames.SPEED_BOOST);
    }

    int calculateInteger(IntNames name) {
        int value = 0;
        float multiplier = 1;
        for (Effect eff : localEffects) {
            value = value + eff.getIntValue(name, this);
            multiplier = multiplier * eff.getFloatValue(name, this);
        }

        // TODO:  Do the same for the faction wide effects (Tech, Rules, Faction Settings, Faction Rules, Secret Projects)
        return (int) (value * multiplier);
    }

    boolean calculateBool(BoolNames name) {
        for (Effect eff : localEffects) {
            if (eff.getBoolValue(name, this)) {
                return true;
            }
        }

        // TODO:  Do the same for the faction wide effects (Tech, Rules, Faction Settings, Faction Rules, Secret Projects)
        return false;

    }


    public int getSensorRange() {
        return 1;  // TODO: Have this be calculated based on rules and config options.
    }

    public int getConstruction_date() {
        return construction_date;
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

    @Override
    public Chassis getChassis() {
        return design.getChassis();
    }

    @Override
    public Reactor getReactor() {
        return design.getReactor();
    }

    @Override
    public Armor getArmor() {
        return design.getArmor();
    }

    @Override
    public Weapon getWeapon() {
        return design.getWeapon();
    }


    
@Override
    public Map<String, UnitAbility> getUnitAbilities() {
        return unit_abilities;
    }

    @Override
    public Map<String, Facility> getUnitFacilities() {
        return unit_facilities;
    }

    public int getId_player() {
        return player.getId();
    }
    
    public Integer getPopulation() {
        return population;
    }

    public int getId_unit() {
        return id_unit;
    }

    public boolean isitabase() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
