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
    
    Map<Integer, List<Noun>> chasis = new LinkedHashMap<>();
    Map<Integer, String[]> weapons = new LinkedHashMap<>();  
    Map<Integer, String[]> armor = new LinkedHashMap<>();  
    Map<Integer, String[]> reactors = new LinkedHashMap<>();  
    Map<String, String[]> unit_abilities = new LinkedHashMap<>();
    
    Map<String, String[]> facilities = new LinkedHashMap<>();
    Map<String, List<Quote>> facilities_quotes = new LinkedHashMap<>();
    
    
    
    public Translation(Locale lang){
        this.language = lang;
    }
}
