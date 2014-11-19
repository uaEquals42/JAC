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
import jac.engine.ruleset.Ideology;
import jac.engine.ruleset.Ruleset;
import jac.unit.GenericUnit;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Gregory Jordan
 */
public class PlayerDetails {
    private final String player_name;
    private final Faction faction;
    private final Ruleset rules;
    
    
    private long energy_resrves;
    private Map<String, Ideology> current_ideologies;
    private Map<String, Ideology> known_ideologies;
    private List<GenericUnit> genericunits;
    private List<String> knownTechnologies;

    
    PlayerDetails(String player_name, Faction faction, long bonus_starting_energy, Ruleset rules){
        this.rules = rules;
        this.player_name = player_name;
        this.faction = faction;
    }

    public String getPlayer_name() {
        return player_name;
    }

    public Faction getFaction() {
        return faction;
    }

    public long getEnergy_resrves() {
        return energy_resrves;
    }

    public Map<String, Ideology> getCurrent_ideologies() {
        return current_ideologies;
    }

    public List<String> getKnownTechnologies() {
        return knownTechnologies;
    }
    
    public List<GenericUnit> getBases(int turn){
        List<GenericUnit> bases = new LinkedList<>();
        for(GenericUnit unit : genericunits){
            if(unit.isitabase(turn, this)){
                bases.add(unit);
            }
        }
        return bases;
    }
}