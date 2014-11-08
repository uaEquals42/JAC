/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jac.engine.ruleset;

import jac.engine.dialog.Quote;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author grjordan
 */
public class Tech {
   
    
    String id;
    
    // these 4 values are hints to the ai to indicate how good the particular tech is in each category.
    // Determines how much a particular ai wants each tech.
    int power;
    int tech;
    int infrastructure;
    int colonize;
    

    
    /**
     * An empty list means there are no pre-requisites.
     */
    List<Tech> pre_requisites = new ArrayList<>(); 
    List<String> pre_requisites_names = new ArrayList<>(); // I might have to use this for the xml files.
    
    // I figure doing it this way will increase the modability for modders.
    boolean freetech = false;
    int probe_bonus = 0;
    int commerce_bonus = 0;
    boolean revealmap = false;
    boolean genewar_offence = false;
    boolean genewar_defence = false;
    int fungus_energy_bonus = 0;
    int fungus_mineral_bonus = 0;
    int fungus_nutrient_bonus = 0;
    
    Translation tran;
    
    // Smac/x does reaserch costs via a formula.
    boolean cost_from_formula = true;  
    int reasearch_cost = 5;  // Added this here for modders, so that if they want to have fixed research costs, they can.
    
    Tech(Translation tran, String id, String name, List<Quote> quotes, List<String> pre_reqs, boolean freetech, int probe_bonus, 
            int commerce_bonus, boolean revealmap, boolean genewar_offence, boolean genewar_defence,
            int fungus_energy_bonus, int fungus_mineral_bonus,int fungus_nutrient_bonus, int power, int tech, int infrastructure, int colonize){
        
       
        this.id = id;
        this.pre_requisites_names.addAll(pre_reqs);
        this.freetech = freetech;
        this.probe_bonus = probe_bonus;
        this.commerce_bonus = commerce_bonus;
        this.revealmap = revealmap;
        this.genewar_offence = genewar_offence;
        this.genewar_defence = genewar_defence;
        this.fungus_energy_bonus = fungus_energy_bonus;
        this.fungus_mineral_bonus = fungus_mineral_bonus;
        this.fungus_nutrient_bonus = fungus_nutrient_bonus;
        this.power = power;
        this.tech = tech;
        this.infrastructure = infrastructure;
        this.colonize = colonize;
        
        // Do not put in xml file.
        this.tran = tran;
        tran.tech_quotes.put(id, quotes);
        tran.technames.put(id, name);
        
        
    }
    
    /**
     * Used to set/change the translation being used.
     * @param tran 
     */
    public void set_Translation(Translation tran){
        this.tran = tran;
    }
    
    public String getName(){
        return tran.technames.get(id);
    }
    
    public List<Quote> getQuotes(){
        return tran.tech_quotes.get(id);
    }
    
    
}
