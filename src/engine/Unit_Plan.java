/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

import engine.ruleset.Armor;
import engine.ruleset.Chasis;
import engine.ruleset.MovementType;
import engine.ruleset.Reactor;
import engine.ruleset.Weapon;
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
        
        int abilility = 0;  // TODO: Have this load the ability costs.
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
        
        c = c + (abilility/4) * c;
        
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
