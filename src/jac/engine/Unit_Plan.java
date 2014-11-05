/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jac.engine;

import jac.engine.ruleset.Armor;
import jac.engine.ruleset.Chasis;
import jac.engine.ruleset.MovementType;
import jac.engine.ruleset.Reactor;
import jac.engine.ruleset.Weapon;
import java.util.Map;

/**
 *
 * @author grjordan
 */
public class Unit_Plan {
    private final Chasis chasis;
    private final Reactor reactor;
    private final Armor armor;
    private final Weapon weapon;
    private final int max_health;
    private boolean prototyped = false;
    private final int cost;
    
    public Unit_Plan(Map<String, Tech> known_techs, Chasis chas, Reactor react, Armor def, Weapon weap){
        // Check to make sure inputs are 
        
        // Then set the variables
        chasis = chas;
        reactor = react;
        armor = def;
        weapon = weap;
        
        max_health = reactor.reactor_power()*10;  //TODO: This is correct.  I wonder if I should have the health multiplier stored in the rules file.
        
        //http://strategywiki.org/wiki/Sid_Meier%27s_Alpha_Centauri/Units is the formula I'm using.
        int def_cost = armor.cost_get();
        if(chasis.mode()==MovementType.SEA){
            def_cost = def_cost/2;
        }
        else if (chasis.mode()==MovementType.AIR){
            def_cost= def_cost*2;
        }
        
        int ability = 0;  // TODO: Have this load the ability costs.
        int wep_cost = def_cost;
        if(wep_cost*2 < def_cost){
            wep_cost = def_cost/2;
        }
        int r = react.reactor_power();
        int speed = chas.speed();
 
        int c = wep_cost * (def_cost + speed) * 10 / (2^(r + 1));
        
        if(chasis.mode()==MovementType.SEA){
           c = c / 2;
        }
        else if (chasis.mode()==MovementType.AIR){
            c = c / 4;
        }
        
        /*
         ; Special Unit Abilities

; Cost   = Cost factor of ability
;          1+ = Straight Cost; 25% increase per unit of cost
;           0 = None
;          -1 = Increases w/ ratio of weapon to armor: 0, 1, or 2.
;               Rounded DOWN. Never higher than 2.
;               Examples: For a W1,A2 unit, cost is 0
;                         For a W3,A2 unit, cost is 1 (3/2 rounded down)
;                         For a W6,A3 unit, cost is 2
;          -2 = Increases w/ weapon value
;          -3 = Increases w/ armor value
;          -4 = Increases w/ speed value
;          -5 = Increases w/ weapon+armor value
;          -6 = Increases w/ weapon+speed value
;          -7 = Increases w/ armor+speed value
        */
        if(ability > 0){
            c = c + (ability/4) * c;
        }
        else if(ability==-1){
            //TODO: All this stuff.
        }
                
        
        
        if(wep_cost>1 && def_cost>1){
            c = c + 10;
        }
        if(wep_cost>1 && def_cost>1 && speed >1){
            c = c + 10;
        }
        
        //Cmin = (R × 2 − R ÷ 2) × 10
        int cmin = (r*2 - r/2) * 10;
        
        if(cmin > c){
            cost = cmin;
        }
        else{
            cost = c;
        }
        
    }
    
    public int cost_get(){
        return cost;
    }
}
