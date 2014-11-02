/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine.ruleset;

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
    Map<String, String> technames = new LinkedHashMap<>();
    Map<String, List<String>> chasis = new LinkedHashMap<>();
    Map<Integer, String[]> weapons = new LinkedHashMap<>();  // TODO: Should I use Linked Map instead?  a linked List?  arggggg....
    Map<Integer, String[]> armor = new LinkedHashMap<>();  // TODO: I'm not happy with this solution.
    Map<Integer, String[]> reactors = new LinkedHashMap<>();  // Will it xml?
    
    Translation(Locale lang){
        this.language = lang;
    }
}
