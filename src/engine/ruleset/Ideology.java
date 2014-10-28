/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine.ruleset;

import engine.Faction.SocialAreas;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author grjordan
 */
public class Ideology {
    String category;
    String name;
    List<String> pre_req = new ArrayList<>();
    
    Map<SocialAreas, Integer> effects = new EnumMap<>(SocialAreas.class);
    
    Ideology(String category, String name, List<String> pre_reqs){
        this.category = category;
        this.name = name;
        System.out.println(pre_reqs.size());
        this.pre_req.addAll(pre_reqs);
        
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
