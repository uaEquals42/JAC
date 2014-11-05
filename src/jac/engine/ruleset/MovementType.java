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
public enum MovementType {
    LAND,
    SEA,
    AIR;
    
    public static MovementType convert(int type){
        if(type == 0){
            return LAND;
        }
        if(type == 1){
            return SEA;
        }
        if(type == 2){
            return AIR;
        }
        throw new IllegalArgumentException();
    }
}
