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

import jac.Enum.Domain;
import jac.engine.ruleset.Tech;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Gregory Jordan
 */
public class Unit_Plan implements Comparable<String>{

    private final Chassis chassis;
    private final Reactor reactor;
    private final Armor armor;
    private final Weapon weapon;
    private final Map<String, UnitAbility> unit_abilities;
    private final Map<String, Facility> unit_facilities;
    
    private int max_health;
    private boolean prototyped = false;
    private int cost;

    public Unit_Plan(Chassis chas, Reactor react, Armor def, Weapon weap, Map<String, UnitAbility> abilities, Map<String, Facility> unitFacilities) {
        this.unit_abilities = abilities;
        this.unit_facilities = unitFacilities;
        chassis = chas;
        reactor = react;
        armor = def;
        weapon = weap;

        
        max_health = reactor.reactor_power() * 10;  //TODO: This is correct.  I wonder if I should have the health multiplier stored in the rules file.

        cost = calculate_cost();

    }

    public int cost_get() {
        return cost;
    }

    public int max_health(){
        
        return max_health;
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

    public Map<String, UnitAbility> getUnit_abilities() {
        return unit_abilities;
    }

    public Map<String, Facility> getUnit_facilities() {
        return unit_facilities;
    }
    
    
    
    
    // Private methods
    private int calculate_cost() {
        //http://strategywiki.org/wiki/Sid_Meier%27s_Alpha_Centauri/Units is the formula I'm using.
        int def_cost = armor.getFlatcost();
        
        if (chassis.getDomain() == Domain.SEA) {
            def_cost = def_cost / 2;
        } else if (chassis.getDomain() == Domain.AIR) {
            def_cost = def_cost * 2;
        }

        int wep_cost = def_cost;
        if (wep_cost * 2 < def_cost) {
            wep_cost = def_cost / 2;
        }
        int r = reactor.reactor_power();
        int speed = chassis.getMovementPoints();

        int runningTotal = wep_cost * (def_cost + speed) * 10 / (2 ^ (r + 1));

        if (chassis.getDomain() == Domain.SEA) {
            runningTotal = runningTotal / 2;
        } else if (chassis.getDomain() == Domain.AIR) {
            runningTotal = runningTotal / 4;
        }

        // 
        int ability_cost = 0;
        for (String key : unit_abilities.keySet()){   
            ability_cost = ability_cost + unit_abilities.get(key).calculate_cost(runningTotal, weapon, armor,chassis);
        }
        

        runningTotal = runningTotal + ability_cost;

        if (wep_cost > 1 && def_cost > 1) {
            runningTotal = runningTotal + 10;
        }
        if (wep_cost > 1 && def_cost > 1 && speed > 1) {
            runningTotal = runningTotal + 10;
        }

        //Cmin = (R × 2 − R ÷ 2) × 10
        int cmin = (r * 2 - r / 2) * 10;

        if (cmin > runningTotal) {
            return cmin;
        } else {
            return runningTotal;
        }
    }
    
    @Override
    public String toString(){
     

        
        return "Unit Plan:"+chassis.getKey()+armor.getKey()+reactor.getKey()+weapon.getKey()+unit_abilities.keySet()+unit_facilities.keySet();
    }
   
    @Override
    public int compareTo(String o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
