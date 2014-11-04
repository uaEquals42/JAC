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
    boolean non_combat_units; // That are not terraformers.  Convoys, probes, transport.
    boolean terraformer_units; // allowed for terraformers
    boolean not_allowed_probe;
    boolean not_allowed_psi;
    boolean transport_only;
    boolean not_allowed_fast_units;
    boolean cost_increased_land; // increased cost for land units.
    
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
 
}
