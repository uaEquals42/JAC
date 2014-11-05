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
public class Reactor {
  

    private Translation tran;
    private int power;
    private List<String> pre_reqs = new ArrayList<>();
    private final int key;

    public Reactor(Translation tran, Integer id, int power, String pre_req, String full_name, String short_name) {
        this.key = id;
        this.tran = tran;
        this.power = power;
        if (!pre_req.trim().equalsIgnoreCase("None")) {
            pre_reqs.add(pre_req.trim());
        }

        String[] names = new String[2];
        names[0] = full_name.trim();
        names[1] = short_name.trim();
        tran.reactors.put(key, names);

    }
    
    public int reactor_power(){
        return power;
    }
    
    public String full_name(){
        return tran.reactors.get(key)[0];
    }
    
    public String short_name(){
        return tran.reactors.get(key)[1];
    }
    
    public List<String> list_pre_reqS(){
        return pre_reqs;
    }
}
