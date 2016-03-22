/*
 * JAC Copyright (C) 2014 Gregory Jordan
 *
 * This file is part of JAC.
 *
 * JAC is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package jac.engine.ruleset;

import jac.Enum.*;
import jac.engine.FileHelpers;
import jac.engine.dialog.Noun;
import jac.engine.dialog.Quote;
import jac.engine.mapstuff.TerrainBaseState;
import jac.engine.mapstuff.TerrainModifier;
import jac.engine.mapstuff.Terrainstat;
import jac.unit.*;
import jac.unit.effectRules.EffectNode;
import jac.unit.effectRules.Value;
import jac.unit.partTranslation.AbilityTranslation;
import jac.unit.partTranslation.ChassisTranslation;
import jac.unit.partTranslation.FacilityTranslation;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Gregory Jordan
 */
public class Ruleset {
    static final Path RULESET_LOCATION = Paths.get("./Rulesets/");
            
    static Logger log = LoggerFactory.getLogger(Ruleset.class);
    
    private final String ruleset_name;
    
    private final List<Ideology> ideologies;
    private final Map<String, Tech> technologies;

    private final Map<String, Chassis> chassis;
    private final Map<String, Reactor> reactors;
    private final Map<String, Armor> armors;
    private final Map<String, Weapon> weapons;
    private final Map<String, UnitAbility> unitAbilities;
    private final Map<String, Facility> facilities;
    
    private final Map<String, Unit_Plan> unitPlans;

    private final Map<String, Terrainstat> basicTerrainStates;
    private final Map<String, Terrainstat> terrainModifiers;
    
   


    public Map<String, Terrainstat> getBasicTerrainStates() {
        return basicTerrainStates;
    }

    public Map<String, Terrainstat> getTerrainModifiers() {
        return terrainModifiers;
    }


    public void toJoson() throws IOException {

        Path saveLocation = RULESET_LOCATION.resolve(ruleset_name);
        FileHelpers.create_folder(saveLocation);

        FileHelpers.list_to_json(saveLocation.resolve("ideologies"), ideologies);
              
    }
    

    public Tech find_tech(String key) {
        return technologies.get(key);
    }

    private Ruleset(Builder build) {
        ruleset_name = build.ruleset_name;
        ideologies = build.ideologies;
        technologies = build.technologies;

        chassis = build.chasises;
        reactors = build.reactors;
        armors = build.armors;
        weapons = build.weapons;
        unitAbilities = build.unit_abilities;
        facilities = build.facilities;
        basicTerrainStates = build.basicTerrainStates;
        terrainModifiers = build.terrainModifiers;

        this.unitPlans = build.unitPlans;
    }

    public Map<String, UnitAbility> getUnitAbilities() {
        return unitAbilities;
    }

    public Map<String, Unit_Plan> getUnitPlans() {
        return unitPlans;
    }

    public List<Ideology> getIdeologies() {
        return ideologies;
    }

    public Map<String, Chassis> getChassis() {
        return chassis;
    }

    public Map<String, Reactor> getReactors() {
        return reactors;
    }

    public Map<String, Armor> getArmors() {
        return armors;
    }

    public Map<String, Weapon> getWeapons() {
        return weapons;
    }



    public Map<String, Tech> getTechnologies() {
        return technologies;
    }

    public Map<String, UnitAbility> getUnit_abilities() {
        return unitAbilities;
    }

    public Map<String, Facility> getFacilities() {
        return facilities;
    }
    
    
  
    public static class Builder {
        static Locale FILELOCALE = Locale.ENGLISH;
        
        private final int SMAC_MP_COST_MULTIPLIER = 3;  // TODO: Load this from SMAC file.
        private String ruleset_name;
        private List<Ideology> ideologies = new ArrayList<>();
        private Map<String, Tech> technologies = new HashMap<>();

        private Map<String, Chassis> chasises = new LinkedHashMap<>();
        private Map<String, Reactor> reactors = new LinkedHashMap<>();
        private Map<String, Armor> armors = new LinkedHashMap<>();
        private Map<String, Weapon> weapons = new LinkedHashMap<>();
        private Map<String, UnitAbility> unit_abilities = new LinkedHashMap<>();
        private Map<String, Facility> facilities = new LinkedHashMap<>();
      
        private Map<String, Unit_Plan> unitPlans = new LinkedHashMap<>();
        
        private  Map<String, Terrainstat> basicTerrainStates = new LinkedHashMap<>();
        private  Map<String, Terrainstat> terrainModifiers = new LinkedHashMap<>();

        public Ruleset loadalpha_txt(String filename) throws SectionNotFoundException, IOException {
            log.debug("loadalpha_txt: {}", filename);
            ruleset_name = "SMAC";
            Path path = Paths.get(filename);

            List<String> input = Files.readAllLines(path, StandardCharsets.ISO_8859_1);
            //tran = new Translation(Locale.ENGLISH);

            // load these in first.
            Map<String, List<Quote>> blurbs = load_blurbs_txt(path.resolveSibling("Blurbs.txt"));
            //tran.opening_quote = blurbs.get("#OPENING").get(0);

            Map<String, String> techlongs = load_techlongs(path.resolveSibling("TECHLONGS.TXT"));

            load_ideologies(input);
            //load_technologies(input, blurbs);
            load_facilities(input, blurbs);
            load_chassis(input);
            load_reactor(input);
            load_armor(input);
            load_weapon(input);
            load_unit_abilities(input);
            load_unitDesigns(input);
            basicTerrainStates = load_BasicTerrainTypes();
            terrainModifiers = load_TerrainModifiers();    
            return new Ruleset(this);
            
        }

        public Ruleset loadalphax_txt() throws SectionNotFoundException, IOException {
        //TODO: implement load alphax.txt
            //TODO: Load SMAC ideologies from SOCIO
            log.warn("Function not implemented yet.");
            ruleset_name = "SMACX";
            return new Ruleset(this);
        }

       

        /**
         * Will find the first line that equals the string tag.
         *
         * @param tag The line you are looking for.
         * @param input The list of strings you are searching.
         * @return The line number where tag was found. Returns -1 if it wasn't
         * found.
         */
        private int gotosection(String tag, List<String> input) throws SectionNotFoundException {
            for (int line = 0; line < input.size(); line++) {
                if (input.get(line).trim().equalsIgnoreCase(tag)) {
                    log.trace("gotosection tag: {}  line: {}", tag, line);
                    return line;
                }
            }

            log.error("Section {} not found!", tag);
            throw new SectionNotFoundException();  // TODO:  Change this to an exception?
        }

        
        private Map<String, Terrainstat> load_BasicTerrainTypes(){
            Map<String, Terrainstat> basicterraintypes = new HashMap<>();
            
            basicterraintypes.put("dirt", new TerrainBaseState.Builder("dirt", 3).build());
            basicterraintypes.put("rock", new TerrainBaseState.Builder("rock", 3).build());
              
            return basicterraintypes;
        }
        
        private Map<String, Terrainstat> load_TerrainModifiers(){
            Map<String, Terrainstat> terrainMods = new HashMap<>();
            
            terrainMods.put("fungus", new TerrainModifier.Builder("fungus", 9).build());
            terrainMods.put("road", new TerrainModifier.Builder("road", 1).setMinMPCost(1).build());
            terrainMods.put("mag", new TerrainModifier.Builder("mag", 0).setMinMPCost(0).build());
            
            
            return terrainMods;
        }
        
        private Map<String, String> load_techlongs(Path location) throws IOException {
            log.debug("Load techlongs: {}", location);
            List<String> input = Files.readAllLines(location, StandardCharsets.ISO_8859_1);
            Map<String, String> techlongs = new HashMap<>();
            for (int ii = 0; ii < input.size(); ii++) {
                if (input.get(ii).contains("##")) {
                    String entry = "";
                    ii++;
                    String key = input.get(ii).trim();
                    ii++;

                    for (; input.get(ii).trim().length() > 0; ii++) {
                        entry = entry + input.get(ii);
                    }
                    //log.trace(entry);
                    techlongs.put(key, entry);
                }
            }
            log.trace("Loaded {} techlongs", techlongs.size());
            return techlongs;
        }

        private Map<String, List<Quote>> load_blurbs_txt(Path location) throws IOException {
            // Blurbs.txt
            Map<String, List<Quote>> blurbs = new HashMap<>();
            log.debug("Load blurbs");
            List<String> input = Files.readAllLines(location, StandardCharsets.ISO_8859_1);

            for (int ii = 0; ii < input.size(); ii++) {
                if (input.get(ii).contains("##")) {
                    ii++;
                    String key = input.get(ii).trim();
                    ii++;
                    List<Quote> quotes = Quote.readblurb(ii, input);

                    blurbs.put(key, quotes);
                }
            }
            return blurbs;
        }

        private void load_unitDesigns(List<String> input) throws SectionNotFoundException {
            log.debug("Load unit Designs");
            int pos = gotosection("#UNITS", input);
            pos++;
            
            pos++;
            for (; !input.get(pos).trim().isEmpty(); pos++) {
                String[] col = input.get(pos).split(",");
                
                //Name, Chassis, Weapon, Armor, Plan, Cost, Carry, Preq, Icon, Abil
                //Colony Pod, Infantry, Colony Pod,   Scout,      8, 0, 0, None,    -1, 000000000000000000000000
                 String name = col[0].trim();
                 String chassis = col[1].trim();
                 String weapon = col[2].trim();
                 String armor = col[3].trim();
                 AiUnitPlan unitRole = AiUnitPlan.convert(col[4]);
                 
                 Integer check = Integer.parseInt(col[5].trim());
                 EffectNode<Integer> costOverride;
                 if(check!=0){
                     costOverride = new Value<>(check);
                     //TODO: Implement cost override for unitplan.  ARGGGGGG.
                 }
                 
                 check = Integer.parseInt(col[6].trim());
                 EffectNode<Integer> carryCapacityOverride;
                 if(check!=0){
                     carryCapacityOverride = new Value<>(check);
                     //TODO: Implement Capacity Override for unitplan.
                 }
                 
                 String prereqTech = col[7].trim();
                 // TODO: Get the disabled, and none working here.
                 
                 Unit_Plan thePlan = new Unit_Plan.Builder(chassis,"Fission" , armor, weapon).prototyped(true).build();
                 

                 unitPlans.put(name, thePlan);
                 
                 
                 
             }
        }
        
        /**
         * This is for loading in the facilities and secret projects.
         *
         * @param input - the file being read as a list of strings. Should be
         * alpha.txt
         * @param blurbs - List of quotes. Will add the appropriate ones to the
         * facility info.
         * @throws SectionNotFoundException Will throw if it can't find the
         * "#FACILITIES" section.
         */
        private void load_facilities(List<String> input, Map<String, List<Quote>> blurbs) throws SectionNotFoundException {
            int pos = gotosection("#FACILITIES", input);
            pos++;
            int facility_count = 0;
            int secret_count = 0;
            for (; !input.get(pos).trim().isEmpty(); pos++) {
                String[] row = input.get(pos).split(",");

                int cost = Integer.parseInt(row[1].trim());
                int maintence = Integer.parseInt(row[2].trim());

                Facility tmp_facility;
                // calculate the key we will be needing.
                String key;
                if (row.length == 11) {
                    // then it is a secret project.
                    key = "#PROJECT" + secret_count;
                    GenericPart generalPartDetails = new GenericPart.Builder(key, cost)
                            .addPreRequisiteTech(row[3])
                            .build();
                    FacilityTranslation ftrans = new FacilityTranslation(FILELOCALE, new Noun(row[0], NounSex.NEUTER_SINGULAR) , row[5], blurbs.get(key));
                    tmp_facility = new Facility.Builder(generalPartDetails, cost, maintence, ftrans)
                            .project()
                            .build();

                    secret_count++;
                } else {
                    key = "#FAC" + facility_count;
                    FacilityTranslation ftrans = new FacilityTranslation(FILELOCALE, new Noun(row[0], NounSex.NEUTER_SINGULAR), row[5], blurbs.get(key));
                    GenericPart generalPartDetails = new GenericPart.Builder(key, cost)
                            .addPreRequisiteTech(row[3])
                            .build();
                    tmp_facility = new Facility.Builder(generalPartDetails, cost, maintence, ftrans)
                            .build();

                    facility_count++;
                }

                facilities.put(key, tmp_facility);

            }

        }

        private void load_weapon(List<String> input) throws SectionNotFoundException {
            int pos = gotosection("#WEAPONS", input);
            pos++;
            for (; !input.get(pos).trim().isEmpty(); pos++) {
                String[] line = input.get(pos).split(",");
                WeaponRole mode;
                int damage = Integer.parseInt(line[2].trim());

                if (damage == -1) {
                    mode = WeaponRole.PSI;
                } else {
                    mode = WeaponRole.convert(Integer.parseInt(line[3].trim()));
                }
                String key = line[1].trim();
                int cost = Integer.parseInt(line[4].trim());
                GenericPart generalPartDetails = new GenericPart.Builder(key, cost)
                            .addPreRequisiteTech(line[5])
                            .build();
                weapons.put(key, new Weapon.Builder(generalPartDetails, damage, mode, Locale.ENGLISH, new Noun(line[0], NounSex.NEUTER_SINGULAR), new Noun(line[1], NounSex.NEUTER_SINGULAR)).build());  
            }
            log.trace("Loaded {} weapons", weapons.size());

        }

        private void load_unit_abilities(List<String> input) throws SectionNotFoundException {
            int pos = gotosection("#ABILITIES", input);
            pos++;

            for (; !input.get(pos).trim().isEmpty(); pos++) {
                String[] line = input.get(pos).split(",");
                String key =line[0].trim().toUpperCase(Locale.ENGLISH);
                if (!line[2].trim().equalsIgnoreCase("Disable")) {
                     GenericPart generalPartDetails = new GenericPart.Builder(key, Integer.parseInt(line[1].trim()))
                            .addPreRequisiteTech(line[2])
                            .smacAbilityFlags(line[4])
                            .build();
                    UnitAbility abile = new UnitAbility.Builder(generalPartDetails, new AbilityTranslation(FILELOCALE, new Noun(line[0],NounSex.NEUTER_SINGULAR), line[5])).build();
                            

                    unit_abilities.put(key, abile);

                }

            }

        }

        private void load_armor(List<String> input) throws SectionNotFoundException {
            int pos = gotosection("#DEFENSES", input);
            pos++;

            for (; !input.get(pos).trim().isEmpty(); pos++) {
                String[] line = input.get(pos).split(",");
                DefenceMode mode;
                int rating = Integer.parseInt(line[2].trim());

                if (rating == -1) {
                    mode = DefenceMode.PSI;

                } else {
                    mode = DefenceMode.convert(Integer.parseInt(line[3].trim()));
                }
                String key = line[1].trim();
                int cost = Integer.parseInt(line[4].trim());
                armors.put(key,
                        new Armor.Builder(new GenericPart.Builder(key, cost)
                                .addPreRequisiteTech(line[5])
                                .build(), rating, mode, line[0], line[1])
                        .build());

            }
            log.trace("Loaded {} armors.", armors.size());

        }

        private void load_reactor(List<String> input) throws SectionNotFoundException {
            int pos = gotosection("#REACTORS", input);
            pos++;

            for (; !input.get(pos).trim().isEmpty(); pos++) {
                String[] line = input.get(pos).split(",");
                String key = line[1].trim();
                int cost_power = Integer.parseInt(line[2].trim());
                Noun fullname = new Noun(line[0]);
                Noun shortname = new Noun(line[1]);
                GenericPart genstats = new GenericPart.Builder(key, cost_power)
                        .addPreRequisiteTech(line[3])
                        .build();
                Reactor tmp = new Reactor.Builder(genstats, cost_power, Locale.ENGLISH, fullname, shortname).build();
       
               
                reactors.put(key, tmp);
            }

        }

        private void load_chassis(List<String> input) throws SectionNotFoundException {
            int pos = gotosection("#CHASSIS", input);
            pos++;
            
            for (; !input.get(pos).trim().isEmpty(); pos++) {
                
                String[] line = input.get(pos).split(",");
                String key = line[0].trim();
           
      
               

                Value speed = new Value(Integer.parseInt(line[8].trim()) * SMAC_MP_COST_MULTIPLIER);
                Domain domain = Domain.convert(Integer.parseInt(line[9].trim()));
                int range = Integer.parseInt(line[10].trim());
                boolean missle = line[11].trim().equals("1");
                Value cargo = new Value(Integer.parseInt(line[12].trim()));
                int cost = Integer.parseInt(line[13].trim());
                String pre_req = line[14].trim();
         
                ChassisTranslation names = new ChassisTranslation(FILELOCALE, new Noun(line[0], line[1]), new Noun(line[2], line[3]),new Noun(line[15], line[16]), new Noun(line[4], line[5]),new Noun(line[6], line[7]),new Noun(line[17], line[18]) );

                int damage = 0;
                if (key.equalsIgnoreCase("Needlejet")) {
                    damage = 100;
                }
                if (key.equalsIgnoreCase("'Copter")) {
                    damage = 25; // TODO: Verify that it is 25% for helicoptors.
                }
                if (key.equalsIgnoreCase("Missile")) {
                    damage = 100;
                }
                Effect localEffect = new Effect.Builder().
                        setIntFlag(IntNames.CARGO_CAPACITY, cargo).
                        setIntFlag(IntNames.SPEED, speed).
                        build();

                
                GenericPart genstats = new GenericPart.Builder(key, cost)
                        .addPreRequisiteTech(pre_req)
                        .setLocalEffects(localEffect)
                        .build();
                
                chasises.put(key,                      
                        new Chassis.Builder(genstats, domain, names)     
                        .ismissle(missle)
                        .setRange(range)
                        .damageDonewhenOutofrange(damage)
                        .build()
                );

            }

        }

        /*
        private void load_technologies(List<String> input, Map<String, List<Quote>> blurbs) throws SectionNotFoundException {
            int pos = gotosection("#TECHNOLOGY", input);
            pos++;

            for (int key = 0; !input.get(pos + key).trim().isEmpty(); key++) {
                String[] row = input.get(pos + key).split(",");
                String id = "#TECH" + key;
                if (!row[6].trim().equalsIgnoreCase("Disable")) {
                    List<String> pre_reqs = new ArrayList<>();
                    if (!row[6].trim().equalsIgnoreCase("None")) {
                        pre_reqs.add(row[6].trim());
                    }
                    if (!row[7].trim().equalsIgnoreCase("None")) {
                        pre_reqs.add(row[7].trim());
                    }

                    char[] flags = row[8].trim().toCharArray();
                    int probe = Integer.parseInt(row[8].trim().substring(7, 8));
                    int commerce_bonus = Integer.parseInt(row[8].trim().substring(6, 7));
                    int fungus_nutrient_bonus = Integer.parseInt(row[8].trim().substring(0, 1));
                    int fungus_mineral_bonus = Integer.parseInt(row[8].trim().substring(1, 2));
                    int fungus_energy_bonus = Integer.parseInt(row[8].trim().substring(2, 3));
                    Tech new_tech;

                    new_tech = new Tech(tran, id, row[0].trim(), blurbs.get(id), pre_reqs,
                            flags[8] == '1', probe, commerce_bonus,
                            flags[5] == '1', flags[3] == '1', flags[4] == '1',
                            fungus_energy_bonus, fungus_mineral_bonus,
                            fungus_nutrient_bonus, Integer.parseInt(row[2].trim()),
                            Integer.parseInt(row[3].trim()), Integer.parseInt(row[4].trim()),
                            Integer.parseInt(row[5].trim()));
                    //tran.technames.put(id, row[0].trim());
                    technologies.put(id, new_tech);
                }

            }

        }
        */

        private void load_ideologies(List<String> input) throws SectionNotFoundException {
            int pos = gotosection("#SOCIO", input);
            pos += 3;

            String[] categories = input.get(pos).split(",");
            pos++;
            for (String cat : categories) {
                for (int i = 0; i < 4; i++) {
                    String[] tmp = input.get(pos).split(",");
                    String idol_name = tmp[0].trim();

                    String prereq = tmp[1].trim();
                    List<String> pp = new ArrayList<>();
                    if (!prereq.equalsIgnoreCase("None")) {
                        pp.add(prereq);
                    }

                    //TODO: This and the ideology code is kindof shitty.  I need to refactor this into a more elegant format.
                    Ideology idol = new Ideology(cat.trim(), idol_name, idol_name, pp);
                    Map<SocialAreas, Integer> tmpmod;
                    for (int zz = 1; zz < tmp.length; zz++) {
                        tmpmod = SocialAreas.social_mods(tmp[zz]);
                        idol.effects.putAll(tmpmod);
                    }
                    ideologies.add(idol);
                    pos += 1;
                }
            }

        }

    }

}
