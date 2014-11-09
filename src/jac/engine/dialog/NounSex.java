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
package jac.engine.dialog;

/**
 *
 * @author Gregory Jordan
 */

public enum NounSex {
    MALE_SINGULAR(false),
    MALE_PLURAL(true),
    FEMALE_SINGULAR(false),
    FEMALE_PLURAL(true),
    NEUTER_SINGULAR(false),
    NEUTER_PLURAL(true);
    
 
    final boolean plural;
    
    NounSex(boolean plural){
        this.plural = plural;
    }
    
    public static NounSex convert(String input){
        switch (input.trim()){
            case "M1":
                return MALE_SINGULAR;
              
            case "M2":
                return MALE_PLURAL;
                
            case "F1":
                return FEMALE_SINGULAR;
                
            case "F2":
                return FEMALE_PLURAL;
                
            case "N1":
                return NEUTER_SINGULAR;
                
            case "N2":
                return NEUTER_PLURAL;
                
            default:
                throw new IllegalArgumentException();  // This should actually never happen.  Not sure if I should have it default to something or what.
        }
    }
}
