/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package engine.ruleset;

import engine.Faction.SocialAreas;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author grjordan
 */
public class ruleset {
    
    List<Ideology> ideologies = new ArrayList<>();
    
    public boolean loadxml(){
        //TODO: implement loadxml
        return false;
    }
    
    public boolean loadalpha_txt(){
        
        return false;
    }
    
    public boolean loadalphax_txt(){
        //TODO: implement load alphax.txt
        return false;
    }
    
    private int gotosection(String tag){
        
        return 0;
    }
    
    private void create_SMAC_ideologies(){
        Ideology frontier = new Ideology("Politics","Frontier","");
        Ideology police_state = new Ideology("Politics","Police State","DocLoy");
        police_state.add_socialmod(SocialAreas.POLICE, 2);
        police_state.add_socialmod(SocialAreas.SUPPORT, 2);
        police_state.add_socialmod(SocialAreas.EFFIC, -2);
    }
    
    
}
