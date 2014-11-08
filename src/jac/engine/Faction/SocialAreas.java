/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jac.engine.Faction;

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

    /**
     * This is used for converting the SMAC/X notation of ++PROBE or ---PLANET, 
     * into a mapping of the Enum and a int representation of the number of + or -.
     * ++PROBE would become [PROBE:2]
     * ---PLANET would become [PLANET:-3]
     * 
     * @param input  A string. 
     * @return Map<SocialAreas, Integer>
     */
    static public Map<SocialAreas, Integer> social_mods(String input) {
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
     * A helper function (mostly for converting the SMAC/X settings) that takes a string
     * And figures out what enum it should be.
     *
     * @param social A String with the word we are trying to figure out.
     * @return  A SocialAreas
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



