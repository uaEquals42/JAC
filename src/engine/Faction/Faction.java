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
            for (int i = 0; i < tmparray.length; i++) {
                setting.set_pro_ideologies(tmparray[i].trim());
            }
            System.out.println(setting.pro_ideologies);

            line++;
            tmparray = fac_in.get(line).split(",");
            for (int i = 0; i < tmparray.length; i++) {
                setting.set_anti_ideologies(tmparray[i].trim());
            }
            System.out.println(setting.anti_ideologies);

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
            
            }   

        }
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

    public void saveXML() {
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

        } catch (JAXBException e) {
            // some exception occured  
            e.printStackTrace();
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
