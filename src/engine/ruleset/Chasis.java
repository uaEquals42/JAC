/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine.ruleset;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author grjordan
 */
public class Chasis {

    int speed;
    MovementType triad;
    boolean missle;
    private int base_cargo;
    int cost;
    List<String> pre_req_str = new ArrayList<>();
    List<Tech> pre_req_techs = new ArrayList<>();
    private String key;  // for looking up translations.
    Translation tran;

    Chasis(Translation tran, String key, List<String> names, boolean missle, int base_cargo, int cost, String pre_req) {
        this.key = key;
        this.tran = tran;
        tran.chasis.put(key, names);
        this.missle = missle;
        this.base_cargo = base_cargo;
        this.cost = cost;
        if(pre_req.trim().length() > 0){
            pre_req_str.add(pre_req.trim());
        }
    }
    
    public String key(){
        return key;
    }
    public List<String> names(){
        return tran.chasis.get(key);
    }
    
    public int base_cargo(){
        return base_cargo;
    }
    
    


}
