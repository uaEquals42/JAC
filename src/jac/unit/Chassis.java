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
public class Chassis extends UnitPart{

    private final int speed;
    private final  MovementType triad;
    private final  boolean missle;
    private final  int base_cargo;
    private final  int cost;
    private final  List<String> pre_req_str;
    private List<Tech> pre_req_techs;
    private final  String key;  // for looking up translations.
 

    private Chassis(Builder build){
        super(build.effectsList, build.restrictions);
        this.key = build.key;
        build.tran.getChasis().put(key, build.names);
        
        this.missle = build.missle;
        this.base_cargo = build.base_cargo;
        this.cost = build.cost;
        this.speed = build.speed;
        this.triad = build.triad;
        this.pre_req_str = build.pre_req_str;
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
    

    public static class Builder {
        private final MovementType triad;
        private final int cost;
        private final String key;  // for looking up translations.
        private final List<Noun> names;
        private final int speed; 
        private final Translation tran;
        
        private boolean missle = false;
        private int base_cargo = 1;
        private List<String> pre_req_str = new ArrayList<>();
        private List<Tech> pre_req_techs = new ArrayList<>();
        private List<Effect> effectsList = new ArrayList<>();
        private List<Restriction> restrictions = new ArrayList<>();
        

        public Builder(Translation tran, String key, MovementType triad, int cost, int speed, List<Noun> names){
            this.tran = tran;
            this.key = key;
            this.triad = triad;
            this.cost = cost;
            this.speed = speed;
            this.names = names;
        }
        
        public Builder addRestriction(Restriction binding){
            restrictions.add(binding);
            return this;
        }
        
        public Builder addEffect(Effect effect){
            effectsList.add(effect);
            return this;
        }
        
        public Builder addPreReqTech(String techkey){
            if(!techkey.trim().equalsIgnoreCase("None")){
                pre_req_str.add(techkey.trim());
            }
            
            return this;
        }
        
        public Builder ismissle(boolean missle){
            this.missle = missle;
            return this;
        }
        
        public Builder setCargo(int cargo){
            base_cargo = cargo;
            return this;
        }
        
        public Chassis build(){
            return new Chassis(this);
        }
    
    }
    

}
