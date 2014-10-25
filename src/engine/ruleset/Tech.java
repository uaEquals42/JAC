/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine.ruleset;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author grjordan
 */
public class Tech {
    
    /*
    ; TECHNOLOGY TREE

     ; Name, id, ai-mil, ai-tech, ai-infra, ai-colonize, preq(1), preq(2), flags
     ;
     ; Name     = Name of technology
     ;
     ; id       = 3 letter id code; this code is used when assigning
     ;            the tech as a prerequisite.
     ;
     ; power    = military value
     ; tech     = advance-of-knowledge value
     ; wealth   = infrastructure value
     ; growth   = colonization value
     ;
     ; preq(n)  = Prerequisite technology
     ;            a) 3 character id code of tech
     ;            b) "nil" to allow w/o prerequisite
     ;            c) "no" to disallow entirely from game
     ;
     ; flags    = Special tech flags
     ;            000000001 = "Secrets": first discoverer gains free tech
     ;            000000010 = Improves Probe Team success rate
     ;            000000100 = Increases commerce income
     ;            000001000 = Reveals map
     ;            000010000 = Allows gene warfare atrocity
     ;            000100000 = Increases intrinsic defense against gene warfare
     ;            001000000 = Increases ENERGY production in fungus
     ;            010000000 = Increases MINERALS production in fungus
     ;            100000000 = Increases NUTRIENT production in fungus
     ;
     #TECHNOLOGY
     Biogenetics,                Biogen,  0, 3, 2, 2, None,    None,    000100000
     Industrial Base,            Indust,  2, 1, 3, 0, None,    None,    000000000
     Information Networks,       InfNet,  0, 3, 2, 1, None,    None,    000000000
     Applied Physics,            Physic,  4, 2, 1, 0, None,    None,    000000000
     */
    String name;
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
    boolean probe_bonus = false;
    boolean commerce_bonus = false;
    boolean revealmap = false;
    boolean genewar_offence = false;
    boolean genewar_defence = false;
    boolean fungus_energy_bonus = false;
    boolean fungus_mineral_bonus = false;
    boolean fungus_nutrient_bonus = false;
    
    
    
    
    
    
}
