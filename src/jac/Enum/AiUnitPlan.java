/*
 * JAC Copyright (C) 2015 Gregory Jordan
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
public enum AiUnitPlan {
    AUTO,
    OFFENSIVE,
    COMBAT,
    DEFENSIVE,
    RECONNAISANCE,
    AIR_SUPERIORITY,
    PLANET_BUSTER,
    NAVEL_SUPRERIORITY,
    NAVEL_TRANSPORT,
    COLONIZATION,
    TERRAFORMING,
    SUPPLY_CONVOY,
    INFO_WAR,
    ALIEN_ARTIFACT;
    
    /**
     * Convert SMAC number code to an ENUM
     * @param input  this function will auto-trim the input.
     * @return
     */
    public static AiUnitPlan convert(String input){
        switch (input.trim()){
                case "-1":
                    return AUTO;
                case "0":
                    return OFFENSIVE;
                case "1":
                    return COMBAT;
                case "2":
                    return DEFENSIVE;
                case "3":
                    return RECONNAISANCE;
                case "4":
                    return AIR_SUPERIORITY;
                case "5":
                    return PLANET_BUSTER;
                case "6":
                    return NAVEL_SUPRERIORITY;
                case "7":
                    return NAVEL_TRANSPORT;
                case "8":
                    return COLONIZATION;
                case "9":
                    return TERRAFORMING;
                case "10":
                    return SUPPLY_CONVOY;
                case "11":
                    return INFO_WAR;
                case "12":
                    return ALIEN_ARTIFACT;
                default:    
                  throw new IllegalArgumentException("Number wasn't -1<=x<=12");
        }
    }
   
}
