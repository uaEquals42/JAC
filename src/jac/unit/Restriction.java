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

import jac.Enum.CombatMode;
import jac.engine.ruleset.Ideology;
import jac.Enum.MovementType;
import jac.engine.ruleset.Weapon;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Gregory Jordan
 */
public class Restriction {
    /**
     * A list of chassis keys that are allowed.
     */
    List<String> allowed_chassis;
    /**
     * Allowed movement types.  Air/Land/Sea.  If null, then all are allowed.  
     */
    List<MovementType> allowedTypes;
    
    /**
     * A list of allowable roletypes for the effect.  EX: Combat, Terraformer, Probe.
     * If null, then all are allowed.
     */
    List<CombatMode> allowedRoles;
    
    /**
     * How many turns after the unit/facility has been built that this effect is effective for. 
     * Requires the current turn and the built date being passed in to calculate if it is effect.
     */
    Integer x_turns;  
            
    String required_ideology;
    
    Integer base_bigger_than;
    Integer base_smaller_than;
    String required_facility;
    Integer max_speed_allowed;
    
    public static class Builder {
        // Required

        // Optional
        List<String> allowed_chassis=new ArrayList<>();
        List<MovementType> allowedTypes=new ArrayList<>();
        List<CombatMode> allowedRoles=new ArrayList<>();
        
        Integer x_turns=null;
        Integer max_speed_allowed=null;
        Integer base_bigger_than=null;
        Integer base_smaller_than=null;
        
        String required_ideology="";
        String required_facility="";
        
        
        
        
        public Restriction build() {
            return new Restriction(this);
        }
    }
    
    private Restriction(Builder build){
        this.allowed_chassis = build.allowed_chassis;
        this.allowedTypes = build.allowedTypes;
        this.allowedRoles = build.allowedRoles;
        
        this.x_turns = build.x_turns;
        this.max_speed_allowed = build.max_speed_allowed;
        this.base_bigger_than = build.base_bigger_than;
        this.base_smaller_than = build.base_smaller_than;
        
        this.required_ideology = build.required_ideology;
        this.required_facility = build.required_facility;
    }
      
    /**
     * 
     * @param turn_created
     * @param current_turn
     * @param chassis
     * @param wep
     * @param ideologys
     * @param base_size
     * @param fac
     * @return 
     */
    public boolean available(int turn_created, int current_turn, Chassis chassis, Weapon wep, Map<String, Ideology> ideologys, int base_size, Map<String, Facility> fac){
        boolean result = true;
        
        result = result && x_turns <= current_turn - turn_created;
        result = result && allowed_chassis.contains(chassis.key());
        
 
        result = result && ideologys.containsKey(required_ideology);
      
        
        if(base_bigger_than!=null){
            result = result && base_bigger_than>base_size;
        }
        
        if(base_smaller_than!=null){
            result = result && base_smaller_than<base_size;
        }
   
        if (!required_facility.isEmpty()) {
            result = result && fac.containsKey(required_facility);
        }

        if (!allowedTypes.isEmpty()) {
            result = result && allowedTypes.contains(chassis.getTriad());
        }

        if (!allowedRoles.isEmpty()) {
            result = result && allowedRoles.contains(wep.getCom_mode());
        }
        
        if (max_speed_allowed != null){
            result = result && chassis.getSpeed() <= max_speed_allowed;
        }
        
        return result;
    }

}
