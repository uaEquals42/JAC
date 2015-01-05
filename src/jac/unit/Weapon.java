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

import jac.Enum.WeaponRole;
import jac.engine.ruleset.Tech;
import jac.engine.ruleset.Translation;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Gregory Jordan
 */
public class Weapon extends UnitPart{
       
    private final int offence; // -1 if psi combat.
    private final WeaponRole weaponsRole;
   
    public int getOffence() {
        return offence;
    }

    public WeaponRole getWeaponsRole() {
        return weaponsRole;
    }

 
    
    private Weapon(Builder build){
        super(build);
        
        build.getTran().getWeapons().put(build.getKey(), build.names);
        this.offence = build.offence;
        this.weaponsRole = build.com_mode;
        
        
    }
    
    public static class Builder extends UnitPart.Builder<Builder>{
        
        private final int offence; // -1 if psi combat.
     
        private final WeaponRole com_mode;
        
        private final String[] names;
        
         
        public Builder(Translation tran, String key, int cost, int offence, WeaponRole com_mode, String name, String name2){
            super(tran, key, cost);
            
            this.names = new String[2];
            this.names[0] = name.trim();
            this.names[1] = name2.trim();
            this.offence = offence;
         
            this.com_mode = com_mode;
        }
        

        
        public Weapon build(){
            return new Weapon(this);
        }
    }
    
    
    
}
