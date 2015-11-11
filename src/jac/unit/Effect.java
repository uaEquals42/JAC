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


import jac.engine.PlayerDetails;
import jac.unit.effectRules.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 *
 * @author Gregory Jordan
 */
public class Effect {
    
    
    static Logger log = LoggerFactory.getLogger(Effect.class);
    
    
    /**
     * is it a base?  aka.  Does it have a population that can gather resources.
     * true - it has a population.
     */
    private final EffectNode<Boolean> isitabase; 
    private final EffectNode<Boolean> can_make_facilities;
    private final EffectNode<Boolean> can_make_units;
    private final EffectNode<Boolean> can_attack;
    private final EffectNode<Boolean> can_defend;
    private final EffectNode<Boolean> capture_when_defeated;   
    private final EffectNode<Boolean> amphibious;
    
    private final List<UnitCoversion> converts_to;  
    
    private final EffectNode<Integer> speedBoost;
    private final EffectNode<Integer> health;
    private final EffectNode<Integer> cost;
    private final EffectNode<Integer> cargoCapacity;
     
    // Resources Effects
    private final EffectNode<Integer> unitMineralProduction;   
    private final EffectNode<Integer> workerMineralProduction; // How much minerals each square produces.

    
    private Effect(Builder build) {
        isitabase = build.isitabase;
        can_make_facilities = build.can_make_facilities;
        can_make_units = build.can_make_units;
        can_attack = build.can_attack;
        can_defend = build.can_defend;
        capture_when_defeated = build.capture_when_defeated;
        converts_to = build.converts_to;
        amphibious = build.amphibious;
        this.speedBoost = build.speed_boost;
        this.unitMineralProduction = build.unitMineralProduction;
        this.workerMineralProduction = build.workerMineralProduction;
        this.health = build.health;
        this.cost = build.cost;
        this.cargoCapacity = build.cargoCapacity;
    }
    
    public EffectNode<Boolean> getIsitabase() {
        return isitabase;
    }

    public EffectNode<Boolean> getCan_make_facilities() {
        return can_make_facilities;
    }

    public EffectNode<Boolean> getCan_make_units() {
        return can_make_units;
    }

    public EffectNode<Boolean> getCan_attack() {
        return can_attack;
    }

    public EffectNode<Boolean> getCan_defend() {
        return can_defend;
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

    public EffectNode<Integer> getCargoCapacity() {
        return cargoCapacity;
    }
    
    
    
    
    public static class Builder {
        
        
        private EffectNode<Boolean> isitabase = Value.False();
        private EffectNode<Boolean> can_make_facilities = Value.False();
        private EffectNode<Boolean> can_make_units = Value.False();
        private EffectNode<Boolean> can_attack = Value.True();
        private EffectNode<Boolean> can_defend = Value.True();
        private EffectNode<Boolean> capture_when_defeated = Value.False();

        private List<UnitCoversion> converts_to = new ArrayList<>();

        private EffectNode<Integer> speed_boost  = Value.zero();
        private EffectNode<Integer> cargoCapacity = Value.zero();

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
            can_attack = Value.True();
            can_defend = Value.True();
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
        
        public Builder setCorgoCapacity(EffectNode<Integer> cargoCapacity){
            this.cargoCapacity = cargoCapacity;
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
      
        
        
    /*
    private PlayerNode<Integer> convert_cost_code(int cost_code, PlayerDetails player){
        int value = 0;
        if (cost_code > 0) {
            value = cost_code;
        }
        int weaponcost = weapon.getFlatcost();
        int armor_cost = armor.getFlatcost();
        int chassis_cost = chassis.getFlatcost();
        switch (cost_code) {
            case 0:
                return super.getFlatcost();
            case -1:
                value = weaponcost / armor_cost;
                break;
            case -2:
                value = weaponcost - 1;
                break;
            case -3:
                value = armor_cost - 1;
                break;
            case -4:
                value = chassis_cost - 1;
                break;
            case -5:
                value = weaponcost + armor_cost - 2;
                break;
            case -6:
                value = weaponcost + chassis_cost - 2;
                break;
            case -7:
                value = armor_cost + chassis_cost - 2;
                break;
            default:
                log.error("Reached end of switch statement.  Invalid number supplied.");
                // This could should never be reached.  Its mostly here in cose someone accidently removes a check someplace.
                throw new IllegalArgumentException("Reached end of switch statement.  Invalid number supplied.");
                
        }
        return value;
    }
    */
        
    public int calculate_cost(int base_cost, Weapon weapon, Armor armor, Chassis chassis) {

        /*
         ; Special Unit Abilities

         ; Cost   = Cost factor of ability
         ;          1+ = Straight Cost; 25% increase per unit of cost
         ;           0 = None
         ;          -1 = Increases w/ ratio of weapon to armor: 0, 1, or 2.
         ;               Rounded DOWN. Never higher than 2.
         ;               Examples: For a W1,A2 unit, cost is 0
         ;                         For a W3,A2 unit, cost is 1 (3/2 rounded down)
         ;                         For a W6,A3 unit, cost is 2
         ;          -2 = Increases w/ weapon value
         ;          -3 = Increases w/ armor value
         ;          -4 = Increases w/ speed value
         ;          -5 = Increases w/ weapon+armor value
         ;          -6 = Increases w/ weapon+speed value
         ;          -7 = Increases w/ armor+speed value
         */
        // http://alphacentauri2.info/index.php?topic=12897.msg61597#msg61597
        /*
         Acording to Yitzi
         Ok (weapon, armor, and chassis all refer to the weapon/armor/chassis cost):
         -1 costs 0 if weapon<armor, 1 if 2*armor>weapon>=armor, and 2 if weapon>=2*armor.
         -2 has cost equal to weapon-1.
         -3 has cost equal to armor-1.
         -4 has cost equal to chassis-1.
         -5 has cost equal to weapon+armor-2.
         -6 has cost equal to weapon+chassis-2.
         -7 has cost equal to armor+chassis-2.
        
         It's used as if that were the positive ability cost, i.e. positive total_unit_cost = (unit_cost *(1+ability_cost / 4))
         */
        
        
        return 0;
    }
        
        
        
        public Effect build() {
            return new Effect(this);
        }
    }

  

   

}
