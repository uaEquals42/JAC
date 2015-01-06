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

import jac.engine.ruleset.Translation;
import jac.unit.tests.RestrictionTest;


/**
 *
 * @author Gregory Jordan
 */
public class Reactor extends UnitPart{
    private final int power;
   
 


    public Reactor(Builder build){
        super(build);
        this.power = build.power;
        build.getTran().getReactors().put(getKey(), build.names);
        
    }
    
    
    public int reactor_power(){
        return power;
    }
    
    public String full_name(Translation tran){
        return tran.getReactors().get(getKey())[0];
    }
    
    public String short_name(Translation tran){
        return tran.getReactors().get(getKey())[1];
    }
    
    public static class Builder extends UnitPart.Builder<Builder>{
        private final int power;
        private final String[] names;
        public Builder(Translation tran, String key, int flatcost, int power, String full_name, String short_name){
            super(tran, key, flatcost);
            this.power = power;
            this.names = new String[2];
            names[0] = full_name;
            names[1] = short_name;
        }

        public Reactor build() {
            return new Reactor(this);
        }
        
       
    
    }
    
}
