/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine.ruleset;

import java.util.HashMap;
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
    Map<String, List<String>> chasis = new HashMap<>();
    Map<Integer, String[]> weapons = new HashMap<>();  // TODO: Should I use Linked Map instead?  a linked List?  arggggg....
    Map<Integer, String[]> armor = new HashMap<>();
    
    Translation(Locale lang){
        this.language = lang;
    }
}
