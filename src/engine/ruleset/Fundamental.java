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
public class Fundamental {

    int road_movement_rate;
    int nutrient_intake_per_citizen; //Nutrient intake requirement for citizens
    // TODO:  3,2      ; Numerator & Denominator for artillery fire damage
    int max_artillery_range;
    int max_airdrop_range; // without orbital insertion.
    int nutrient_cost_multiplier; // ??????
    int mineral_cost_multiplier; // ??????
    int technology_discover_rate = 100; // Technology discovery rate as a percentage of standard
    int min_increase_limit;  // Limits mineral increase for mine without road in square
    int mine_nutrient_effect; //(0 or -1)
    int min_base_size_specialists; // 5,       ; Minimum base size to support specialists

// 1,       ; Drones induced by Genejack factory  This should be loaded into the facility data.
//7,       ; Population limit w/o hab complex   This should be loaded into the facility data.
//14,      ; Population limit w/o hab dome      This should be loaded into the facility data.
    int land_prototype; // Extra percentage cost of prototype land unit.
    int sea_prototype;  // "         "        "  "     "      sea
    int air_prototype;  // "         "        "  "     "      air

    int[] psi_ratio_attack_land; // array of size 2.  Land unit defending.
    int[] psi_ratio_attack_sea;  // sea unit defending.
    int[] psi_ratio_attack_air; // air unit defending.

    int default_starting_energy; //Players' starting energy reserves.  Should be a game start option as well.
    int intrinsic_base_defence;  //Bonus for defenders in a friendly base.
    int road_attack_bonus;  // bonus for attacking along a road.
    int higher_elevation_bonus; // % bonus for attacking from high ground.
    int lower_elevation_penalty; // % penalty when defending from lower ground.

        //TODO:  I'm wondering if I can abstract this bonus stuff out so that adding unit types is easier.
        /*
     25,      ; Combat % -> Mobile unit in open ground
     0,       ; Combat % -> Defend vs. mobile in rough
     25,      ; Combat % -> Infantry vs. Base
     50,      ; Combat penalty % -> attack after airdrop
     25,      ; Combat % -> Fanatic attack bonus
     50,      ; Combat % -> Land based guns vs. ship artillery bonus
     25,      ; Combat % -> Artillery bonus per level of altitude
     50,      ; Combat % -> Trance bonus defending vs. psi
     50,      ; Combat % -> Empath Song bonus attacking vs. psi
     50,      ; Combat penalty % -> Air superiority unit vs. ground unit
     100,     ; Combat % -> Air superiority unit vs. air unit
     50,      ; Combat penalty % -> Non-combat unit defending vs. combat unit
     50,      ; Combat % -> Comm Jammer unit defending vs. mobile unit
     100,     ; Combat % -> Bonus vs. ships caught in port
     100,     ; Combat % -> AAA bonus vs. air units
     25,      ; Combat % -> Defend in range of friendly Sensor
     10,      ; Combat % -> Psi attack bonus/penalty per +PLANET
     */
    int retool_percent_cost; // % of minerals lost when retooling for a production change.
    int retool_stricness; // (0 = Always Free, 1 = Free in Category, 2 = Free if Project, 3 = Never Free) TODO: Probably should change the values to an enum.
    int retool_exception;  // first x minerals not affected by retooling penalty.

    int turns_between_councils;  // Minimum # of turns between councils.
    int forest_minerals; // Minerals gathered when you harvest a forest.
    int territory_max;  // Territory distanse from a base.
    int global_energy_turns; // # of turns to corner the   int global_energy_turns; // # of turns to cornerglobal energy market victory.

    // TODO:  These vars should probably be something else for flexibility.
    String tech_fungus_improve; //Build improvments in fungus squares.
    String tech_fungus_move;    //Move at full speed in fungus.
    String tech_fungus_roads;  // build roads in fungus

    String tech_allow_2_abilities; //TODO: I KNOW this should be stored in the tech object.  and it should be for x abilities.
    String tech_allow_3_nutrients; //TODO: I KNOW this should be stored in the tech object.
    String tech_allow_3_minerals;//TODO: I KNOW this should be stored in the tech object.
    String tech_allow_3_energy;//TODO: I KNOW this should be stored in the tech object.
    String tech_allow_orbitial_insertion;//TODO: I KNOW this should be stored in the tech object.
    String tech_allow_mining_platform_bonus;//TODO: I KNOW this should be stored in the tech object.
    String tech_allow_economic_vicotry;//TODO: I KNOW this should be stored in the tech object.

    boolean probe_teams_steal_tech;

    int max_arty_damage_vs_base;
    int max_arty_damage_vs_land;
    int max_arty_damage_vs_sea;

    int global_warming_level; // This will be a modifier TBD for determining when global warming will take place.

    int starting_year;
    int late_ending_year;
    int early_ending_year;
    boolean base_razing_atrocity;  // Is razing a base an atrocity?

}
