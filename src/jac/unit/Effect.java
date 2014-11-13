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

import jac.Enum.EffectScope;
import jac.engine.ruleset.Ideology;
import jac.engine.ruleset.Weapon;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Gregory Jordan
 */
public class Effect {
    
    
    
    

    /**
     *     SCOPE VAR!
     * Per square of resource gathering or just per unit?
     * Or is it one that effects all units/bases?
     */
    final EffectScope scope;
    
    
    // Optional settings.
    final List<Restriction> restrictions;
    
    
    /**
     * is it a base?  aka.  Does it have a population that can gather resources.
     * true - it has a population.
     */
    private final boolean isitabase; 
    private final boolean can_make_facilities;
    private final boolean can_make_units;
    private final boolean cant_attack;
    private final boolean cant_defend;
    private final boolean capture_when_defeated;
    private final Unit_Plan converts_to;
    
    
    public static class Builder {
        // Required
        final EffectScope scope;
        
        // Optional
        List<Restriction> restrictions = new ArrayList<>();
        private boolean isitabase=false; 
        private boolean can_make_facilities=false;
        private boolean can_make_units=false;
        private boolean cant_attack=false;
        private boolean cant_defend=false;
        private boolean capture_when_defeated=false;
        private Unit_Plan converts_to=null;
        
        public Builder(EffectScope scope){
            this.scope = scope;
        }
        
        public Builder setRestrictions(List<Restriction> restrictions){
            this.restrictions = restrictions;
            return this;
        }
        
        public Builder has_population(){
            isitabase = true;
            return this;
        }
      
        public Effect build() {
            return new Effect(this);
        }
    }

    private Effect(Builder build) {
        scope = build.scope;
        restrictions = build.restrictions;
        isitabase = build.isitabase;
        can_make_facilities = build.can_make_facilities;
        can_make_units = build.can_make_units;
        cant_attack = build.cant_attack;
        cant_defend = build.cant_defend;
        capture_when_defeated = build.capture_when_defeated;
        converts_to = build.converts_to;
    }

    public boolean available(int turn_created, int current_turn, Chassis chassis, Weapon wep, Map<String, Ideology> ideologys, int base_size, Map<String, Facility> fac) {
        if (restrictions.isEmpty()) {
            return true;
        }
        boolean tf = true;
        for (Restriction restrict : restrictions) {
            tf = tf && restrict.available(turn_created, current_turn, chassis, wep, ideologys, base_size, fac);
        }
        return tf;
    }

}
