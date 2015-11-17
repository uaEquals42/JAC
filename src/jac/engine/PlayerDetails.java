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

import jac.Enum.BoolNames;
import jac.engine.Faction.Faction;
import jac.engine.ruleset.Ideology;
import jac.engine.ruleset.Ruleset;
import jac.unit.Effect;
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
    private final int id;  // unique id to keep track of who is who multiplayer.
    
   
    
    private long energy_resrves;
    private Map<String, Ideology> current_ideologies;
    private Map<String, Ideology> known_ideologies;
    private List<GenericUnit> genericunits;
    private List<String> knownTechnologies;
    
    private List<GenericUnit> bases = new LinkedList<>();
    private Effect empireEffects = new Effect.Builder().build();  // TODO Make this load all the unit effects.  
    
    PlayerDetails(int id, String player_name, Faction faction, long bonus_starting_energy, Ruleset rules){
        this.id = id;
        this.rules = rules;
        this.player_name = player_name;
        this.faction = faction;
        
       
    }

    public Effect getEmpireEffects(){
        return empireEffects;
    }
    
    public String getPlayer_name() {
        return player_name;
    }

    public Faction getFaction() {
        return faction;
    }

    public int getId() {
        return id;
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
    
    
    public List<GenericUnit> getBases(){
        return bases;
    }
    
    // Use this to test getBases().  Make sure the code works correctly.  This is the slow method.
    public List<GenericUnit> findBases(int turn){
        List<GenericUnit> bases = new LinkedList<>();
        for(GenericUnit unit : genericunits){
            if(unit.calculateBool(BoolNames.IS_IT_A_BASE)){
                bases.add(unit);
            }
        }
        return bases;
    }
}
