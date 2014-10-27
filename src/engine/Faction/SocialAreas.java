/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine.Faction;

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
     * #TODO See if I can move to socialAreas.
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



