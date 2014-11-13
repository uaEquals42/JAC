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
package jac.engine.ruleset;

import jac.Enum.CombatMode;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gregory Jordan
 */
public class Weapon {
       
    private int offence; // -1 if psi combat.
    private CombatMode com_mode;
    private int cost;
    private List<String> pre_req_keys = new ArrayList<>();
    private List<Tech> pre_req_tech = new ArrayList<>();
    private final int id;

    public Weapon(Translation tran, int id, String name, String name2, int offence, int cost, String pre_req_key){
        String[] names = new String[2];
        names[0] = name.trim();
        names[1] = name2.trim();
        this.id = id;
        tran.weapons.put(id, names);
        this.offence = offence;
        this.cost = cost;
        if(!pre_req_key.trim().equalsIgnoreCase("None")){
            pre_req_keys.add(pre_req_key.trim());
        }
        
    }
    
    public boolean config_Techs(List<Tech> techlist){
        for(String key : pre_req_keys){
            for(Tech tech : techlist){
                if(key.equalsIgnoreCase(tech.id)){
                    pre_req_tech.add(tech);
                }
            }
        }
        
        if(pre_req_keys.size()==pre_req_tech.size()){
            return true;
        }
        return false;
    }

    public int getOffence() {
        return offence;
    }

    public CombatMode getCom_mode() {
        return com_mode;
    }

    public int getCost() {
        return cost;
    }
    
    
    
}
