/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jac.engine.ruleset;

/**
 *
 * @author grjordan
 */
public enum CombatMode {
    PROJECTILE, 
    ENERGY, 
    MISSLE,
    PSI,
    TRANSPORT,
    COLONIST,
    TERRAFORMER,
    CONVOY,
    INFOWAR,
    ARTIFACT;  
    
    public static CombatMode convert(int mode){
     
        switch(mode){
            case 0:
                return PROJECTILE;
            case 1:
                return ENERGY;
            case 2:
                return MISSLE;
            case 7:
                return TRANSPORT;
            case 8:
                return COLONIST;
            case 9:
                return TERRAFORMER;
            case 10:
                return CONVOY;
            case 11:
                return INFOWAR;
            case 12:
                return ARTIFACT;    
        }
        
        throw new IllegalArgumentException();
    }
}
