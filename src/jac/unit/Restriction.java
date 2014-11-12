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

import jac.engine.ruleset.Ideology;
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
     * How many turns after the unit/facility has been built that this effect is effective for. 
     * Requires the current turn and the built date being passed in to calculate if it is effect.
     */
    Integer x_turns;  
            
    String required_ideology;
    
    Integer base_bigger_than;
    Integer base_smaller_than;
    String required_facility;
      
    
    public boolean unrestricted(int turn_created, int current_turn, Chassis chassis, Map<String, Ideology> ideologys, int base_size, Map<String, Facility> fac){
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
        
        result = result && fac.containsKey(required_facility);
        
        
        return result;
    }
    
}
