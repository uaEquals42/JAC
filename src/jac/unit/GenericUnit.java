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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Gregory Jordan
 */
public class GenericUnit {
    private final int construction_date;
    private final int max_health;
    
    private int current_health;
    
    private Chassis chassis;
    private Reactor reactor;
    private Armor armor;
    private Weapon weapon;
    
    private Map<String, UnitAbility> unit_abilities;
    private Map<String, Facility> unit_facilities;
    
    private Integer population;
    
    public GenericUnit(Unit_Plan design, int turn){
        construction_date = turn;
        max_health = design.max_health();
        current_health = max_health;
        
        chassis = design.getChassis();
        reactor = design.getReactor();
        armor = design.getArmor();
        weapon = design.getWeapon();
        unit_abilities = design.getUnit_abilities();
        unit_facilities = new HashMap<>(design.getUnit_facilities());
        
      Integer population;
        
    }

    public List<Effect> activeEffects(int turn, Map<String, Ideology> current_ideologies){
        int lifespan = turn - construction_date;
        ArrayList<Effect> effects = new ArrayList<>();
        for(UnitAbility ability: unit_abilities.values()){
            effects.addAll(ability.active_effects(lifespan, this, current_ideologies));
        }
        
        
        
        return effects;
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

    public Map<String, UnitAbility> getUnit_abilities() {
        return unit_abilities;
    }

    public Map<String, Facility> getUnit_facilities() {
        return unit_facilities;
    }

    public Integer getPopulation() {
        return population;
    }
    
    
    
}
