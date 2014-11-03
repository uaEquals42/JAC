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
public class Reactor {
  

    Translation tran;
    int power;
    List<String> pre_reqs = new ArrayList<>();

    public Reactor(Translation tran, Integer id, int power, String pre_req, String full_name, String short_name) {
        this.tran = tran;
        this.power = power;
        if (!pre_req.trim().equalsIgnoreCase("None")) {
            pre_reqs.add(pre_req.trim());
        }

        String[] names = new String[2];
        names[0] = full_name.trim();
        names[1] = short_name.trim();
        tran.reactors.put(id, names);

    }
}
