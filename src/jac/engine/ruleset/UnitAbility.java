/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jac.engine.ruleset;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author grjordan
 */
public class UnitAbility {

    int cost; // Doing this differently.  // TODO: Figure out a better way to store the cost, make it more flexible (or clear) as to what effect we want.
    int cost_code;
    List<String> pre_reqs = new ArrayList<>();
    boolean land_unit=true;  // allowed for land units.
    boolean sea_unit=true;
    boolean air_unit=true;
    boolean combat_unit=true;
   
    String key;

    // Decided to remove the double negatives.
    boolean psi_unit=true;  // TODO: allowed for psi based units (is this for both offence and defence?) will need to check in game.

    // Broke it up into individual non-combat types.
    boolean terraformer_units=true; // allowed for terraformers
    boolean probe_units=true;
    boolean transport_units=true;
    boolean convoy_unit=true;

    int max_speed_allowed = -1;  // there was a not allowed for fast-moving units flag.  Need to know what speed was considered fast.  -1 means no limit.

    boolean cost_increased_land=false; // increased cost for land units.  TODO: figure out the formula for this to use in teh unit_plan section.

   
    /**
     * This is the version for importing from SMAC files. Will have to create a
     * different version for the editor.
     *
     * @param tran
     * @param name
     * @param abbreviation
     * @param description
     * @param cost_code
     * @param pre_req
     * @param smacFlags
     */
    public UnitAbility(Translation tran, String name, String abbreviation, String description, int cost_code, String pre_req, String smacFlags) {
        String[] basic = new String[4];
        basic[0] = name.trim();
        basic[1] = abbreviation.trim();
        basic[2] = description.trim();
        this.key = name.trim().toUpperCase(Locale.ENGLISH);
        tran.unit_abilities.put(key, basic);

        
        this.cost_code = cost_code;
        
        if(!pre_req.trim().equalsIgnoreCase("None")){
            pre_reqs.add(pre_req);
        }
        
        
        
        smacFlags = smacFlags.trim();
        if (smacFlags.charAt(0) == '1') {
            cost_increased_land = true;
        }
        if (smacFlags.charAt(1) == '1') {
            max_speed_allowed = 1;
        }
        if (smacFlags.charAt(2) == '1') {
            combat_unit = false;
            terraformer_units=false;
            probe_units=false;
            convoy_unit=false;
        }
        if (smacFlags.charAt(3) == '1') {
            //Not allowed for psi units
            psi_unit = false;

        }
        if (smacFlags.charAt(4) == '1') {
            probe_units=false;
        }
        if (smacFlags.charAt(5) == '0') {
            probe_units=false;
            convoy_unit=false;
            transport_units = false;

        }
        if (smacFlags.charAt(6) == '0') {
            terraformer_units=false;
        }
        if (smacFlags.charAt(7) == '0') {
            combat_unit = false;

        }
        if (smacFlags.charAt(8) == '0') {
            air_unit=false;

        }
        if (smacFlags.charAt(9) == '0') {
            sea_unit=false;

        }
        if (smacFlags.charAt(10) == '0') {
            land_unit=false;
        }
        
        
        //TODO: Get the effects stored in here somehow.

    }

    // http://strategywiki.org/wiki/Sid_Meier%27s_Alpha_Centauri/Special_Ability

}
