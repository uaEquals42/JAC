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
package jac.engine.Faction;

import com.google.gson.Gson;
import jac.Enum.AI_Emphesis;
import jac.Enum.FreeUnitType;
import jac.Enum.SocialAreas;
import jac.Enum.NounSex;
import jac.engine.FileHelpers;
import jac.engine.dialog.Quote;
import jac.engine.ruleset.SectionNotFoundException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Gregory Jordan
 */
public class Faction {
    private static final String SETTING_FILE_NAME = "settings";
    public static final String FACTION_FOLDER = "Factions";
    public static final String TRANSLATION_FOLDER = "Translations";
    static Logger log = LoggerFactory.getLogger(Faction.class);

    final FactionSettings setting;
    final Map<Locale, Faction_Dialog> translations;

    public Faction(FactionSettings setting, Map<Locale, Faction_Dialog> translations) {
        this.setting = setting;
        this.translations = translations;
        
    }
    
    private Faction(Builder build){
        this.setting = build.setting;
        this.translations = build.translations;
    }

    public FactionSettings getSetting() {
        return setting;
    }

    public Faction_Dialog getDialog(Locale language) throws ExceptionNoFactionDialog {
        if (translations.isEmpty()) {
            log.error("Faction {} is missing all translations.", this.setting.codeName);
            throw new ExceptionNoFactionDialog(this.setting.codeName);
        }

        if (translations.containsKey(language)) {
            return translations.get(language);
        } else if (translations.containsKey(Locale.ENGLISH)) {
            log.warn("Fallback to English");
            return translations.get(Locale.ENGLISH);
        } else {
            Locale key = translations.keySet().iterator().next(); 
            log.warn("Falling back to {}", key);
            return translations.get(key);

        }

    }

    public String getCodeName() {
        return setting.codeName;
    }

    public String getRace() {
        return setting.race;
    }

    
            /**
         * Given a folder, return a list of all factions stored in that folder.
         * @param path
         * @return 
         */
     public static List<Faction> load_factions(Path path) throws IOException {
        List<Faction> facts = new ArrayList<Faction>();

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(path)) {
            for (Path file : stream) {
                if (Files.isDirectory(file)) {
                    facts.add(new Faction.Builder().loadJson(file).build());
                }
            }

        }

        return facts;
    }
    
    
     public void to_json(Path rulset_location) throws IOException {
         log.trace("Faction to json");
         Path save_location = rulset_location.resolve(FACTION_FOLDER).resolve(this.getCodeName());
         FileHelpers.create_folder(save_location);

         FileHelpers.to_json_file(save_location,SETTING_FILE_NAME, setting);

         FileHelpers.map_to_Json(save_location.resolve(TRANSLATION_FOLDER), translations);

     }
     
    public static class Builder {

        FactionSettings setting = new FactionSettings();
        Map<Locale, Faction_Dialog> translations  = new HashMap<>();;

        public Faction build() {
            return new Faction(this);
        }

        
        
        
        public Builder loadJson(Path folder) throws FileNotFoundException, IOException {
            log.trace("loadJson: {}", folder.toString());
            Gson gson = new Gson();

            setting = gson.fromJson(new FileReader(folder.resolve(SETTING_FILE_NAME + ".json").toFile()), FactionSettings.class);

            List<Path> paths = FileHelpers.listFiles(folder.resolve(TRANSLATION_FOLDER), "*.json");
            for (Path pp : paths) {
                Faction_Dialog dialog = gson.fromJson(new FileReader(pp.toFile()), Faction_Dialog.class);
                translations.put(dialog.getLanguage(), dialog);
            }

            return this;
        }
        
        

        /**
         * Load in the old SMAC/X config files for the factions.
         *
         * @param FileName
         * @return
         * @throws java.io.IOException
         */
        public Builder loadSmacFactionFile(Path path) throws IOException, SectionNotFoundException {
            log.debug("Load Smac Faction at {}", path.toString());
            setting.race = "human";  // TODO: Set to alien if it is the alien people.

            Faction_Dialog dialog = new Faction_Dialog(Locale.ENGLISH);

            

            List<String> textFileIn = Files.readAllLines(path, StandardCharsets.UTF_8);
            int line = 0;

            line = FileHelpers.findKey("#",textFileIn);
            setting.codeName = textFileIn.get(line).substring(1);
            line = FileHelpers.nextLine(line, textFileIn);
            String[] tmp = textFileIn.get(line).split(",");
            dialog.faction_name_title = tmp[0];
            dialog.fact_short_description_of_ideology = tmp[1].trim();
            dialog.noun = tmp[2].trim();

            String temporaryString = tmp[3].trim() + tmp[4].trim();

            dialog.faction_name_sexP = NounSex.convert(temporaryString);

            dialog.leaders_name = tmp[5].trim();
            dialog.leaders_gender = NounSex.convert(tmp[6].trim() + 1); // Yes, I'm making it so that the leader can be plural.  A council.  The elder's.  an alien hive mind. etc.

            setting.ai_fight = Integer.parseInt(tmp[7].trim());
            setting.ai_power = Integer.parseInt(tmp[8].trim());
            setting.ai_tech = Integer.parseInt(tmp[9].trim());
            setting.ai_wealth = Integer.parseInt(tmp[10].trim());
            setting.ai_growth = Integer.parseInt(tmp[11].trim());

            // Next line in file.
            // Follows the following grammer:  Rule, rulesetting, rule, rulesetting, etc...
            line++;
            //System.out.println(textFileIn.get(line));
            tmp = textFileIn.get(line).split(",");
            setRules(tmp, setting);
            line++;
            String[] tmparray = textFileIn.get(line).split(",");

            switch (tmparray[2].toUpperCase().trim()) {
                case "NIL":
                    setting.ai_emphesis = AI_Emphesis.NIL;
                    break;
                case "ECONOMY":
                    setting.ai_emphesis = AI_Emphesis.ECONOMY;
                    break;
                case "EFFIC":
                    setting.ai_emphesis = AI_Emphesis.EFFIC;
                    break;
                case "SUPPORT":
                    setting.ai_emphesis = AI_Emphesis.SUPPORT;
                    break;
                case "TALENT":
                    setting.ai_emphesis = AI_Emphesis.TALENT;
                    break;
                case "MORALE":
                    setting.ai_emphesis = AI_Emphesis.MORALE;
                    break;
                case "POLICE":
                    setting.ai_emphesis = AI_Emphesis.POLICE;
                    break;
                case "GROWTH":
                    setting.ai_emphesis = AI_Emphesis.GROWTH;
                    break;
                case "PLANET":
                    setting.ai_emphesis = AI_Emphesis.PLANET;
                    break;
                case "PROBE":
                    setting.ai_emphesis = AI_Emphesis.PROBE;
                    break;
                case "INDUSTRY":
                    setting.ai_emphesis = AI_Emphesis.INDUSTRY;
                    break;
                case "RESEARCH":
                    setting.ai_emphesis = AI_Emphesis.RESEARCH;
                    break;

            }
            configIdeology(tmparray[0], tmparray[1], setting.pro_ideologies);

            line++;
            tmparray = textFileIn.get(line).split(",");
            configIdeology(tmparray[0], tmparray[1], setting.anti_ideologies);

            // Lets skip the sentences for now
            dialog.land_base_names = FileHelpers.readSection(textFileIn, "#BASES");

            dialog.water_base_names = FileHelpers.readSection(textFileIn, "#WATERBASES");

            line = FileHelpers.findKey("#BLURB", textFileIn);
            dialog.faction_blurb = Quote.readblurb(line + 1, textFileIn).get(0);
            
            translations.put(Locale.ENGLISH, dialog);
            return this;
        }


        private static void configIdeology(String value1, String value2, List<String[]> addto) {

            value1 = value1.trim().toLowerCase();
            value2 = value2.trim().toLowerCase();

            String[] tmp = {value1, value2};
            addto.add(tmp);

        }
        
        private static void setRules(String[] input, FactionSettings setting) {
            String rule;
            String answ;
            for (int ii = 0; ii + 1 < input.length; ii = ii + 2) {
                rule = input[ii].trim();
                answ = input[ii + 1].trim();

                switch (rule.toUpperCase()) {
                    case "TECH":
                        // This can be either a name of a specific tech or a number of user selected techs.
                        try {
                            setting.num_of_free_techs = Integer.parseInt(answ);

                        } catch (NumberFormatException e) {
                            setting.Free_Techs.add(answ);

                        }
                        break;

                    case "SOCIAL":
                        setting.social.putAll(SocialAreas.social_mods(answ));
                        break;

                    case "DRONE":
                        setting.drone_bonus = Integer.parseInt(answ);
                        break;

                    case "FACILITY":
                        setting.freeFacilitys.add(answ);
                        break;

                    case "TALENT":
                        setting.talent = Integer.parseInt(answ);
                        break;

                    case "ENERGY":
                        setting.energy = Integer.parseInt(answ);

                        break;

                    case "INTEREST":
                        setting.interest = Integer.parseInt(answ);
                        break;

                    case "COMMERCE":
                        setting.commerce = Integer.parseInt(answ);
                        break;

                    case "POPULATION":
                        setting.pop_cap_difference = Integer.parseInt(answ);
                        break;

                    case "HURRY":
                        setting.hurry = Integer.parseInt(answ);
                        break;

                    case "UNIT":

                        int tmp = Integer.parseInt(answ);
                        if (tmp == 0) {
                            setting.unit = FreeUnitType.COLONIST;
                        }
                        if (tmp == 1) {
                            setting.unit = FreeUnitType.TERRAFORMER;
                        }
                        if (tmp == 2) {
                            setting.unit = FreeUnitType.SCOUT;
                        }
                        break;

                    case "TECHCOST":
                        setting.techcost = Integer.parseInt(answ);
                        break;

                    case "SHARETECH":
                        setting.sharetech = Integer.parseInt(answ);
                        break;

                    case "TECHSHARE":
                        setting.tech_share = true;
                        break;

                    case "TERRAFORM":
                        setting.terraform_cost = 50;
                        break;

                    case "ROBUST":
                        setting.robust.add(SocialAreas.findtype(answ));
                        break;

                    case "IMMUNITY":
                        setting.immunity.add(SocialAreas.findtype(answ));
                        break;

                    case "IMPUNITY":
                        setting.impunity.add(SocialAreas.findtype(answ));
                        break;

                    case "PENALTY":
                        setting.penalty.add(SocialAreas.findtype(answ));
                        break;

                    case "FUNGNUTRIENT":
                        setting.fungus_nutrient = Integer.parseInt(answ);
                        break;

                    case "FUNGMINERALS":
                        setting.fungus_minerals = Integer.parseInt(answ);
                        break;

                    case "FUNGENERGY":
                        setting.fungus_energy = Integer.parseInt(answ);
                        break;

                    case "COMMFREQ":
                        setting.extra_frequency = 1;
                        break;

                    case "MINDCONTROL":
                        setting.mind_control_immunity = true;
                        break;

                    case "FANATIC":
                        setting.fanatic = true;
                        break;

                    case "VOTES":
                        setting.votes = Integer.parseInt(answ);
                        break;

                    case "FREEPROTO":
                        setting.freeproto = true;
                        break;

                    case "AQUATIC":
                        setting.aquatic_faction = true;
                        break;

                    case "ALIEN":
                        setting.race = "alien";
                        break;

                    case "FREEFAC":
                        setting.free_facility_prereq.add(answ);
                        break;

                    case "REVOLT":
                        setting.revolt_success_modifier = Integer.parseInt(answ);
                        break;

                    case "NODRONE":
                        setting.drone_reduction = Integer.parseInt(answ);
                        break;

                    case "WORMPOLICE":
                        setting.wormpolice = 2;
                        break;

                    case "FREEABIL":
                        setting.free_ability = answ;
                        break;

                    case "PROBECOST":
                        setting.probe_cost = Integer.parseInt(answ);
                        break;

                    case "DEFENSE":
                        setting.defence = Integer.parseInt(answ);
                        break;

                    case "OFFENSE":
                        setting.offence = Integer.parseInt(answ);
                        break;

                    case "TECHSTEAL":
                        setting.techsteal = true;
                        break;

                }

            }
        }

        
        
    }

}
