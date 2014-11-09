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

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gregory Jordan
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
