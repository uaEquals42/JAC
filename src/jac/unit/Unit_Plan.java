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

import jac.engine.ruleset.Armor;
import jac.Enum.MovementType;
import jac.engine.ruleset.Reactor;
import jac.engine.ruleset.Tech;
import jac.engine.ruleset.Weapon;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Gregory Jordan
 */
public class Unit_Plan {

    private Chassis chassis;
    private Reactor reactor;
    private Armor armor;
    private Weapon weapon;
    private List<UnitAbility> unit_abilities;
    private List<Facility> unit_facilities;
    private int max_health;
    private boolean prototyped = false;
    private int cost;

    public Unit_Plan(Map<String, Tech> known_techs, Chassis chas, Reactor react, Armor def, Weapon weap, List<UnitAbility> abilities) {
        // Check to make sure inputs are 
        this.unit_abilities = abilities;
        // Then set the variables
        chassis = chas;
        reactor = react;
        armor = def;
        weapon = weap;

        max_health = reactor.reactor_power() * 10;  //TODO: This is correct.  I wonder if I should have the health multiplier stored in the rules file.

        cost = calculate_cost();

    }

    public int cost_get() {
        return cost;
    }

    // Private methods
    private int calculate_cost() {
        //http://strategywiki.org/wiki/Sid_Meier%27s_Alpha_Centauri/Units is the formula I'm using.
        int def_cost = armor.getCost();
        if (chassis.mode() == MovementType.SEA) {
            def_cost = def_cost / 2;
        } else if (chassis.mode() == MovementType.AIR) {
            def_cost = def_cost * 2;
        }

        int wep_cost = def_cost;
        if (wep_cost * 2 < def_cost) {
            wep_cost = def_cost / 2;
        }
        int r = reactor.reactor_power();
        int speed = chassis.getSpeed();

        int c = wep_cost * (def_cost + speed) * 10 / (2 ^ (r + 1));

        if (chassis.mode() == MovementType.SEA) {
            c = c / 2;
        } else if (chassis.mode() == MovementType.AIR) {
            c = c / 4;
        }

        // 
        int ability_cost = 0;
        for (UnitAbility able : unit_abilities) {
            ability_cost = ability_cost + able.calc_cost(c, weapon, chassis, armor);
        }

        c = c + ability_cost;

        if (wep_cost > 1 && def_cost > 1) {
            c = c + 10;
        }
        if (wep_cost > 1 && def_cost > 1 && speed > 1) {
            c = c + 10;
        }

        //Cmin = (R × 2 − R ÷ 2) × 10
        int cmin = (r * 2 - r / 2) * 10;

        if (cmin > c) {
            return cmin;
        } else {
            return c;
        }
    }

}
