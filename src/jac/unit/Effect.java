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
import jac.unit.effectRules.EffectNode;
import jac.unit.effectRules.Value;
import java.util.ArrayList;
import java.util.EnumMap;
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
    
     
    private final List<UnitCoversion> converts_to; 
    /**
     * // Yes I'm using a map.  Yes a switch would probably be faster to run... 
     * but this will save allot of time writing and testing a bunch of switch statements.  
     * If we need the speed... we can optimize latter after the design has been finalized.
     * Doing this.... really... shrunk down the lines of code needed to be written.
     */
    private final Map<IntNames, EffectNode<Integer>> intEffects; 
    private final Map<IntNames, EffectNode<Float>> multiplier; 
    private final Map<BoolNames, EffectNode<Boolean>> boolEffects; 

    
    private Effect(Builder build) {
   
        converts_to = build.converts_to;
        this.intEffects = build.intEffects;
        this.boolEffects = build.boolEffects;
        multiplier = build.multiplier;
    }
    
    
     public EffectNode<Integer> getIntVariable(IntNames var){
        return intEffects.get(var);
    }
     
    public EffectNode<Boolean> getBoolVariable(BoolNames var){
        return boolEffects.get(var);
    }
    
    public Integer getIntValue(IntNames var, Unit unit){
        return intEffects.get(var).result(unit);
    }
    
    public Float getFloatValue(IntNames var, Unit unit){
        return multiplier.get(var).result(unit);
    }
     
    public Boolean getBoolValue(BoolNames var, Unit unit){
        return boolEffects.get(var).result(unit);
    }
    
    
    public static class Builder {
        
        private Map<BoolNames, EffectNode<Boolean>> boolEffects = new EnumMap<>(BoolNames.class);
        Map<IntNames, EffectNode<Integer>> intEffects = new EnumMap<>(IntNames.class); 
        Map<IntNames, EffectNode<Float>> multiplier = new EnumMap<>(IntNames.class);
            
        private List<UnitCoversion> converts_to = new ArrayList<>();


        public Builder(){
            for(BoolNames nnn : BoolNames.values()){
                boolEffects.put(nnn, Value.False());
            }
            
            for(IntNames nnn : IntNames.values()){
                intEffects.put(nnn, Value.Zero());
            }
            
            for(IntNames nnn : IntNames.values()){
                multiplier.put(nnn, Value.One());
            }
            
        }
        
        
        public Builder makeItABase() {
            boolEffects.put(BoolNames.IS_IT_A_BASE, Value.True());
            boolEffects.put(BoolNames.CAN_MAKE_FACILITIES, Value.True());
            boolEffects.put(BoolNames.CAN_MAKE_UNITS, Value.True());
            boolEffects.put(BoolNames.CAPTURED_WHEN_DEFEATED, Value.True());
            return this;
        }
        
        public Builder makeItAUnit(){
            boolEffects.put(BoolNames.CAN_IT_ATTACK, Value.True());
            boolEffects.put(BoolNames.CAN_DEFEND, Value.True());
            return this;
        }
        
        
       public Builder setBoolFlag(BoolNames effect, EffectNode<Boolean> value){
           boolEffects.put(effect, value);
           return this;
       }
       
        public Builder setIntFlag(IntNames effect, EffectNode<Integer> value){
          intEffects.put(effect, value);
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
