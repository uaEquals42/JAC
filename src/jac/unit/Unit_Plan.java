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

import jac.Enum.IntNames;
import jac.engine.ruleset.Ruleset;
import jac.unit.effectRules.EffectNode;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Gregory Jordan
 */
public class Unit_Plan implements Comparable<String>, Unit {

    private final String chassisKey;
    private final String reactorKey;
    private final String armorKey;
    private final String weaponKey;

    private final Set<String> unitAbilityKeys;
    private final Set<String> unitFacilityKeys;

    private final EffectNode<Integer> costoverride;  // So ruleset designer can specify a certain combination of parts to be allot cheaper. 

    private boolean prototyped = false;

    private Unit_Plan(Builder build) {
        this.prototyped = build.prototyped;
        this.chassisKey = build.chassisKey;
        this.armorKey = build.armorKey;
        this.reactorKey = build.reactorKey;
        this.weaponKey = build.weaponKey;
        this.costoverride = build.costoverride;
        this.unitAbilityKeys = build.unitAbilityKeys;
        this.unitFacilityKeys = build.unitFacilityKeys;
    }

    @Override
    public List<Effect> getLocalEffects(Ruleset rules) {
        List<Effect> locEffect = new ArrayList<>();
        locEffect.add(rules.getChassis().get(chassisKey).getLocalEffects());
        locEffect.add(rules.getReactors().get(reactorKey).getLocalEffects());
        locEffect.add(rules.getArmors().get(armorKey).getLocalEffects());
        locEffect.add(rules.getWeapons().get(weaponKey).getLocalEffects());
        

        for (String abilityKey : unitAbilityKeys) {
            locEffect.add(rules.getUnit_abilities().get(abilityKey).getLocalEffects());
        }

        for (String facilityKey : unitFacilityKeys) {
            locEffect.add(rules.getFacilities().get(facilityKey).getLocalEffects());
        }
        return locEffect;
    }

    
    public int cost_get(Ruleset rules) {
        if (costoverride != null) {
            return costoverride.result(this, rules);
        } else {
            return UnitLibrary.calculateInteger(IntNames.COST, this, rules);
        }

    }

    

    /**
     * // Private methods private int calculate_cost() {
     * //http://strategywiki.org/wiki/Sid_Meier%27s_Alpha_Centauri/Units is the
     * formula I'm using. int def_cost = armor.getFlatcost();
     *
     * if (chassis.getDomain() == Domain.SEA) { def_cost = def_cost / 2; } else
     * if (chassis.getDomain() == Domain.AIR) { def_cost = def_cost * 2; }
     *
     * int wep_cost = def_cost; if (wep_cost * 2 < def_cost) {
     * wep_cost = def_cost / 2;
     * }
     * int r = reactor.reactor_power();
     * int speed = chassis.getMovementPoints();
     *
     * int runningTotal = wep_cost * (def_cost + speed) * 10 / (2 ^ (r + 1));
     *
     * if (chassis.getDomain() == Domain.SEA) {
     * runningTotal = runningTotal / 2;
     * } else if (chassis.getDomain() == Domain.AIR) {
     * runningTotal = runningTotal / 4;
     * }
     *
     * //
     * int ability_cost = 0;
     * for (String key : unitAbilities.keySet()){
     * ability_cost = ability_cost + unitAbilities.get(key).calculate_cost(runningTotal, weapon, armor,chassis);
     * }
     *
     *
     * runningTotal = runningTotal + ability_cost;
     *
     * if (wep_cost > 1 && def_cost > 1) { runningTotal = runningTotal + 10; }
     * if (wep_cost > 1 && def_cost > 1 && speed > 1) { runningTotal =
     * runningTotal + 10; }
     *
     * //Cmin = (R × 2 − R ÷ 2) × 10 int cmin = (r * 2 - r / 2) * 10;
     *
     * if (cmin > runningTotal) { return cmin; } else { return runningTotal; } }
     */
    @Override
    public String toString() {
        return "Unit Plan:" + chassisKey + armorKey + reactorKey + weaponKey + unitAbilityKeys.toString() + unitFacilityKeys.toString();
    }

    @Override
    public int compareTo(String o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getChassisKey() {
        return chassisKey;
    }

    @Override
    public String getReactorKey() {
        return reactorKey;
    }

    @Override
    public String getArmorKey() {
        return armorKey;
    }

    @Override
    public String getWeaponKey() {
       return weaponKey;
    }

    @Override
    public Set<String> getUnitAbilityKeys() {
        return unitAbilityKeys;
    }

    @Override
    public Set<String> getUnitFacilityKeys() {
        return unitFacilityKeys;
    }

    @Override
    public Integer getPopulation() {
        return 0;
    }

    
    //----------------------------------------------------------------------
    
    public static class Builder {

        private String chassisKey;
        private String reactorKey;
        private String armorKey;
        private String weaponKey;

        private Set<String> unitAbilityKeys;
        private Set<String> unitFacilityKeys;

        private boolean prototyped;
        
        
        //Overrides.
        private EffectNode<Integer> costoverride = null;  // So that

        public Builder(String chassis, String reactor, String armor, String weapon) {
            this.chassisKey = chassis;
            this.reactorKey = reactor;
            this.armorKey = armor;
            this.weaponKey = weapon;    
            
            prototyped = false;
            
            unitAbilityKeys = new HashSet<>();
            unitFacilityKeys = new HashSet<>();
        }

        public Builder setUnitAbilities(Set<String> unitAbilityKeys) {
            this.unitAbilityKeys = unitAbilityKeys;
            return this;
        }

        public Builder prototyped(boolean value) {
            prototyped = value;
            return this;
        }

        public Builder setUnitFacilities(Set<String> unitFacilityKeys) {
            this.unitFacilityKeys = unitFacilityKeys;
            return this;
        }

        /**
         * Overrides the autogenerated costvalue.
         *
         * @param value
         * @return
         */
        public Builder costOverride(EffectNode<Integer> value) {
            costoverride = value;
            return this;
        }

        public Unit_Plan build() {
            return new Unit_Plan(this);
        }

    }

    
}
