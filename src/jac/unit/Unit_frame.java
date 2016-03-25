/*
 * JAC Copyright (C) 2016 Gregory Jordan
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

import jac.engine.HasKey;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Describes what types of parts can be attached and how many. 
 * @author Gregory Jordan
 */
public class Unit_frame implements HasKey {
    static Logger log = LoggerFactory.getLogger(Unit_frame.class);
    
        private final Map<Part_Category, Integer> capabilities;
        private final String key;
    
        
        
        
        
        private Unit_frame(Builder build) {
        this.capabilities = build.capabilities;
        this.key = build.key;
    } 

    @Override
    public String getKey() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public static class Builder{
        final Map<Part_Category, Integer> capabilities;
        private final String key;
        
        public Builder(String key){
            capabilities = new EnumMap<>(Part_Category.class);
            for(Part_Category cat : Part_Category.values()){
                capabilities.put(cat, 0);
            }
            this.key = key;
            
            
        }
        
        public Builder set(Part_Category cat, Integer limit){
            capabilities.put(cat, limit);
            return this;
        }
        
        public Unit_frame build(){
            return new Unit_frame(this);
        }
        
    }
}
