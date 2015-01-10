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
import jac.unit.effectRules.EffectNode;
import java.util.ArrayList;
import java.util.HashMap;
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
    private final Map<String, UnitAbility> unitAbilities;
    private final Map<String, Facility> unitFacilities;
    private final Effect planEffect;
    
  
    private boolean prototyped = false;
    private int cost;

     
    private Unit_Plan(Builder build){
        this.armor = build.armor;
        this.chassis = build.chassis;
        this.reactor = build.reactor;
        this.weapon = build.weapon;
        this.unitAbilities = build.unitAbilities;
        this.unitFacilities = build.unitFacilities;
        this.planEffect = build.planLocalEffect;
    }

    public static class Builder{
        private final Chassis chassis;
        private final Reactor reactor;
        private final Armor armor;
        private final Weapon weapon;
        
        private Effect planLocalEffect;
        
        
        private boolean prototyped = false;
        private Map<String, UnitAbility> unitAbilities = new HashMap<>();
        private Map<String, Facility> unitFacilities = new HashMap<>();;
        
        //Overrides.
        private EffectNode<Integer> costoverride = null;
        
   

        public Builder(Chassis chassis, Reactor reactor, Armor armor, Weapon weapon){
            this.chassis = chassis;
            this.reactor = reactor;
            this.armor = armor;
            this.weapon = weapon;
            this.regenEffect();
        }
        
        public Builder setUnitAbilities(Map<String, UnitAbility> unitAbilities){
            this.unitAbilities = unitAbilities;
            return this.regenEffect();
        }

        public Builder prototyped(boolean value){
            prototyped = value;
            return this;
        }
        
        public Builder setUnitFacilities(Map<String, Facility> unitFacilities) {
            this.unitFacilities = unitFacilities;
            return this.regenEffect();
        }
        
        /**
         * Overrides the autogenerated costvalue.  
         * @return 
         */
        public Builder costOverride(EffectNode<Integer> value){
            costoverride = value;
            return this;
        }
        
        private Builder regenEffect(){
             List<Effect> locEffect = new ArrayList<>();
            locEffect.add(chassis.getLocalEffects());
            locEffect.add(reactor.getLocalEffects());
            locEffect.add(armor.getLocalEffects());
            locEffect.add(weapon.getLocalEffects());
            
            for(UnitAbility unitA : unitAbilities.values() ){
                locEffect.add(unitA.getLocalEffects());
            }
            
            for(Facility facility : unitFacilities.values() ){
                locEffect.add(facility.getLocalEffects());
            }
            
            planLocalEffect = new Effect.Builder().
                    combineEffects(locEffect).
                    costOverride(costoverride).build();
            
            return this;
        }
        
        public Unit_Plan build(){
            
            
            return new Unit_Plan(this);
        }
        
        
    }
    
    
    public int cost_get() {
        return cost;
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

    public Map<String, UnitAbility> getUnitAbilities() {
        return unitAbilities;
    }

    public Map<String, Facility> getUnitFacilities() {
        return unitFacilities;
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
        for (String key : unitAbilities.keySet()){   
            ability_cost = ability_cost + unitAbilities.get(key).calculate_cost(runningTotal, weapon, armor,chassis);
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
     

        
        return "Unit Plan:"+chassis.getKey()+armor.getKey()+reactor.getKey()+weapon.getKey()+unitAbilities.keySet()+unitFacilities.keySet();
    }
   
    @Override
    public int compareTo(String o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
