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

import jac.Enum.AI_Emphesis;
import jac.Enum.FreeUnitType;
import jac.Enum.SocialAreas;
import jac.Enum.NounSex;
import jac.engine.dialog.Quote;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import javax.xml.bind.Unmarshaller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Gregory Jordan
 */
public class Faction {
    private static final Logger log = LoggerFactory.getLogger(Faction.class);
    private static String PREFIX = "./Factions/"; 

    FactionSettings setting;
    Faction_Dialog dialog;
    private String codeName;
    private String race;
    
    public FactionSettings getSetting() {
        return setting;
    }

    public Faction_Dialog getDialog() {
        return dialog;
    }

    public String getCodeName() {
        return codeName;
    }

    public String getRace() {
        return race;
    }
    

    
    
    
    /**
     * Load in the old SMAC/X config files for the factions.
     *
     * @param FileName
     * @return
     */
    public boolean loadSmacFactionFile(String FileName) {


        try {
            race = "human";  // TODO: Set to alien if it is the alien people.
            
            setting = new FactionSettings();

            dialog = new Faction_Dialog(Locale.ENGLISH);
            
            Path path = Paths.get(FileName);

            
            List<String> textFileIn = Files.readAllLines(path, StandardCharsets.UTF_8);
            int line = 0;

            line = findKey(textFileIn, "#");
            codeName = textFileIn.get(line).substring(1);
            line = nextLine(line, textFileIn);
            String[] tmp = textFileIn.get(line).split(",");
            dialog.faction_name_title = tmp[0];
            dialog.fact_short_description_of_ideology = tmp[1].trim();
            dialog.noun = tmp[2].trim();
            
            String temporaryString = tmp[3].trim()+tmp[4].trim();
            
                    
            dialog.faction_name_sexP = NounSex.convert(temporaryString);


            dialog.leaders_name = tmp[5].trim();
            dialog.leaders_gender = NounSex.convert(tmp[6].trim()+1); // Yes, I'm making it so that the leader can be plural.  A council.  The elder's.  an alien hive mind. etc.

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
            setRules(tmp);
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
            configIdeology(tmparray[0],tmparray[1],setting.pro_ideologies);
            

            line++;
            tmparray = textFileIn.get(line).split(",");
           configIdeology(tmparray[0],tmparray[1],setting.anti_ideologies);

            // Lets skip the sentences for now
            dialog.land_base_names = readSection(textFileIn, "#BASES");


            dialog.water_base_names = readSection(textFileIn, "#WATERBASES");


            line = findKey(textFileIn, "#BLURB");
            dialog.faction_blurb = Quote.readblurb(line + 1, textFileIn).get(0);


            return true; // SUCESS
        } catch (IOException ex) {

            return false; // FAILAURE

        }
    }
    
    /**
     * Will read in the given XML.  This function is likely to be edited in the future.
     * TODO: Update this when the factions data layout is figured out better. (for translations)
     * @param name
     * @return
     */
    public boolean readXML(String name){
        if(name.length()==0){
            return false;
        }
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(FactionSettings.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            //StringReader sr = new StringReader(xml);
            String filename = name + "_settings.xml";
            Path file = Paths.get(PREFIX, filename);     
            this.setting = (FactionSettings) jaxbUnmarshaller.unmarshal(file.toFile());
            
            
            jaxbContext = JAXBContext.newInstance(Faction_Dialog.class);
            jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            filename = name + "_English.xml"; // TODO: make this work for multiple languages.
            file = Paths.get(PREFIX, filename);
            this.dialog = (Faction_Dialog) jaxbUnmarshaller.unmarshal(file.toFile());
            
            this.codeName = name;
            return true;
        
        } catch (JAXBException e) {
            // some exception occured  
            e.printStackTrace();
            return false;
        } 
        
    }
    
    /**
     *
     * @return
     */
    public boolean saveXML() {
        if(setting==null){
            return false;
        }
        
        try {
            File folder = new File(PREFIX);
            boolean result = folder.mkdirs();
            log.debug("Had to create Factions folder: {}", result);
            
            // create JAXB context and initializing Marshaller  
            JAXBContext jaxbContext = JAXBContext.newInstance(FactionSettings.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            // for getting nice formatted output  
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            //specify the location and name of xml file to be created  
            String savename = codeName + "_settings.xml";
            File XMLfile = new File(PREFIX,savename);
           
            
            // Writing to XML file  
            jaxbMarshaller.marshal(setting, XMLfile);
            // Writing to console  
            //jaxbMarshaller.marshal(setting, System.out); // TODO: move this to the logging system.

            jaxbContext = JAXBContext.newInstance(Faction_Dialog.class);
            jaxbMarshaller = jaxbContext.createMarshaller();

            // for getting nice formatted output  
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            savename =  codeName + "_English.xml";
            XMLfile = new File(PREFIX, savename);

            // Writing to XML file  
            jaxbMarshaller.marshal(dialog, XMLfile);
            // Writing to console  
            //jaxbMarshaller.marshal(dialog, System.out);
            return true;
        } catch (JAXBException e) {
            // some exception occured  
            e.printStackTrace();
            return false;
        }
    }
    
    private void configIdeology(String value1, String value2, List<String[]> addto){

        value1 = value1.trim().toLowerCase();
        value2 = value2.trim().toLowerCase();
       
        String[] tmp = {value1, value2};
        addto.add(tmp);
         
       
    }
    

    private void setRules(String[] input) {
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
                    setting.free_facilitys.add(answ);
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
                    
                case "UNIT" :
                  
                    int tmp = Integer.parseInt(answ);
                    if(tmp==0){
                        setting.unit = FreeUnitType.COLONIST;
                    }
                    if(tmp==1){
                        setting.unit = FreeUnitType.TERRAFORMER;
                    }
                    if(tmp==2){
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
                    setting.alien_faction = true;
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

    

    
    

    private List<String> readSection(List<String> strlist, String code) {

        ArrayList<String> tmp = new ArrayList<>();
        int line = findKey(strlist, code);

        // now we read in strings until we hit #END
        line++;
        while (line < strlist.size() && !strlist.get(line).startsWith("#")) {
            tmp.add(strlist.get(line).trim());
            line++;
        }

        return tmp;
    }

    

    private int findKey(List<String> strlist, String key) {
        int line = 0;
        while (!strlist.get(line).startsWith(key) && line < strlist.size()) {
            line++;
        }

        return line;
    }

    private int nextLine(int line, List<String> strlist) {
        line++;
        while (strlist.get(line).startsWith(";")) {
            line++;
        }
        return line;
    }
}
