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

import jac.Enum.SocialAreas;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Gregory Jordan
 */
public class Ideology {
    String category;
    String name;
    List<String> pre_req = new ArrayList<>();
    String key;
       
    
    Map<SocialAreas, Integer> effects = new EnumMap<>(SocialAreas.class);
    
    Ideology(String category, String key, String name, List<String> pre_reqs){
        this.category = category;
        this.name = name;
        //System.out.println(pre_reqs.size());
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
    
    public String getKey(){
        return key;
    }
   
    
    
}
