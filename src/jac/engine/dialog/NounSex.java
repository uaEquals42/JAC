/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jac.engine.dialog;

/**
 *
 * @author grjordan
 */

public enum NounSex {
    MALE_SINGULAR(false),
    MALE_PLURAL(true),
    FEMALE_SINGULAR(false),
    FEMALE_PLURAL(true),
    NEUTER_SINGULAR(false),
    NEUTER_PLURAL(true);
    
 
    final boolean plural;
    
    NounSex(boolean plural){
        this.plural = plural;
    }
    
    public static NounSex convert(String input){
        switch (input.trim()){
            case "M1":
                return MALE_SINGULAR;
              
            case "M2":
                return MALE_PLURAL;
                
            case "F1":
                return FEMALE_SINGULAR;
                
            case "F2":
                return FEMALE_PLURAL;
                
            case "N1":
                return NEUTER_SINGULAR;
                
            case "N2":
                return NEUTER_PLURAL;
                
            default:
                throw new IllegalArgumentException();  // This should actually never happen.  Not sure if I should have it default to something or what.
        }
    }
}
