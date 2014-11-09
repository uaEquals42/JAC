/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
