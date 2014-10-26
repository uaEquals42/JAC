/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine.ruleset;

import engine.Faction.SocialAreas;
import java.util.EnumMap;
import java.util.Map;

/**
 *
 * @author grjordan
 */
public class Ideology {
    String category;
    String name;
    String pre_req;
    
    Map<SocialAreas, Integer> effects = new EnumMap<>(SocialAreas.class);
    
    Ideology(String category, String name, String pre_req){
        this.category = category;
        this.name = name;
        this.pre_req = pre_req;
    }
    
    public void add_socialmod(SocialAreas area, int modifier){
        effects.put(area, modifier);
       
    }
    
    public int get_modifier(SocialAreas key){
        if(effects.containsKey(key)){
            return effects.get(key);
        }
        else{
            return 0;
        }
    }
   
    
    
}
