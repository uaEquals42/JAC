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
   

    private final int range;  // how many turns from base can it go before becoming damaged/destroyed.
    private final int percentDamageWhenOutOfRange;
  
   

    private Chassis(Builder build){
        super(build);
        
        build.getTran().getChasis().put(build.getKey(), build.names);
        
        this.missle = build.missle;
        this.base_cargo = build.base_cargo;
  
        this.speed = build.speed;
        this.triad = build.triad;
       
        this.range = build.range;
        this.percentDamageWhenOutOfRange = build.percentDamageWhenOutOfRange;
    }
    
    
  
    public List<Noun> names(Translation tran){
        return tran.getChasis().get(getKey());
    }

    
    public int getBase_cargo(){
        return base_cargo;
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
    

    public static class Builder  extends UnitPart.Builder<Builder>{
        private final MovementType triad;
        private final List<Noun> names;
        private final int speed; 
        
       private Integer range;  // how many turns from base can it go before becoming damaged/destroyed.
       private int percentDamageWhenOutOfRange=0;  
        
        private boolean missle = false;
        private int base_cargo = 1;
   

        public Builder(Translation tran, String key, int flatcost, MovementType triad, int speed, List<Noun> names){
            super(tran, key, flatcost);
            
            this.triad = triad;
            this.speed = speed;
            this.names = names;
        }
             
        public Builder setRange(int range){
            this.range = range;
            return this;
        }
        
        public Builder damageDonewhenOutofrange(int percent){
            this.percentDamageWhenOutOfRange = percent;
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
