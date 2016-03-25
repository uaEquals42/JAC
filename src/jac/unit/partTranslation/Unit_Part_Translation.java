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

package jac.unit.partTranslation;

import java.util.Locale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Gregory Jordan
 */
public class Unit_Part_Translation implements Translation{
    static Logger log = LoggerFactory.getLogger(Unit_Part_Translation.class);
    
    private final Locale language;
    final String full_name;
    final String short_name;
    final String defensive_name;
    final String offensive_name;
    final String short_description;  
    

    private Unit_Part_Translation(Builder build) {
        language = build.language;
        full_name = build.full_name;
        short_name = build.short_name;
        defensive_name = build.defensive_name;
        offensive_name = build.offensive_name;
        short_description = build.short_description;
    }

    @Override
    public Locale getLanguage() {
        return language;
    }

    public String getFull_name() {
        return full_name;
    }

    public String getShort_name() {
        return short_name;
    }

    public String getDefensive_name() {
        return defensive_name;
    }

    public String getOffensive_name() {
        return offensive_name;
    }

    public String getShort_description() {
        return short_description;
    }
    
    
    
    
    public static class Builder{
    private final Locale language;
    private final String full_name;
    private final String short_name;
    private  String defensive_name;
    private  String offensive_name;
    private  String short_description;  

        public Builder(Locale language, String full_name, String short_name) {
            this.language = language;
            this.full_name = full_name;
            this.short_name = short_name;
            defensive_name = short_name;
            offensive_name = short_name;
        }
        
        public Builder set_defensive_name(String defense){
            defensive_name = defense;
            return this;
        }
        
        public Builder set_description(String description){
            short_description = description;
            return this;
        }
        
        public Builder set_offensive_name(String offensive){
            offensive_name = offensive;
            return this;
        }
        
        public Unit_Part_Translation build(){
            return new Unit_Part_Translation(this);
        }
    }
}
