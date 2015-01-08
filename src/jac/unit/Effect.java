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


import jac.unit.effectRules.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;



/**
 *
 * @author Gregory Jordan
 */
public class Effect {
    
    
    public static Effect combine(List<Effect> effects) {
        List<EffectValue<Boolean>> isitabase = new LinkedList<>();
        List<EffectValue<Boolean>> can_make_facilities = new LinkedList<>();
        List<EffectValue<Boolean>> can_make_units = new LinkedList<>();
        List<EffectValue<Boolean>> cant_attack = new LinkedList<>();
        List<EffectValue<Boolean>> cant_defend = new LinkedList<>();
        List<EffectValue<Boolean>> capture_when_defeated = new LinkedList<>();
        List<EffectValue<Boolean>> amphibious = new LinkedList<>();
        
        List<Map<String, ? extends EffectValue<Unit_Plan>>> converts_to = new LinkedList<>();  // Key binding, unit plan
        List<EffectValue<Integer>> speedBoost = new LinkedList<>();
        

        // Resources Effects
        List<EffectValue<Integer>> unitMineralProduction = new LinkedList<>();
        List<EffectValue<Integer>> workerMineralProduction = new LinkedList<>(); // How much minerals each square produces.
        
        for(Effect effect : effects){
            isitabase.add(effect.isitabase);
            can_make_facilities.add(effect.can_make_facilities);
            can_make_units.add(effect.can_make_units);
            cant_attack.add(effect.cant_attack);
            cant_defend.add(effect.cant_defend);
            capture_when_defeated.add(effect.capture_when_defeated);
            converts_to.add(effect.converts_to);
            speedBoost.add(effect.speedBoost);
            amphibious.add(effect.amphibious);
            unitMineralProduction.add(effect.unitMineralProduction);
            workerMineralProduction.add(effect.workerMineralProduction);
        }
        //TODO: Get this to do the same for all the rest.
        return new Effect.Builder().setSpeed_boost(new AddValues(speedBoost)).build();
    }

    private Effect(EffectValue<Boolean> isitabase, 
            EffectValue<Boolean> can_make_facilities, 
            EffectValue<Boolean> can_make_units, 
            EffectValue<Boolean> cant_attack, 
            EffectValue<Boolean> cant_defend, 
            EffectValue<Boolean> capture_when_defeated, 
            EffectValue<Boolean> amphibious, 
            
            Map<String, EffectValue<Unit_Plan>> converts_to, 
            EffectValue<Integer> speedBoost, 
            
            EffectValue<Integer> unitMineralProduction, 
            EffectValue<Integer> workerMineralProduction) {
        this.isitabase = isitabase;
        this.can_make_facilities = can_make_facilities;
        this.can_make_units = can_make_units;
        this.cant_attack = cant_attack;
        this.cant_defend = cant_defend;
        this.capture_when_defeated = capture_when_defeated;
        this.converts_to = converts_to;
        this.speedBoost = speedBoost;
        this.amphibious = amphibious;
        this.unitMineralProduction = unitMineralProduction;
        this.workerMineralProduction = workerMineralProduction;
    }
    
    
    /**
     * is it a base?  aka.  Does it have a population that can gather resources.
     * true - it has a population.
     */
    private final EffectValue<Boolean> isitabase; 
    private final EffectValue<Boolean> can_make_facilities;
    private final EffectValue<Boolean> can_make_units;
    private final EffectValue<Boolean> cant_attack;
    private final EffectValue<Boolean> cant_defend;
    private final EffectValue<Boolean> capture_when_defeated;
    
    private final EffectValue<Boolean> amphibious;
    
    private final Map<String, ? extends EffectValue<Unit_Plan>> converts_to;  // Key binding, unit plan
    private final EffectValue<Integer> speedBoost;
    
    
    
    // Resources Effects
    private final EffectValue<Integer> unitMineralProduction;   
    private final EffectValue<Integer> workerMineralProduction; // How much minerals each square produces.

    
    
    
    public EffectValue<Boolean> getIsitabase() {
        return isitabase;
    }

    public EffectValue<Boolean> getCan_make_facilities() {
        return can_make_facilities;
    }

    public EffectValue<Boolean> getCan_make_units() {
        return can_make_units;
    }

    public EffectValue<Boolean> getCant_attack() {
        return cant_attack;
    }

    public EffectValue<Boolean> getCant_defend() {
        return cant_defend;
    }

    public EffectValue<Boolean> getCapture_when_defeated() {
        return capture_when_defeated;
    }

    public Map<String, ? extends EffectValue<Unit_Plan>> getConverts_to() {
        return converts_to;
    }

    public EffectValue<Integer> getSpeedBoost() {
        return speedBoost;
    }

    public EffectValue<Boolean> getAmphibious() {
        return amphibious;
    }

    public EffectValue<Integer> getUnitMineralProduction() {
        return unitMineralProduction;
    }

    public EffectValue<Integer> getWorkerMineralProduction() {
        return workerMineralProduction;
    }
    
    
    
    
    public static class Builder {
        
        
        private EffectValue<Boolean> isitabase = Value.False();
        private EffectValue<Boolean> can_make_facilities = Value.False();
        private EffectValue<Boolean> can_make_units = Value.False();
        private EffectValue<Boolean> cant_attack = Value.False();
        private EffectValue<Boolean> cant_defend = Value.False();
        private EffectValue<Boolean> capture_when_defeated = Value.False();

        private Map<String, ? extends EffectValue<Unit_Plan>> converts_to = new HashMap<>();

        private EffectValue<Integer> speed_boost  = Value.zero();

        private EffectValue<Boolean> amphibious = Value.False();
        
        // Resources Effects
        private EffectValue<Integer> unitMineralProduction = Value.zero();

        private EffectValue<Integer> workerMineralProduction = Value.zero(); // How much minerals each square produces.



        
        
        
        public Builder makeItABase() {
            isitabase = Value.True();
            can_make_facilities = Value.True();
            can_make_units = Value.True();
            cant_attack = Value.True();
            cant_defend = Value.True();
            capture_when_defeated = Value.True();
            return this;
        }
        
        public Builder amphibious(EffectValue<Boolean> choice){
            amphibious = choice;
            return this;
        }
        

        public Builder setSpeed_boost(EffectValue<Integer> speed_boost) {
            this.speed_boost = speed_boost;
            return this;
        }
        
        
      
        public Effect build() {
            return new Effect(this);
        }
    }

    private Effect(Builder build) {
        isitabase = build.isitabase;
        can_make_facilities = build.can_make_facilities;
        can_make_units = build.can_make_units;
        cant_attack = build.cant_attack;
        cant_defend = build.cant_defend;
        capture_when_defeated = build.capture_when_defeated;
        converts_to = build.converts_to;
        amphibious = build.amphibious;
        this.speedBoost = build.speed_boost;
        this.unitMineralProduction = build.unitMineralProduction;
        this.workerMineralProduction = build.workerMineralProduction;
    }

   

}
