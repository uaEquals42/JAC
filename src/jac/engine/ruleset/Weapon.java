/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jac.engine.ruleset;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author grjordan
 */
public class Weapon {
    

    
    int offence; // -1 if psi combat.
    CombatMode com_mode;
    int cost;
    List<String> pre_req_keys = new ArrayList<>();
    List<Tech> pre_req_tech = new ArrayList<>();
    final int id;

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
    
}
