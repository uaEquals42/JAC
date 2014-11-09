/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jac.engine.ruleset;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author grjordan
 */
public class Armor {
    
    private Translation tran;
    private int id;
    private int armor;
    private DefenceMode mode;
    private int cost;
    private List<String> pre_reqs = new ArrayList<>();
    
    public Armor(Translation tran, int id, int armor, DefenceMode mode, int cost, String pre_req, String name1, String name2){
        this.tran = tran;
        this.id = id;
        if (!pre_req.equalsIgnoreCase("None")) {
            pre_reqs.add(pre_req);
        }
        this.armor = armor;
        this.mode = mode;
        String[] names = new String[2];
        names[0] = name1.trim();
        names[1] = name2.trim();
        tran.armor.put(id, names);
    }
    
    
    public String name1(){
        return tran.armor.get(id)[0];
    }
    
    public String name2(){
        return tran.armor.get(id)[1];
    }

    public int getArmor() {
        return armor;
    }

    public DefenceMode getMode() {
        return mode;
    }

    public int getCost() {
        return cost;
    }
    
    
}
