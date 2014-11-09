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

import jac.engine.Faction.SocialAreas;
import jac.engine.dialog.Noun;
import jac.engine.dialog.Quote;
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

    private final Logger log = LoggerFactory.getLogger(this.getClass().getName());
    List<Ideology> ideologies = new ArrayList<>();
    Map<String, Tech> technologies = new HashMap<>();
    Translation tran;
    List<Chasis> chasises = new ArrayList<>();
    List<Reactor> reactors = new ArrayList<>();
    List<Armor> armors = new ArrayList<>();
    List<Weapon> weapons = new ArrayList<>();
    Map<String, UnitAbility> unit_abilities = new LinkedHashMap<>();
    Map<String, Facility> facilities = new LinkedHashMap<>();

    
    
    private Map<String, String> load_techlongs(Path location) throws IOException{
        log.debug("Load techlongs: {}", location);
        List<String> input = Files.readAllLines(location, StandardCharsets.ISO_8859_1);
        Map<String, String> techlongs = new HashMap<>();
        for (int ii = 0; ii < input.size(); ii++) {
            if (input.get(ii).contains("##")) {
                String entry="";
                ii++;
                String key = input.get(ii).trim();
                ii++;
                
                for(;input.get(ii).trim().length()>0; ii++){
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

    public void loadalpha_txt(String filename) throws SectionNotFoundException, IOException {
        log.debug("loadalpha_txt: {}", filename);

        Path path = Paths.get(filename);

        List<String> input = Files.readAllLines(path, StandardCharsets.ISO_8859_1);
        tran = new Translation(Locale.ENGLISH);

        // load these in first.
        Map<String, List<Quote>> blurbs = load_blurbs_txt(path.resolveSibling("Blurbs.txt"));
        tran.opening_quote = blurbs.get("#OPENING").get(0);
        
        Map<String, String> techlongs = load_techlongs(path.resolveSibling("TECHLONGS.TXT"));

        load_ideologies(input);
        load_technologies(input, blurbs);
        load_facilities(input, blurbs);
        load_chasis(input);
        load_reactor(input);
        load_armor(input);
        load_weapon(input);
        load_unit_abilities(input);

    }

    public void loadalphax_txt() throws SectionNotFoundException, IOException {
        //TODO: implement load alphax.txt
        //TODO: Load SMAC ideologies from SOCIO
        log.warn("Function not implemented yet.");
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

    Tech find_tech(String key) {
        return technologies.get(key);
    }

    /**
     * This is for loading in the facilities and secret projects.
     * @param input - the file being read as a list of strings.  Should be alpha.txt
     * @param blurbs - List of quotes.  Will add the appropriate ones to the facility info.
     * @throws SectionNotFoundException Will throw if it can't find the "#FACILITIES" section.
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

                tmp_facility = new Facility.Builder(key, tran, cost, maintence, row[0], row[5]).project().pre_req(row[3]).
                        quotes(blurbs.get(key)).build();

                secret_count++;
            } else {
                key = "#FAC" + facility_count;
                
                tmp_facility = new Facility.Builder(key, tran, cost, maintence, row[0], row[5]).pre_req(row[3]).
                        quotes(blurbs.get(key)).build();
                
                
                facility_count++;
            }
            
            
            facilities.put(key, tmp_facility);
            
            
        }

    }

    private void load_weapon(List<String> input) throws SectionNotFoundException {
        int pos = gotosection("#WEAPONS", input);
        pos++;
        for (int key = 0; !input.get(pos + key).trim().isEmpty(); key++) {
            String[] line = input.get(pos + key).split(",");
            CombatMode mode;
            int damage = Integer.parseInt(line[2].trim());

            if (damage == -1) {
                mode = CombatMode.PSI;
            } else {
                mode = CombatMode.convert(Integer.parseInt(line[3].trim()));
            }

            weapons.add(new Weapon(tran, key, line[0], line[1], damage, Integer.parseInt(line[4].trim()), line[6]));
        }
        log.trace("Loaded {} weapons", weapons.size());
        

    }

    private void load_unit_abilities(List<String> input) throws SectionNotFoundException {
        int pos = gotosection("#ABILITIES", input);
        pos++;
        
        for (; !input.get(pos).trim().isEmpty(); pos++) {
            String[] line = input.get(pos).split(",");

            if (!line[2].trim().equalsIgnoreCase("Disable")) {
                UnitAbility abile = new UnitAbility.Builder(line[0].trim(), tran, line[0].trim(), line[3], line[5]).
                        smacAbilityFlags(line[4]).
                        setCost_code(Integer.parseInt(line[1].trim())).
                        addPreReq(line[2]).
                        build();
                       
                unit_abilities.put(line[0].trim().toUpperCase(Locale.ENGLISH), abile);
            }

        }

    }

    private void load_armor(List<String> input) throws SectionNotFoundException {
        int pos = gotosection("#DEFENSES", input);
        pos++;
        
        for (int key = 0; !input.get(pos + key).trim().isEmpty(); key++) {
            String[] line = input.get(pos + key).split(",");
            DefenceMode mode;
            int rating = Integer.parseInt(line[2].trim());

            if (rating == -1) {
                mode = DefenceMode.PSI;

            } else {
                mode = DefenceMode.convert(Integer.parseInt(line[3].trim()));
            }

            armors.add(new Armor(tran, key, rating, mode,
                    Integer.parseInt(line[4].trim()), line[5], line[0], line[1]));
        }
        log.trace("Loaded {} armors.", armors.size());

    }

    private void load_reactor(List<String> input) throws SectionNotFoundException {
        int pos = gotosection("#REACTORS", input);
        pos++;
        
        for (int key = 0; !input.get(pos + key).trim().isEmpty(); key++) {
            String[] line = input.get(pos + key).split(",");
            Reactor tmp = new Reactor(tran, key, Integer.parseInt(line[2].trim()), line[3], line[0], line[1]);
            reactors.add(tmp);
        }

    }

    private void load_chasis(List<String> input) throws SectionNotFoundException {
        int pos = gotosection("#CHASSIS", input);
        pos++;
        
        for (int key = 0; !input.get(pos + key).trim().isEmpty(); key++) {

            String[] line = input.get(pos + key).split(",");
            List<Noun> names = new ArrayList<>();
            names.add(new Noun(line[0], line[1]));
            names.add(new Noun(line[2], line[3]));
            names.add(new Noun(line[4], line[5]));
            names.add(new Noun(line[6], line[7]));

            int speed = Integer.parseInt(line[8].trim());
            MovementType mtype = MovementType.convert(Integer.parseInt(line[9].trim()));
            int range = Integer.parseInt(line[10].trim());
            boolean missle = line[11].trim().equals("1");
            int cargo = Integer.parseInt(line[12].trim());
            int cost = Integer.parseInt(line[13].trim());
            String pre_req = line[14].trim();
            names.add(new Noun(line[15], line[16]));
            names.add(new Noun(line[17], line[18]));

            chasises.add(new Chasis(tran, key, names, speed, mtype, missle, cargo, cost, pre_req));

        }

    }

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
                tran.technames.put(id, row[0].trim());
                technologies.put(id, new_tech);
            }

        }

    }

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
                Ideology idol = new Ideology(cat.trim(), idol_name, pp);
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
