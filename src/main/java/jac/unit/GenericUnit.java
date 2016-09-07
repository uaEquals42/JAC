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
import jac.engine.mapstuff.GameMap;
import jac.engine.mapstuff.MapDesync;
import jac.engine.mapstuff.Square;
import jac.engine.ruleset.Ruleset;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

    private final List<Effect> localEffects=new LinkedList<>();
    private final List<Effect> empireEffects=new LinkedList<>();

    private Map<String, Unit_part> parts;
    

    private int movementPoints;
    private boolean on_hold;
    private MoveTask nextmove;

    private Square location;
    private Ruleset rules;

    
    public GenericUnit(Unit_Plan design, int turn, PlayerDetails player, int id, Square location, Ruleset rules) {
        construction_date = turn;
        this.rules = rules;
        this.location = location;
        this.design = design;
        
        for(String key : design.getParts()){
            parts.put(key, rules.getUnit_components().get(key));
        }
        
        

        this.id_unit = id;
        this.player = player;


        for(Unit_part tmp : parts.values()){
            localEffects.add(tmp.getLocalEffects());
            empireEffects.add(tmp.getEmpireEffects());
        }


    }


    public List<Effect> getLocalEffects(Ruleset Rules) {
        return localEffects;
    }

    public void setHealthToMax() {
        current_health = calculateInteger(IntNames.HEALTH);
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
        movementPoints = calculateInteger(IntNames.SPEED);
    }


    public int getConstruction_date() {
        return construction_date;
    }

    public int calculateInteger(IntNames name) {
        // TODO: Also have it calculate the effects of the square it is on... (if any).
        int value = 0;
        float multiplier = 1;
        for (Effect eff : getLocalEffects(rules)) {
            value = value + eff.getIntVariable(name);
            multiplier = multiplier * eff.getFloatValue(name);
        }

        // TODO:  Do the same for the faction wide effects (Tech, Rules, Faction Settings, Faction Rules, Secret Projects)
        return (int) (value * multiplier);
    }
    
    public boolean calculateBool(BoolNames name) {
        for (Effect eff : getLocalEffects(rules)) {
            if (eff.getBoolVariable(name)) {
                return true;
            }
        }

        // TODO:  Do the same for the faction wide effects (Tech, Rules, Faction Settings, Faction Rules, Secret Projects)
        return false;

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



    public int getId_player() {
        return player.getId();
    }

    public Integer getPopulation() {
        return population;
    }

    public int getId_unit() {
        return id_unit;
    }

}
