/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine.ruleset;

import engine.Faction.SocialAreas;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author grjordan
 */
public class Ruleset {

    List<Ideology> ideologies = new ArrayList<>();
    List<Tech> technologies = new ArrayList<>();

    public boolean loadxml() {
        //TODO: implement loadxml
        return true;
    }

    public boolean loadalpha_txt(String filename) {
        try {
            Path path = Paths.get(filename);
            System.out.println(path);
            List<String> input = Files.readAllLines(path, StandardCharsets.UTF_8);
           
            load_ideologies(input);
            load_technologies(input);
            load_facilities(input);

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
        for(Tech test : technologies){
            if(test.id.equalsIgnoreCase(key)){
                return test;
            }
        }
        return null;
    }
    
    private boolean load_facilities(List<String> input){
        return true;
    }
    
    private boolean load_technologies(List<String> input){
        int pos = gotosection("#TECHNOLOGY", input);
        if (pos == -1) {
            Logger.getLogger(Ruleset.class.getName()).log(Level.SEVERE, "#SOCIO not found.");
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
                    new_tech = new Tech(row[0].trim(), row[1].trim(), pre_reqs,
                            flags[8] == '1', probe, commerce_bonus, 
                            flags[5]=='1', flags[3] == '1', flags[4]=='1', 
                            fungus_energy_bonus, fungus_mineral_bonus,
                            fungus_nutrient_bonus, Integer.parseInt(row[2].trim()),
                            Integer.parseInt(row[3].trim()), Integer.parseInt(row[4].trim()),
                            Integer.parseInt(row[5].trim()));
                    technologies.add(new_tech);
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
