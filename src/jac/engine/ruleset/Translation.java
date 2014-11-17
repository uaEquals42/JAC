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
package jac.engine.ruleset;

import jac.engine.dialog.Noun;
import jac.engine.dialog.Quote;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 *
 * @author Gregory Jordan
 */
public class Translation {
    Locale language;
    
    Quote opening_quote;
    
    Map<String, String> technames = new LinkedHashMap<>();
    Map<String, List<Quote>> tech_quotes = new LinkedHashMap<>();
    
    Map<String, List<Noun>> chasis = new LinkedHashMap<>();
    Map<String, String[]> weapons = new LinkedHashMap<>();  
    Map<String, String[]> armor = new LinkedHashMap<>();  
    Map<String, String[]> reactors = new LinkedHashMap<>();  
    Map<String, String[]> unit_abilities = new LinkedHashMap<>();
    
    Map<String, String[]> facilities = new LinkedHashMap<>();
    Map<String, List<Quote>> facilities_quotes = new LinkedHashMap<>();

    public Locale getLanguage() {
        return language;
    }

    public Quote getOpening_quote() {
        return opening_quote;
    }

    public Map<String, String> getTechnames() {
        return technames;
    }

    public Map<String, List<Quote>> getTech_quotes() {
        return tech_quotes;
    }

    public Map<String, List<Noun>> getChasis() {
        return chasis;
    }

    public Map<String, String[]> getWeapons() {
        return weapons;
    }

    public Map<String, String[]> getArmor() {
        return armor;
    }

    public Map<String, String[]> getReactors() {
        return reactors;
    }

    public Map<String, String[]> getUnit_abilities() {
        return unit_abilities;
    }

    public Map<String, String[]> getFacilities() {
        return facilities;
    }

    public Map<String, List<Quote>> getFacilities_quotes() {
        return facilities_quotes;
    }
    
    
    
    public Translation(Locale lang){
        this.language = lang;
    }
}
