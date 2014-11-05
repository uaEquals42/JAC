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
public enum DefenceMode {
    PROJECTILE, 
    ENERGY,
    BINARY,
    PSI;
    
    /**
     * For converting the old SMAC/X ints into an enum.
     * @param mode accepts 0,1,2.
     * @return The correct DefenceMode.
     */
    public static DefenceMode convert(int mode){
        switch (mode){
            case 0:
                return PROJECTILE;
            case 1:
                return ENERGY;
            case 2: 
                return BINARY;
        }
        
        throw new IllegalArgumentException("Values can only be between 0 and 2");
    }
    
}
