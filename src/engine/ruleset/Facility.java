/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine.ruleset;

/**
 *
 * @author grjordan
 */
public class Facility {
    /*
     ; Base Facilities
     ;
     ; Name, Cost, Maint, Preq, Free, Effect
     ;
     ; Name  = Name of facility type
     ; Cost  = Construction cost in minerals (x Minerals multiplier in RULES)
     ; Maint = Maintenance cost in energy per turn
     ; Preq  = Technology prerequisite (see TECHNOLOGY)
     ; Free  = No longer supported.
     ; Effect= Brief description of effect
    
     #FACILITIES
     Headquarters,                  5, 0, None,    Disable,  Efficiency
     Children's Creche,             5, 1, EthCalc, Disable,  Growth/Effic/Morale
     Recycling Tanks,               4, 0, Biogen,  EcoEng2,  Bonus Resources
     Perimeter Defense,             5, 0, DocLoy,  Disable,  Defense +100%
    
     The Human Genome Project,     20, 0, Biogen,  Disable,  +1 Talent Each Base,            -1, 0, 0, 1, 1,
     The Command Nexus,            20, 0, DocLoy,  Disable,  Command Center Each Base,        1, 2, 0,-1, 0,
     The Weather Paradigm,         20, 0, Ecology, Disable,  Terraform Rate +50%,             0, 0, 0, 2, 1,
     The Merchant Exchange,        20, 0, Indust,  Disable,  +1 Energy Each Square Here,      0, 0, 1, 2, 0,
     The Empath Guild,             20, 0, CentEmp, Disable,  Commlink For Every Faction,     -2, 0, 0, 0, 0,
     The Citizens' Defense Force,  30, 0, Integ,   Disable,  Perimeter Defense Each Base,     0, 1, 0, 0, 0,
     The Virtual World,            30, 0, PlaNets, Disable,  Network Nodes Help Drones,       0, 0, 2, 0, 0,
     The Planetary Transit System, 30, 0, IndAuto, Disable,  New Bases Begin At Size 3,       0, 0, 0, 1, 2,
     The Xenoempathy Dome,         30, 0, CentMed, Disable,  Fungus Movement Bonus,           0,-1, 0, 0, 2,
    */
    
    boolean secret_project = false;
    
    void Facility(String name, int cost, int maintence, Tech prereq){
        
        
        
    }
    
    
}
