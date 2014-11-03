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
    /*
1,       ; Drones induced by Genejack factory
7,       ; Population limit w/o hab complex
14,      ; Population limit w/o hab dome
50,      ; Extra percentage cost of prototype LAND unit
50,      ; Extra percentage cost of prototype SEA unit
50,      ; Extra percentage cost of prototype AIR unit
3,2,     ; Psi combat offense-to-defense ratio (LAND unit defending)
1,1,     ; Psi combat offense-to-defense ratio (SEA unit defending)
1,1,     ; Psi combat offense-to-defense ratio (AIR unit defending)
10,      ; Players' starting energy reserves
25,      ; Combat % -> intrinsic base defense
0,       ; Combat % -> attacking along road
0,       ; Combat % -> for attacking from higher elevation
0,       ; Combat penalty % -> attacking from lower elevation
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
50,      ; Retool percent penalty for production change
2,       ; Retool strictness (0 = Always Free, 1 = Free in Category, 2 = Free if Project, 3 = Never Free)
10,      ; Retool exemption (first X minerals not affected by penalty)
20,      ; Minimum # of turns between councils
5,       ; Minerals for harvesting forest
8,       ; Territory: max distance from base
20,      ; Turns to corner Global Energy Market
CentPsi, ; Technology to improve fungus squares
CentPsi, ; Technology to ease fungus movement
CentEmp, ; Technology to build roads in fungus
Neural,  ; Technology to allow 2 special abilities for a unit
Gene,    ; Technology to allow 3 nutrients in a square
EcoEng,  ; Technology to allow 3 minerals in a square
EnvEcon, ; Technology to allow 3 energy in a square
Gravity, ; Technology to allow orbital insertion w/o Space Elevator
EcoEng2, ; Technology for +1 mining platform bonus
PlaEcon, ; Technology for economic victory
1,       ; If non-zero, probe teams can steal technologies
50,      ; Maximum % damage inflicted by arty versus units in base/bunker
99,      ; Maximum % damage inflicted by arty versus units in open
100,     ; Maximum % damage inflicted by arty versus units at sea
1, 1     ; Numerator/Denominator for frequency of global warming (1,2 would be "half" normal warming).
2100     ; Normal starting year
2600     ; Normal ending year for lowest 3 difficulty levels
2500     ; Normal ending year for highest 3 difficulty levels
1        ; If non-zero, obliterating a base counts as an atrocity
    */
}
