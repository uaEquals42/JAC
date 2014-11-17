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
package jac.engine;

import jac.engine.Faction.Faction;
import jac.engine.Faction.FactionSettings;
import jac.engine.Faction.Faction_Dialog;
import jac.engine.ruleset.Ideology;
import jac.engine.ruleset.Ruleset;
import jac.unit.GenericUnit;
import java.util.List;

/**
 *
 * @author Gregory Jordan
 */
public class PlayerDetails {
    private final String player_name;
    private final Faction faction;
    private long energy_resrves;
    private final Ruleset rules;
    List<Ideology> ideologies;
    List<GenericUnit> genericunits;
    List<GenericUnit> units;  // Not sure how I will sync this over the network...
    List<GenericUnit> bases; 
    
    PlayerDetails(String player_name, Faction faction, long bonus_starting_energy, Ruleset rules){
        this.rules = rules;
        this.player_name = player_name;
        this.faction = faction;
    }
}
