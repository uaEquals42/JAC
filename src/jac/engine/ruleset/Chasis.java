/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jac.engine.ruleset;

import jac.engine.dialog.Noun;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author grjordan
 */
public class Chasis {

    private int speed;
    private MovementType triad;
    private boolean missle;
    private int base_cargo;
    private int cost;
    private List<String> pre_req_str = new ArrayList<>();
    private List<Tech> pre_req_techs = new ArrayList<>();
    private final int key;  // for looking up translations.
    private Translation tran;

    Chasis(Translation tran, int key, List<Noun> names, int speed, MovementType triad, boolean missle, int base_cargo, int cost, String pre_req) {
        this.key = key;
        this.tran = tran;
        tran.chasis.put(key, names);
        this.missle = missle;
        this.base_cargo = base_cargo;
        this.cost = cost;
        this.speed = speed;
        this.triad = triad;
        if(pre_req.trim().length() > 0){
            if(!pre_req.equalsIgnoreCase("None")){
                pre_req_str.add(pre_req.trim());
            }
            
        }
    }
    
    public int key(){
        return key;
    }
    public List<Noun> names(){
        return tran.chasis.get(key);
    }

    public List<String> getPre_req_str() {
        return pre_req_str;
    }
    
    public int getBase_cargo(){
        return base_cargo;
    }

    public int getCost() {
        return cost;
    }

    public int getSpeed() {
        return speed;
    }

    public MovementType getTriad() {
        return triad;
    }

    public boolean isMissle() {
        return missle;
    }
    
    public MovementType mode(){
        return triad;
    }
    


}
