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
public class Facility {
    
    int key;
    boolean secret_project = false;
    int cost;
    int maintence;
    List<String> pre_reqs = new ArrayList<>();
    Translation tran;
    
    
    void Facility(int key, Translation tran, String name, String short_description, int cost, int maintence, String prereq, boolean secret_project){
        this.cost = cost;
        this.maintence = maintence;
        pre_reqs.add(prereq);
        this.secret_project = secret_project;
        this.tran = tran;
        this.key = key;
        String[] words = new String[2];
        words[0] = name;
        words[1] = short_description;
        tran.facilities.put(key, words);
        
    }
    
    
}
