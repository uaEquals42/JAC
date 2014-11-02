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
    /*
     Reactors
;
; Name, power, preq
;
#REACTORS
Fission Plant,        Fission,     1, None,
Fusion Reactor,       Fusion,      2, Fusion,
Quantum Chamber,      Quantum,     3, Quantum,
Singularity Engine,   Singularity, 4, SingMec,
    */
        Translation tran;
        int power;
        List<String> pre_reqs = new ArrayList<>();
        Reactor(Translation tran, Integer id, int power, String pre_req, String full_name, String short_name){
            this.tran = tran;
            this.power = power;
            pre_reqs.add(pre_req);
            String[] names = new String[2];
            names[0] = full_name;
            names[1] = short_name;
            tran.reactors.put(id, names);
            
    
}
}
