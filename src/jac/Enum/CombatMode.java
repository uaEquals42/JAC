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
package jac.Enum;

/**
 *
 * @author Gregory Jordan
 */
public enum CombatMode {
    PROJECTILE, 
    ENERGY, 
    MISSILE,
    PSI,
    TRANSPORT,
    COLONIST,
    TERRAFORMER,
    CONVOY,
    PROBE,
    ARTIFACT;  
    public static int COUNT = 10;
    public static CombatMode convert(int mode){
     
        switch(mode){
            case 0:
                return PROJECTILE;
            case 1:
                return ENERGY;
            case 2:
                return MISSILE;
            case 7:
                return TRANSPORT;
            case 8:
                return COLONIST;
            case 9:
                return TERRAFORMER;
            case 10:
                return CONVOY;
            case 11:
                return PROBE;
            case 12:
                return ARTIFACT;    
        }
        
        throw new IllegalArgumentException();
    }
}
