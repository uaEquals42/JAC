/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine.Faction;

import java.util.EnumMap;
import java.util.Map;

/**
 *
 * @author grjordan
 */
public enum SocialAreas {
    ECONOMY,
    EFFIC,
    SUPPORT,
    TALENT,
    MORALE,
    POLICE,
    GROWTH,
    PLANET,
    PROBE,
    INDUSTRY,
    RESEARCH;

    static public Map social_mods(String input) {
        // we need to count the number of + or - in front of the name.
        Map<SocialAreas, Integer> effect = new EnumMap<>(SocialAreas.class);
        int count = 0;
        input = input.trim();
        if (input.charAt(0) != '-' && input.charAt(0) != '+') {
            return effect;
        }

        char[] tmp = input.toCharArray();
        int position = 0;
        for (int i = 0; i < tmp.length; i++) {
            if (tmp[i] == '+') {
                count += 1;
            } else if (tmp[i] == '-') {
                count -= 1;
            } else {
                position = i;
                i = tmp.length;  // There has got to be a cleaner way to do this.
            }
        }
        
        effect.put(SocialAreas.findtype(input.substring(position)), count);
        return effect;

    }

    /**
     *
     *
     * @param social
     * @return 
     */
    static public SocialAreas findtype(String social) {
        social = social.trim();
        switch (social.toUpperCase()) {
            case "ECONOMY":
                return SocialAreas.ECONOMY;    
            case "EFFIC":
                return SocialAreas.EFFIC;
            case "SUPPORT":
                return SocialAreas.SUPPORT;
            case "TALENT":
                return SocialAreas.TALENT;
            case "MORALE":
                return SocialAreas.MORALE;
            case "POLICE":
                return SocialAreas.POLICE;
            case "GROWTH":
                return SocialAreas.GROWTH;
            case "PLANET":
                return SocialAreas.PLANET;
            case "PROBE":
                return SocialAreas.PROBE;
            case "INDUSTRY":
                return SocialAreas.INDUSTRY;
            case "RESEARCH":
                return SocialAreas.RESEARCH;
        }
        // TODO: Make it throw and exception.
        return null;  // this shouldn't happen ever.  
    }
    
}



