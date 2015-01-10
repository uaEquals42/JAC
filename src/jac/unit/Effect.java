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
    
    

    
    
    /**
     * is it a base?  aka.  Does it have a population that can gather resources.
     * true - it has a population.
     */
    private final EffectNode<Boolean> isitabase; 
    private final EffectNode<Boolean> can_make_facilities;
    private final EffectNode<Boolean> can_make_units;
    private final EffectNode<Boolean> cant_attack;
    private final EffectNode<Boolean> cant_defend;
    private final EffectNode<Boolean> capture_when_defeated;   
    private final EffectNode<Boolean> amphibious;
    
    private final List<UnitCoversion> converts_to;  
    
    private final EffectNode<Integer> speedBoost;
    private final EffectNode<Integer> health;
    private final EffectNode<Integer> cost;
     
    // Resources Effects
    private final EffectNode<Integer> unitMineralProduction;   
    private final EffectNode<Integer> workerMineralProduction; // How much minerals each square produces.

    
    
    
    public EffectNode<Boolean> getIsitabase() {
        return isitabase;
    }

    public EffectNode<Boolean> getCan_make_facilities() {
        return can_make_facilities;
    }

    public EffectNode<Boolean> getCan_make_units() {
        return can_make_units;
    }

    public EffectNode<Boolean> getCant_attack() {
        return cant_attack;
    }

    public EffectNode<Boolean> getCant_defend() {
        return cant_defend;
    }

    public EffectNode<Boolean> getCapture_when_defeated() {
        return capture_when_defeated;
    }

    public List<UnitCoversion> getConverts_to() {
        return converts_to;
    }

    public EffectNode<Integer> getSpeedBoost() {
        return speedBoost;
    }

    public EffectNode<Boolean> getAmphibious() {
        return amphibious;
    }

    public EffectNode<Integer> getUnitMineralProduction() {
        return unitMineralProduction;
    }

    public EffectNode<Integer> getWorkerMineralProduction() {
        return workerMineralProduction;
    }

    public EffectNode<Integer> getHealth() {
        return health;
    }

    public EffectNode<Integer> getCost() {
        return cost;
    }
    
    
    
    
    public static class Builder {
        
        
        private EffectNode<Boolean> isitabase = Value.False();
        private EffectNode<Boolean> can_make_facilities = Value.False();
        private EffectNode<Boolean> can_make_units = Value.False();
        private EffectNode<Boolean> cant_attack = Value.False();
        private EffectNode<Boolean> cant_defend = Value.False();
        private EffectNode<Boolean> capture_when_defeated = Value.False();

        private List<UnitCoversion> converts_to = new ArrayList<>();

        private EffectNode<Integer> speed_boost  = Value.zero();

        private EffectNode<Boolean> amphibious = Value.False();
        
        // Resources Effects
        private EffectNode<Integer> unitMineralProduction = Value.zero();

        private EffectNode<Integer> workerMineralProduction = Value.zero(); // How much minerals each square produces.
        EffectNode<Integer> health = Value.zero();
        EffectNode<Integer> cost = Value.zero();


        
        
        
        public Builder makeItABase() {
            isitabase = Value.True();
            can_make_facilities = Value.True();
            can_make_units = Value.True();
            cant_attack = Value.True();
            cant_defend = Value.True();
            capture_when_defeated = Value.True();
            return this;
        }
        
        public Builder costOverride(EffectNode<Integer> value){
            if(value!=null){
                cost = value;
            }
            return this;
        }
        
        
        public Builder amphibious(EffectNode<Boolean> choice){
            amphibious = choice;
            return this;
        }
        

        public Builder setSpeed_boost(EffectNode<Integer> speed_boost) {
            this.speed_boost = speed_boost;
            return this;
        }
        
        
        public Builder combineEffects(Effect effect1, Effect effect2, Effect effect3, Effect effect4){
            List<Effect> effects = new ArrayList<>();
            effects.add(effect1);
            effects.add(effect2);
            effects.add(effect3);
            effects.add(effect4);
            return combineEffects(effects);
        }
        
        
        public Builder combineEffects(List<Effect> effects){
            
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
        this.health = build.health;
        this.cost = build.cost;
    }

   

}
