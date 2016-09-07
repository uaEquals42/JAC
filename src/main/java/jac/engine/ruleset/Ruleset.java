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
import jac.engine.dialog.Quote;
import jac.engine.mapstuff.TerrainBaseState;
import jac.engine.mapstuff.TerrainModifier;
import jac.engine.mapstuff.Terrainstat;
import jac.unit.*;
import jac.unit.partTranslation.Unit_Part_Translation;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
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
    static final String CHASSIS = "chassis";
    static final String ARMOR = "ARMOR ";
    static final String REACTOR = "REACTOR ";
    static final String SMAC_UNIT_FRAME = "SMAC_UNIT";
    static final String SMAC_BASE = "SMAC_BASE";

    static Logger log = LoggerFactory.getLogger(Ruleset.class);

    private final String ruleset_name;

    private final List<Ideology> ideologies;
    private final Map<String, Tech> technologies;

    // Unit stuff
    private final Map<String, Unit_part> unit_components;
    private final Map<String, Unit_frame> unit_frames;


    private final Map<String, Unit_Plan> unitPlans;

    // Terrain
    private final Map<String, Terrainstat> basicTerrainStates;
    private final Map<String, Terrainstat> terrainModifiers;

    public List<Unit_part> get_part_list_by_category(Part_Category category) {
        List<Unit_part> results = new LinkedList<>();
        for (Unit_part part : unit_components.values()) {
            if (part.getCategory() == category) {
                results.add(part);
            }
        }
        return results;
    }

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
        FileHelpers.map_to_Json(saveLocation, unit_components);

    }

    public Tech find_tech(String key) {
        return technologies.get(key);
    }

    private Ruleset(Builder build) {
        ruleset_name = build.ruleset_name;
        ideologies = build.ideologies;
        technologies = build.technologies;

        //Unit variables
        unit_components = build.unit_components;
        unit_frames = build.unit_frames;

        basicTerrainStates = build.basicTerrainStates;
        terrainModifiers = build.terrainModifiers;

        this.unitPlans = build.unitPlans;
    }


    public Map<String, Unit_Plan> getUnitPlans() {
        return unitPlans;
    }

    public List<Ideology> getIdeologies() {
        return ideologies;
    }

    public Map<String, Unit_part> getUnit_components() {
        return unit_components;
    }

    
    
    
    private Map<String, Unit_part> get_map_of_unit_part_category(Part_Category selector) {
        Map<String, Unit_part> selection = new HashMap<>();
        for (Unit_part part : unit_components.values()) {
            if (part.getCategory() == selector) {
                selection.put(part.getKey(), part);
            }
        }
        return selection;
    }


    public Map<String, Tech> getTechnologies() {
        return technologies;
    }



    public static class Builder {

        static Locale FILELOCALE = Locale.ENGLISH;

        private final int SMAC_MP_COST_MULTIPLIER = 3;  // TODO: Load this from SMAC file.
        private String ruleset_name;
        private List<Ideology> ideologies = new ArrayList<>();
        private Map<String, Tech> technologies = new HashMap<>();

        private Map<String, Unit_part> unit_components = new LinkedHashMap<>();
        private Map<String, Unit_frame> unit_frames = new LinkedHashMap<>();

        private Map<String, Unit_Plan> unitPlans = new LinkedHashMap<>();

        private Map<String, Terrainstat> basicTerrainStates = new LinkedHashMap<>();
        private Map<String, Terrainstat> terrainModifiers = new LinkedHashMap<>();

        public Ruleset loadalpha_txt(Path path) throws SectionNotFoundException, IOException {
            log.debug("loadalpha_txt: {}", path.toAbsolutePath());
            ruleset_name = "SMAC"; 
            List<String> input = Files.readAllLines(path, StandardCharsets.ISO_8859_1);
            //tran = new Translation(Locale.ENGLISH);

            // load these in first.
            Map<String, List<Quote>> blurbs = load_blurbs_txt(path.resolveSibling("Blurbs.txt"));
            //tran.opening_quote = blurbs.get("#OPENING").get(0);

            Map<String, String> techlongs = load_techlongs(path.resolveSibling("TECHLONGS.TXT"));

            load_ideologies(input);
            //load_technologies(input, blurbs);
            load_SMAC_chassis_loadout();
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

        private void load_SMAC_chassis_loadout() {
            
            
            unit_frames.put(SMAC_UNIT_FRAME, new Unit_frame.Builder(SMAC_UNIT_FRAME).
                    set(Part_Category.ARMOR, 1).
                    set(Part_Category.PROPULSION, 1).
                    set(Part_Category.REACTOR, 1).
                    set(Part_Category.MODS, 2).
                    build());
            unit_frames.put(SMAC_BASE, new Unit_frame.Builder(SMAC_BASE).
                    set(Part_Category.FACILITY, 10000).
                    set(Part_Category.MODS, 1). // There is a hidden mod to allow building units, pop, etc.
                    build());

        }



        private Map<String, Terrainstat> load_BasicTerrainTypes() {
            Map<String, Terrainstat> basicterraintypes = new HashMap<>();

            basicterraintypes.put("dirt", new TerrainBaseState.Builder("dirt", 3).build());
            basicterraintypes.put("rock", new TerrainBaseState.Builder("rock", 3).build());

            return basicterraintypes;
        }

        private Map<String, Terrainstat> load_TerrainModifiers() {
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
            int pos = FileHelpers.findKey("#UNITS", input);
            pos++;

            pos++;
            for (; !input.get(pos).trim().isEmpty(); pos++) {
                String[] col = input.get(pos).split(",");

                //Name, Chassis, Weapon, Armor, Plan, Cost, Carry, Preq, Icon, Abil
                //Colony Pod, Infantry, Colony Pod,   Scout,      8, 0, 0, None,    -1, 000000000000000000000000
                List<String> parts = new ArrayList<>();
                String name = col[0].trim();
                String chassis = CHASSIS + col[1].trim();
                String weapon = col[2].trim();
                String armor = ARMOR + col[3].trim();
                String reactor = REACTOR + "Fission";
                parts.add(name);
                parts.add(chassis);
                parts.add(weapon);
                parts.add(reactor);
                
                AiUnitPlan unitRole = AiUnitPlan.convert(col[4]);

                Integer check = Integer.parseInt(col[5].trim());
                Integer costOverride;
                if (check != 0) {
                    costOverride = check;
                    //TODO: Implement cost override for unitplan.  ARGGGGGG.
                }

                check = Integer.parseInt(col[6].trim());
                Integer carryCapacityOverride;
                if (check != 0) {
                    carryCapacityOverride = check;
                    //TODO: Implement Capacity Override for unitplan.
                }

                String prereqTech = col[7].trim();
                 // TODO: Get the disabled, and none working here.

                Unit_Plan thePlan = new Unit_Plan.Builder(name, SMAC_UNIT_FRAME, parts).prototyped(true).build();


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
            int pos = FileHelpers.findKey("#FACILITIES", input);
            pos++;
            int facility_count = 0;
            int secret_count = 0;
            for (; !input.get(pos).trim().isEmpty(); pos++) {
                String[] row = input.get(pos).split(",");

                int cost = Integer.parseInt(row[1].trim());
                int maintence = Integer.parseInt(row[2].trim());

                Unit_part part;
                // calculate the key we will be needing.
                String key;
                String full_name = row[0];
                String description = row[5];
                String tech_key = row[3];

                Unit_Part_Translation trans = new Unit_Part_Translation.Builder(FILELOCALE, full_name, full_name).
                        set_description(description).
                        build();

                Effect effect = new Effect.Builder().
                        setIntFlag(IntNames.COST, cost).
                        setIntFlag(IntNames.ENERGY_SUPPORT_COST, maintence).
                        build();

                if (row.length == 11) {
                    // then it is a secret project.
                    key = "#PROJECT" + secret_count;

                    part = new Unit_part.Builder(key, Part_Category.FACILITY, trans).
                            addPreRequisiteTech(tech_key).is_secret_project(true).setLocalEffects(effect).build();
                    //TODO: Add blurbs being added.

                    secret_count++;
                } else {
                    key = "#FAC" + facility_count;
                    part = new Unit_part.Builder(key, Part_Category.FACILITY, trans).
                            addPreRequisiteTech(tech_key).is_secret_project(true).setLocalEffects(effect).build();

                    facility_count++;
                }

                unit_components.put(key, part);

            }

        }

        private void load_weapon(List<String> input) throws SectionNotFoundException {
            int pos = FileHelpers.findKey("#WEAPONS", input);
            pos++;
            int count = 0;
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

                String full_name = line[0];
                String short_name = line[1];

                Effect effect = new Effect.Builder().
                        set_attack(mode, damage).
                        setIntFlag(IntNames.COST, cost).
                        build();
                Unit_Part_Translation trans = new Unit_Part_Translation.Builder(FILELOCALE, full_name, short_name).
                        build();
                Unit_part weapon = new Unit_part.Builder(key, Part_Category.WEAPON, trans).
                        addPreRequisiteTech(line[5]).
                        setLocalEffects(effect).
                        build();

                unit_components.put(key, weapon);
                count++;
            }
            log.trace("Loaded {} weapons", count);

        }

        private void load_unit_abilities(List<String> input) throws SectionNotFoundException {
            int pos = FileHelpers.findKey("#ABILITIES", input);
            pos++;

            for (; !input.get(pos).trim().isEmpty(); pos++) {
                String[] line = input.get(pos).split(",");
                String key = line[0].trim().toUpperCase(Locale.ENGLISH);
                if (!line[2].trim().equalsIgnoreCase("Disable")) {
                    
                    //TODO: Actually add unit abilities to these.
                    String full_name = line[0];
                    String description = line[5];
                    int cost = Integer.parseInt(line[1].trim());
                    Unit_Part_Translation trans = new Unit_Part_Translation
                            .Builder(FILELOCALE, full_name, full_name)
                            .set_description(description)
                            .build();
                    
                    Effect effect = new Effect.Builder()
                            .setIntFlag(IntNames.COST, cost)
                            .build();
                    
                    Unit_part part = new Unit_part
                            .Builder(key, Part_Category.MODS, trans)
                            .setLocalEffects(effect)
                            .smacAbilityFlags(line[4])
                            .addPreRequisiteTech(line[2])
                            .build();
                    
                    unit_components.put(key, part);

                }

            }

        }

        private void load_armor(List<String> input) throws SectionNotFoundException {
            int pos = FileHelpers.findKey("#DEFENSES", input);
            pos++;

            int count = 0;
            for (; !input.get(pos).trim().isEmpty(); pos++) {
                String[] line = input.get(pos).split(",");
                DefenceMode mode;
                int rating = Integer.parseInt(line[2].trim());

                if (rating == -1) {
                    mode = DefenceMode.PSI;

                } else {
                    mode = DefenceMode.convert(Integer.parseInt(line[3].trim()));
                }

                String key = ARMOR + line[1].trim();
                int cost = Integer.parseInt(line[4].trim());

                String full_name = line[0];
                String short_name = line[1];

                Effect localEffect = new Effect.Builder()
                        .add_shielding(mode, rating)
                        .setIntFlag(IntNames.COST, cost)
                        .build();

                Unit_Part_Translation trans = new Unit_Part_Translation.Builder(Locale.ENGLISH, full_name, short_name).build();

                unit_components.put(key, new Unit_part.Builder(key, Part_Category.ARMOR, trans).
                        addPreRequisiteTech(line[5]).
                        setLocalEffects(localEffect).
                        build());
                count++;
            }
            log.trace("Loaded {} armors.", count);

        }

        private void load_reactor(List<String> input) throws SectionNotFoundException {
            int pos = FileHelpers.findKey("#REACTORS", input);
            pos++;

            for (; !input.get(pos).trim().isEmpty(); pos++) {
                String[] line = input.get(pos).split(",");
                String key = REACTOR + line[1].trim();
                int cost_power = Integer.parseInt(line[2].trim());
                String full_name = line[0];
                String short_name = line[1];

                Unit_Part_Translation trans = new Unit_Part_Translation.Builder(Locale.ENGLISH, full_name, short_name).build();

                Unit_part tmp = new Unit_part.Builder(key, Part_Category.REACTOR, trans).
                        addPreRequisiteTech(line[3]).
                        setLocalEffects(
                                new Effect.Builder().
                                setIntFlag(IntNames.POWER, (cost_power))
                                .setIntFlag(IntNames.COST, cost_power)
                                .build()).
                        build();

                unit_components.put(key, tmp);

            }

        }

        private void load_chassis(List<String> input) throws SectionNotFoundException {
            int pos = FileHelpers.findKey("#CHASSIS", input);
            pos++;

            for (; !input.get(pos).trim().isEmpty(); pos++) {

                String[] line = input.get(pos).split(",");
                String key = CHASSIS + line[0].trim();

                int speed = Integer.parseInt(line[8].trim()) * SMAC_MP_COST_MULTIPLIER;
                Domain domain = Domain.convert(Integer.parseInt(line[9].trim()));
                int range = Integer.parseInt(line[10].trim());
                boolean missle = line[11].trim().equals("1");
                int cargo = Integer.parseInt(line[12].trim());
                int cost = Integer.parseInt(line[13].trim());
                String pre_req = line[14].trim();

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
                        setIntFlag(IntNames.CARGO_CAPACITY, cargo)
                        .setIntFlag(IntNames.COST, cost)
                        .setIntFlag(IntNames.SPEED, speed)
                        .setIntFlag(IntNames.OUT_OF_RANGE_DAMAGE, damage)
                        .setIntFlag(IntNames.AIR_RANGE, range)
                        .build();

                Unit_Part_Translation trans = new Unit_Part_Translation.Builder(FILELOCALE, line[0], line[2]).set_defensive_name(line[7]).build();
                
                Unit_part tmp = new Unit_part.Builder(key, Part_Category.PROPULSION, trans)
                        .addPreRequisiteTech(pre_req)
                        .setLocalEffects(localEffect)
                        .set_Domain(domain)
                        .build();
                
               unit_components.put(key, tmp);
                

            }

        }

        /*
         private void load_technologies(List<String> input, Map<String, List<Quote>> blurbs) throws SectionNotFoundException {
         int pos = findKey("#TECHNOLOGY", input);
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
            int pos = FileHelpers.findKey("#SOCIO", input);
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
