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
public class UnitAbility {
    int cost; // Doing this differently.  
    List<String> pre_reqs = new ArrayList<>();
    boolean land_unit;  // allowed for land units.
    boolean sea_unit;
    boolean air_unit;   
    boolean combat_unit;
    
    // Decided to remove the double negatives.
    boolean psi_unit;  // TODO: allowed for psi based units (is this for both offence and defence?) will need to check in game.
    
    // Broke it up into individual non-combat types.
    boolean terraformer_units; // allowed for terraformers
    boolean probe_units;
    boolean transport_units;
    boolean convoy_units;
    
    int max_speed_allowed = -1;  // there was a not allowed for fast-moving units flag.  Need to know what speed was considered fast.  -1 means no limit.
    
    boolean cost_increased_land; // increased cost for land units.  TODO: figure out the formula for this to use in teh unit_plan section.
    
    /*
           00000000001 = Allowed for Land units
;          00000000010 = Allowed for Sea units
;          00000000100 = Allowed for Air units
;          00000001000 = Allowed for Combat units
;          00000010000 = Allowed for Terraformer units
;          00000100000 = Allowed for Noncombat units (non-terraformer)
;          00001000000 = Not allowed for probe teams
;          00010000000 = Not allowed for psi units
;          00100000000 = Transport units only
;          01000000000 = Not allowed for fast-moving units
;          10000000000 = Cost increased for land units
    */
    
    public UnitAbility(Translation tran, String name, String abbreviation, String description, int cost_code, String pre_req, String smacFlags){
        // This is the function for 
    }
    
    // http://strategywiki.org/wiki/Sid_Meier%27s_Alpha_Centauri/Special_Ability
    /*
    #ABILITIES
Super Former,           1, EcoEng2,  Super,     00000010111, Terraform rate doubled
Deep Radar,             0, MilAlg,   ,          10000111111, Sees 2 spaces
Cloaking Device,        1, Surface,  Cloaked,   00001111001, Invisible; Ignores ZOCs
Amphibious Pods,        1, DocInit,  Amphibious,00000001001, Attacks from ship
Drop Pods,              2, MindMac,  Drop,      00000111001, Makes air drops
Air Superiority,        1, DocAir,   SAM,       00000001111, Attacks air units
Deep Pressure Hull,     1, Metal,    Sub,       00000111010, Operates underwater
Carrier Deck,           1, Metal,    Carrier,   00101101010, Mobile Airbase
AAA Tracking,           1, MilAlg,   AAA,       00010001011, x2 vs. air attacks
Comm Jammer,           -1, Subat,    ECM,       00010111001, +50% vs. fast units
Antigrav Struts,        1, Gravity,  Grav,      00000111001, +1 movement rate (or +Reactor*2 for Air)
Empath Song,            2, CentEmp,  Empath,    00010001111, +50% attack vs. Psi
Polymorphic Encryption, 1, Algor,    Secure,    00000111111, x2 cost to subvert
Fungicide Tanks,        1, Fossil,   Fungicidal,00000010111, Clear fungus at double speed
High Morale,            1, Integ,    Trained,   00000001111, Gains morale upgrade
Heavy Artillery,       -7, Poly,     Artillery, 00010001001, Bombards
Clean Reactor,          2, BioEng,   Clean,     00000111111, Requires no support
Blink Displacer,        1, Matter,   Blink,     00000001111, Bypass base defenses
Hypnotic Trance,       -1, Brain,    Trance,    00010111111, +50% defense vs. PSI
Heavy Transport,        1, Disable,  Heavy,     00100100111, +50% transport capacity
Nerve Gas Pods,         1, Chemist,  X,         00011001101, Can +50% offense (Atrocity)
Repair Bay,             1, Metal,    Repair,    00100100111, Repairs ground units on board
Non-Lethal Methods,     1, Integ,    Police,    00000001001, x2 Police powers
    */
    
 
}
