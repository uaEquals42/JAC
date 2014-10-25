/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package engine.Faction;

import engine.dialog.Quote;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.util.logging.Logger;

/**
 *
 * @author Gregory
 */
public class Faction {

    FactionSettings setting;
    Faction_Dialog dialog;
    String code_name;

    /**
     * Load in the old SMAC/X config files for the factions.
     *
     * @param FileName
     * @return
     */
    public boolean load_alpha_fac_file(String FileName) {

        setting = new FactionSettings();

        dialog = new Faction_Dialog();
        try {
            Path path = Paths.get(FileName);
            System.out.println(path);
            List<String> fac_in = Files.readAllLines(path, StandardCharsets.UTF_8);
            int line = 0;

            line = findkey(fac_in, "#");
            code_name = fac_in.get(line).substring(1);
            line = nextline(line, fac_in);
            String[] tmp = fac_in.get(line).split(",");
            dialog.faction_name_title = tmp[0];
            dialog.fact_short_description_of_ideology = tmp[1].trim();
            dialog.noun = tmp[2].trim();
            dialog.fac_name_gender = tmp[3].trim();
            dialog.singular_plural = tmp[4].trim();
            dialog.leaders_name = tmp[5].trim();
            dialog.leaders_gender = tmp[6].trim();

            setting.ai_fight = Integer.parseInt(tmp[7].trim());
            setting.ai_power = Integer.parseInt(tmp[8].trim());
            setting.ai_tech = Integer.parseInt(tmp[9].trim());
            setting.ai_wealth = Integer.parseInt(tmp[10].trim());
            setting.ai_growth = Integer.parseInt(tmp[11].trim());

            // Next line in file.
            // Follows the following grammer:  Rule, rulesetting, rule, rulesetting, etc...
            line++;
            //System.out.println(fac_in.get(line));
            tmp = fac_in.get(line).split(",");
            set_rules(tmp);
            line++;
            String[] tmparray = fac_in.get(line).split(",");
           
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
            config_ideology(tmparray[0],tmparray[1],setting.pro_ideologies);
            

            line++;
            tmparray = fac_in.get(line).split(",");
           config_ideology(tmparray[0],tmparray[1],setting.anti_ideologies);

            // Lets skip the sentences for now
            dialog.land_base_names = read_section(fac_in, "#BASES");
            System.out.println(dialog.land_base_names);

            dialog.water_base_names = read_section(fac_in, "#WATERBASES");
            System.out.println(dialog.water_base_names);

            line = findkey(fac_in, "#BLURB");
            dialog.faction_blurb = new Quote(line + 1, fac_in);
            dialog.faction_blurb.print();

            return true; // SUCESS
        } catch (IOException ex) {
            Logger.getLogger(Faction.class.getName()).log(Level.SEVERE, null, ex);
            return false; // FAILAURE

        }
    }
    
    private void config_ideology(String value1, String value2, List<Ideologies> addto){

        value1 = value1.trim().toLowerCase();
        value2 = value2.trim().toLowerCase();
       
        if(value1.equals("politics")){
            if(value2.equals("")){
                addto.add(Ideologies.POL_POLICE);
            }
            if(value2.equals("democratic")){
                addto.add(Ideologies.POL_DEMOCRATIC);
            }
            if(value2.equals("fundamentalist")){
                addto.add(Ideologies.POL_FUNDAMENTALIST);
            }
            
        }
        if(value1.equals("economics")){
            if(value2.equals("free market")){
                addto.add(Ideologies.ECON_FREE_MARKET);
            }
            if(value2.equals("planned")){
                addto.add(Ideologies.ECON_PLANNED);
            }
            if(value2.equals("green")){
                addto.add(Ideologies.ECON_GREEN);
            }
            
        }
        if(value1.equals("values")){
            if(value2.equals("power")){
                addto.add(Ideologies.VALUE_POWER);
            }
            if(value2.equals("knowledge")){
                addto.add(Ideologies.VALUE_KNOWLEDGE);
            }
            if(value2.equals("wealth")){
                addto.add(Ideologies.VALUE_WEALTH);
            }
            
        }
        if(value1.equals("future society")){
            if(value2.equals("cybernetic")){
                addto.add(Ideologies.FUTURE_CYBERNETIC);
            }
            if(value2.equals("eudaimonic")){
                addto.add(Ideologies.FUTURE_EUDAIMONIC);
            }
            if(value2.equals("thought control")){
                addto.add(Ideologies.FUTURE_THOUGHT_CONTROL);
            }
            
        }
    }
    

    private void set_rules(String[] input) {
        String rule;
        String answ;
        for (int ii = 0; ii + 1 < input.length; ii = ii + 2) {
            rule = input[ii].trim();
            answ = input[ii + 1].trim();
            System.out.print(rule);

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
                    setting.social.add(answ);
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
                    setting.robust.add(findtype(answ));
                    break;
                    
                case "IMMUNITY":
                    setting.immunity.add(findtype(answ));
                    break;
                    
                case "IMPUNITY":
                    setting.impunity.add(findtype(answ));
                    break;
                    
                case "PENALTY":
                    setting.penalty.add(findtype(answ));
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

    /**
     * #TODO See if I can move to socialAreas.
     * 
     * @param social
     * @return 
     */
    private SocialAreas findtype(String social) {
        switch (social.toUpperCase()) {
            case "ECONOMY":
                return SocialAreas.ECONOMY;
              
            case "EFFIC":
                return SocialAreas.EFFIC;
            case "SUPPORT":
                return SocialAreas.SUPPORT;
            case "TALENT":
                return SocialAreas.TALENT;
            case "MORALE":
                return SocialAreas.MORALE;
            case "POLICE":
                return SocialAreas.POLICE;
            case "GROWTH":
                return SocialAreas.GROWTH;
            case "PLANET":
                return SocialAreas.PLANET;
            case "PROBE":
                return SocialAreas.PROBE;
            case "INDUSTRY":
                return SocialAreas.INDUSTRY;
            case "RESEARCH":
                return SocialAreas.RESEARCH;
        }
        // TODO: Make it throw and exception.
        return null;  // this shouldn't happen ever.  
    }

    private List<String> read_section(List<String> strlist, String code) {

        ArrayList<String> tmp = new <String>ArrayList();
        int line = findkey(strlist, code);

        // now we read in strings until we hit #END
        line++;
        while (line < strlist.size() && !strlist.get(line).startsWith("#")) {
            tmp.add(strlist.get(line).trim());
            line++;
        }

        return tmp;
    }


    public boolean saveXML() {
        if(setting==null){
            return false;
        }
        
        try {

            // create JAXB context and initializing Marshaller  
            JAXBContext jaxbContext = JAXBContext.newInstance(FactionSettings.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            // for getting nice formatted output  
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            //specify the location and name of xml file to be created  
            String savename = code_name + "_settings.xml";
            File XMLfile = new File(savename);

            // Writing to XML file  
            jaxbMarshaller.marshal(setting, XMLfile);
            // Writing to console  
            jaxbMarshaller.marshal(setting, System.out);

            jaxbContext = JAXBContext.newInstance(Faction_Dialog.class);
            jaxbMarshaller = jaxbContext.createMarshaller();

            // for getting nice formatted output  
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            savename = code_name + "_English.xml";
            XMLfile = new File(savename);

            // Writing to XML file  
            jaxbMarshaller.marshal(dialog, XMLfile);
            // Writing to console  
            jaxbMarshaller.marshal(dialog, System.out);
            return true;
        } catch (JAXBException e) {
            // some exception occured  
            e.printStackTrace();
            return false;
        }
    }

    private int findkey(List<String> strlist, String key) {
        int line = 0;
        while (!strlist.get(line).startsWith(key) && line < strlist.size()) {
            line++;
        }

        return line;
    }

    private int nextline(int line, List<String> strlist) {
        line++;
        while (strlist.get(line).startsWith(";")) {
            line++;
        }
        return line;
    }
}
