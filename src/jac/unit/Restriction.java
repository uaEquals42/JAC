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

import jac.Enum.CombatMode;
import jac.Enum.MovementType;
import jac.engine.PlayerDetails;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 *
 * @author Gregory Jordan
 */
public class Restriction {

    private final Set<String> required_chassis;
    private final Set<MovementType> requiredTypes;
    private final Set<CombatMode> requiredRoles;
    private final Set<String> required_reactor;


    private final Integer length_of_effect;

    private final String required_ideology;

    private final Integer base_bigger_than;
    private final Integer base_smaller_than;
    private final String required_facility;
    

    public boolean available(int lifespan, GenericUnit unit, PlayerDetails player) {
        boolean result = true;

        result = result && length_of_effect <= lifespan;
        result = result && required_chassis.contains(unit.getChassis().getKey());

        result = result && player.getCurrent_ideologies().containsKey(required_ideology);

        if (!required_reactor.isEmpty()){
            result = result && required_reactor.contains(unit.getReactor().getKey());         
        }
        
        
        if (base_bigger_than != null) {
            if (unit.getPopulation() == null) {
                return false;
            } else {
                result = result && base_bigger_than > unit.getPopulation();
            }

        }

        if (base_smaller_than != null) {
            if (unit.getPopulation() == null) {
                return false;
            } else {
                result = result && base_smaller_than < unit.getPopulation();
            }
        }

        if (!required_facility.isEmpty()) {
            result = result && unit.getUnit_facilities().containsKey(required_facility);
        }

        if (!requiredTypes.isEmpty()) {
            result = result && requiredTypes.contains(unit.getChassis().getTriad());
        }

        if (!requiredRoles.isEmpty()) {
            result = result && requiredRoles.contains(unit.getWeapon().getCom_mode());
        }
        

        return result;
    }

    public static class Builder {
        // Required

        // Optional
        private Set<String> required_chassis = new HashSet<>();
        private Set<MovementType> requiredTypes = new HashSet<>();
        private Set<CombatMode> requiredRoles = new HashSet<>();
        private Set races = new LinkedHashSet<>();
        private Set<String> required_reactor = new HashSet<>();

        private Integer x_turns = null;

        private Integer base_bigger_than = null;
        private Integer base_smaller_than = null;

        private String required_ideology = "";
        private String required_facility = "";
       

        public Builder RestrictRaceTo(String race){
            this.races.add(race);
            return this;
        }
        
        public Builder SetAllowedRoles(Set<CombatMode> commode) {
            this.requiredRoles = commode;
            return this;
        }
        
        public Builder SetAllowedTypes(Set<MovementType> allowedTypes) {
            this.requiredTypes = allowedTypes;
            return this;
        }

        public Builder addAllowedType(MovementType allowedType) {
            this.requiredTypes.add(allowedType);
            return this;
        }

        public Builder setRequired_reactor(Set<String> required_reactors) {
            this.required_reactor = required_reactors;
            return this;
        }
        
        public Builder SetAllowedChassis(Set<String> chassis_keys) {
            this.required_chassis = chassis_keys;
            return this;
        }

        public Builder ForLimitedTime(Integer time) {
            x_turns = time;
            return this;
        }

        public Restriction build() {
            return new Restriction(this);
        }
    }

    private Restriction(Builder build) {
        this.required_chassis = build.required_chassis;
        this.requiredTypes = build.requiredTypes;
        this.requiredRoles = build.requiredRoles;

        this.length_of_effect = build.x_turns;

        this.base_bigger_than = build.base_bigger_than;
        this.base_smaller_than = build.base_smaller_than;

        this.required_ideology = build.required_ideology;
        this.required_facility = build.required_facility;
        this.required_reactor = build.required_reactor;
    }

}
