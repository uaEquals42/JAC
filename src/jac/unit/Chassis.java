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
package jac.unit;

import jac.Enum.MovementType;
import jac.engine.ruleset.*;
import jac.engine.dialog.Noun;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gregory Jordan
 */
public class Chassis {

    private int speed;
    private MovementType triad;
    private boolean missle;
    private int base_cargo;
    private int cost;
    private List<String> pre_req_str = new ArrayList<>();
    private List<Tech> pre_req_techs = new ArrayList<>();
    private final String key;  // for looking up translations.
 

    public Chassis(Translation tran, String key, List<Noun> names, int speed, MovementType triad, boolean missle, int base_cargo, int cost, String pre_req) {
        this.key = key;
        
        tran.getChasis().put(key, names);
        
        this.missle = missle;
        this.base_cargo = base_cargo;
        this.cost = cost;
        this.speed = speed;
        this.triad = triad;
        if(pre_req.trim().length() > 0){
            if(!pre_req.equalsIgnoreCase("None")){
                pre_req_str.add(pre_req.trim());
            }
            
        }
    }
    
    public String key(){
        return key;
    }
    public List<Noun> names(Translation tran){
        return tran.getChasis().get(key);
    }

    public List<String> getPre_req_str() {
        return pre_req_str;
    }
    
    public int getBase_cargo(){
        return base_cargo;
    }

    public int getCost() {
        return cost;
    }

    public int getSpeed() {
        return speed;
    }

    public MovementType getTriad() {
        return triad;
    }

    public boolean isMissle() {
        return missle;
    }
    
    public MovementType mode(){
        return triad;
    }
    


}
