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
import java.util.LinkedHashSet;
import java.util.Set;

/**
 *
 * @author Gregory Jordan
 */
public class Restriction {

    private final Set<String> allowed_chassis;
    private final Set<MovementType> allowedTypes;
    private final Set<CombatMode> allowedRoles;


    private final Integer length_of_effect;

    private final String required_ideology;

    private final Integer base_bigger_than;
    private final Integer base_smaller_than;
    private final String required_facility;
    

    public boolean available(int lifespan, GenericUnit unit, PlayerDetails player) {
        boolean result = true;

        result = result && length_of_effect <= lifespan;
        result = result && allowed_chassis.contains(unit.getChassis().getKey());

        result = result && player.getCurrent_ideologies().containsKey(required_ideology);

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

        if (!allowedTypes.isEmpty()) {
            result = result && allowedTypes.contains(unit.getChassis().getTriad());
        }

        if (!allowedRoles.isEmpty()) {
            result = result && allowedRoles.contains(unit.getWeapon().getCom_mode());
        }
        

        return result;
    }

    public static class Builder {
        // Required

        // Optional
        private Set<String> allowed_chassis = new LinkedHashSet<>();
        private Set<MovementType> allowedTypes = new LinkedHashSet<>();
        private Set<CombatMode> allowedRoles = new LinkedHashSet<>();
        private Set races = new LinkedHashSet<>();

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
            this.allowedRoles = commode;
            return this;
        }
        
        public Builder SetAllowedTypes(Set<MovementType> allowedTypes) {
            this.allowedTypes = allowedTypes;
            return this;
        }

        public Builder addAllowedType(MovementType allowedType) {
            this.allowedTypes.add(allowedType);
            return this;
        }

        public Builder SetAllowedChassis(Set<String> chassis_keys) {
            this.allowed_chassis = chassis_keys;
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
        this.allowed_chassis = build.allowed_chassis;
        this.allowedTypes = build.allowedTypes;
        this.allowedRoles = build.allowedRoles;

        this.length_of_effect = build.x_turns;

        this.base_bigger_than = build.base_bigger_than;
        this.base_smaller_than = build.base_smaller_than;

        this.required_ideology = build.required_ideology;
        this.required_facility = build.required_facility;
     
    }

}
