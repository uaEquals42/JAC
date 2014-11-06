/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jac.engine.ruleset;

import jac.engine.dialog.Noun;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 *
 * @author grjordan
 */
public class Translation {
    Locale language;
    Map<String, String> technames = new HashMap<>();
    Map<Integer, List<Noun>> chasis = new HashMap<>();
    Map<Integer, String[]> weapons = new HashMap<>();  
    Map<Integer, String[]> armor = new HashMap<>();  
    Map<Integer, String[]> reactors = new HashMap<>();  
    Map<String, String[]> unit_abilities = new HashMap<>();
    
    public Translation(Locale lang){
        this.language = lang;
    }
}
