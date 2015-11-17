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

import jac.Enum.DefenceMode;
import jac.engine.ruleset.Translation;



/**
 *
 * @author Gregory Jordan
 */
public class Armor extends PartCodeReuse implements UnitPart  {
    
    private final int armor;
    private final DefenceMode mode;
    
    private Armor(Builder build){
        super(build.generalPartDetails);
        
        this.armor = build.armor;
        this.mode = build.mode;
     
    }
    
       
    
    public String name1(Translation tran){
        return tran.getArmor().get(getKey())[0];
    }
    
    public String name2(Translation tran){
        return tran.getArmor().get(getKey())[1];
    }

    public int getArmor() {
        return armor;
    }

    public DefenceMode getMode() {
        return mode;
    }

    
    public static class Builder{
        private final GenericPart generalPartDetails;
        private final int armor;
        private final DefenceMode mode;
        private final String[] names;
 
            public Builder(GenericPart generalPartDetails, int armor, DefenceMode mode, String name1, String name2) {
            this.generalPartDetails = generalPartDetails;
            this.armor = armor;
           
            this.mode = mode;
            names = new String[2];
            names[0] = name1.trim();
            names[1] = name2.trim();

        }

        public Armor build() {
           return new Armor(this);
        }
        
    }
    
    
}
