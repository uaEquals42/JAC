/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine.ruleset;

import engine.Faction.SocialAreas;
import engine.dialog.Noun;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author grjordan
 */
public class Ruleset {

    List<Ideology> ideologies = new ArrayList<>();
    Map<String, Tech> technologies = new HashMap<>();
    Translation tran;
    List<Chasis> chasises = new ArrayList<>();
    List<Reactor> reactors = new ArrayList<>();
    List<Armor> armors = new ArrayList<>();
    

    public boolean loadxml() {
        //TODO: implement loadxml
        return true;
    }

    public boolean loadalpha_txt(String filename) {
        try {
            Path path = Paths.get(filename);
            System.out.println(path);
            List<String> input = Files.readAllLines(path, StandardCharsets.UTF_8);
            tran = new Translation(Locale.ENGLISH);
            // TODO: Test that these are all true.  If not throw an error.
            load_ideologies(input);
            load_technologies(input);
            load_facilities(input); // TODO: Does nothing right now.
            load_chasis(input);
            load_reactor(input);
            load_armor(input);

            return true;
        } catch (IOException ex) {
            Logger.getLogger(Ruleset.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean loadalphax_txt() {
        //TODO: implement load alphax.txt
        //TODO: Load SMAC ideologies from SOCIO
        return true;
    }

    private int gotosection(String tag, List<String> input) {
        for (int line = 0; line < input.size(); line++) {
            if (input.get(line).trim().equalsIgnoreCase(tag)) {
                return line;
            }
        }

        return -1;
    }

    
    Tech find_tech(String key){
       return technologies.get(key);
    }
    
    private boolean load_facilities(List<String> input){
        return true;
    }
    
    private boolean load_armor(List<String> input){
        int pos = gotosection("#DEFENSES", input);
        if (pos == -1) {
            Logger.getLogger(Ruleset.class.getName()).log(Level.SEVERE, "#DEFENSES not found.");
            System.out.println("Failure");
            return false;

        } else {
            pos++;
            for (int key = 0; !input.get(pos + key).trim().isEmpty(); key++) {
                String[] line = input.get(pos + key).split(",");
                
                armors.add(new Armor(tran, key, Integer.parseInt(line[2].trim()), DefenceMode.convert(Integer.parseInt(line[3].trim())),
                        Integer.parseInt(line[4].trim()),line[5],line[0],line[1]));
            }
            
            return true;
        }
    }
    
    private boolean load_reactor(List<String> input){
        int pos = gotosection("#REACTORS", input);
        if (pos == -1) {
            Logger.getLogger(Ruleset.class.getName()).log(Level.SEVERE, "#REACTORS not found.");
            System.out.println("Failure");
            return false;

        } else {
            pos++;
            for (int key = 0; !input.get(pos + key).trim().isEmpty(); key++) {
                String[] line = input.get(pos + key).split(",");
                Reactor tmp = new Reactor(tran, key, Integer.parseInt(line[2].trim()), line[3], line[0], line[1]);
                reactors.add(tmp);
            }
            
            return true;
        }
        
    }
    
    private boolean load_chasis(List<String> input){
        int pos = gotosection("#CHASSIS", input);
        if (pos == -1) {
            Logger.getLogger(Ruleset.class.getName()).log(Level.SEVERE, "#CHASSIS not found.");
            System.out.println("Failure");
            return false;

        } else {
            pos++;
            for (int key = 0; !input.get(pos + key).trim().isEmpty(); key++) {

                String[] line = input.get(pos + key).split(",");
                List<Noun> names = new ArrayList<>();
                names.add(new Noun(line[0], line[1]));
                names.add(new Noun(line[2], line[3]  ));
                names.add(new Noun(line[4], line[5]  ));
                names.add(new Noun(line[6], line[7] ));
                               
                int speed = Integer.parseInt(line[8].trim());
                MovementType mtype = MovementType.convert(Integer.parseInt(line[9].trim()));
                int range = Integer.parseInt(line[10].trim());
                boolean missle = line[11].trim().equals("1");
                int cargo = Integer.parseInt(line[12].trim());
                int cost = Integer.parseInt(line[13].trim());
                String pre_req = line[14].trim();
                names.add(new Noun(line[15], line[16]  ));
                names.add(new Noun(line[17], line[18]  ));
 
                chasises.add(new Chasis(tran, key, names, speed, mtype, missle, cargo, cost, pre_req));

            }

        }
        return false;
    }
    
    private boolean load_technologies(List<String> input){
        int pos = gotosection("#TECHNOLOGY", input);
        if (pos == -1) {
            Logger.getLogger(Ruleset.class.getName()).log(Level.SEVERE, "#TECHNOLOGY not found.");
            System.out.println("Failure");
            
            return false;

        } else {
            pos++;
            for (; !input.get(pos).trim().isEmpty(); pos++) {
                String[] row = input.get(pos).split(",");
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
                    
                   
                    new_tech = new Tech(row[1].trim(), pre_reqs,
                            flags[8] == '1', probe, commerce_bonus, 
                            flags[5]=='1', flags[3] == '1', flags[4]=='1', 
                            fungus_energy_bonus, fungus_mineral_bonus,
                            fungus_nutrient_bonus, Integer.parseInt(row[2].trim()),
                            Integer.parseInt(row[3].trim()), Integer.parseInt(row[4].trim()),
                            Integer.parseInt(row[5].trim()));
                    tran.technames.put(new_tech.id, row[0].trim());
                    technologies.put(new_tech.id, new_tech);
                }

            }

        }
        return true;
    }
    
    private boolean load_ideologies(List<String> input) {
        
        int pos = gotosection("#SOCIO", input);
        if (pos == -1) {
            Logger.getLogger(Ruleset.class.getName()).log(Level.SEVERE, "#SOCIO not found.");
            System.out.println("Failure");
            return false;
           
        } else {
            System.out.println(input.get(pos));
            pos += 3;
            String[] categories = input.get(pos).split(",");
            pos += 1;
            for (String cat : categories) {
                for (int i = 0; i < 4; i++) {
                    String[] tmp = input.get(pos).split(",");
                    String idol_name = tmp[0].trim();
                    
                    String prereq = tmp[1].trim();
                    List<String> pp = new ArrayList<>();
                    if(!prereq.equalsIgnoreCase("None")){
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
                    pos+=1;
                }
            }

        }

        return true;
    }

    
    
}
