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

import jac.Enum.MovementType;
import jac.engine.PlayerDetails;
import jac.engine.mapstuff.MoveTask;
import jac.engine.ruleset.Ideology;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Gregory Jordan
 */
public class GenericUnit {
    private final int construction_date;
    private final int max_health;
    private final int id_unit;
    private final PlayerDetails player;
    
    private int current_health;
    private Integer population;
    
     private Chassis chassis;
    private Reactor reactor;
    private Armor armor;
    private Weapon weapon;
    
    private Map<String, UnitAbility> unit_abilities;
    private Map<String, Facility> unit_facilities;
    
    private int movement_points;
    private boolean on_hold;
    private MoveTask nextmove;
    
    
    public GenericUnit(Unit_Plan design, int turn, PlayerDetails player, int id, PlayerDetails PlayerDetails){
        construction_date = turn;
        max_health = design.max_health();
        current_health = max_health;
        
        chassis = design.getChassis();
        reactor = design.getReactor();
        armor = design.getArmor();
        weapon = design.getWeapon();
        unit_abilities = design.getUnit_abilities();
        unit_facilities = new HashMap<>(design.getUnit_facilities());
        
        this.id_unit = id;
        this.player = PlayerDetails;
        
      Integer population;
        
    }

    void set_movement_goal(MoveTask moveit){
        
    }
    
    /**
     * Check to see if the unit has the required amount of movement points to complete the action
     * And reduce stuff accordingly.
     * @param ammount
     * @return remainder.
     */
    int useMovementPoints(int ammount){
        if(movement_points >= ammount){
            movement_points = movement_points - ammount;
            ammount = 0;
        }
        else{
            ammount = ammount - movement_points;
            movement_points = 0;
        }
        return ammount;
    }
    
    public void resetMovementPoints(int turn){
        movement_points = calculateMaxMovementPoints(turn);
    }
    
    int calculateMaxMovementPoints(int turn){
        int speed = 0;
        speed = chassis.getMovementPoints();
        
        for(Effect effect : activeEffects(turn)){
            speed = speed + effect.getSpeed_boost();
        }
        return speed;
    }
    
    /**
     * List of effects that are active upon this unit.  Where all the pre requisites for the effects are met.
     * @param turn
     * @param player
     * @return 
     */
    public List<Effect> activeEffects(int turn){
        int lifespan = turn - construction_date;
        ArrayList<Effect> effects = new ArrayList<>();
        for(UnitAbility ability: unit_abilities.values()){
            effects.addAll(ability.active_effects(lifespan, this, player));
        }
        
        // TODO: Check and add in effects that apply for all units in the faction.
        
        return effects;
    }
    
    
   
    public boolean isitabase(int turn){
               
        for(Effect effect : activeEffects(turn)){
            if(effect.isIsitabase()){
                return true;
            }
        }
        return false;
        
    }
    public int getSensorRange(){
        return 1;  // TODO: Have this be calculated based on rules and config options.
    }
    
    public int getConstruction_date() {
        return construction_date;
    }

    public int getMax_health() {
        return max_health;
    }

    public int getCurrent_health() {
        return current_health;
    }

    public Chassis getChassis() {
        return chassis;
    }

    public Reactor getReactor() {
        return reactor;
    }

    public Armor getArmor() {
        return armor;
    }

    public Weapon getWeapon() {
        return weapon;
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
