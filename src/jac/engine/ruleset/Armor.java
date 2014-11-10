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
public class Armor {
    
 
    private int id;
    private int armor;
    private DefenceMode mode;
    private int cost;
    private List<String> pre_reqs = new ArrayList<>();
    
    public Armor(Translation tran, int id, int armor, DefenceMode mode, int cost, String pre_req, String name1, String name2){
       
        this.id = id;
        if (!pre_req.equalsIgnoreCase("None")) {
            pre_reqs.add(pre_req);
        }
        this.armor = armor;
        this.mode = mode;
        String[] names = new String[2];
        names[0] = name1.trim();
        names[1] = name2.trim();
        tran.armor.put(id, names);
    }
    
    
    public String name1(Translation tran){
        return tran.armor.get(id)[0];
    }
    
    public String name2(Translation tran){
        return tran.armor.get(id)[1];
    }

    public int getArmor() {
        return armor;
    }

    public DefenceMode getMode() {
        return mode;
    }

    public int getCost() {
        return cost;
    }
    
    
}
