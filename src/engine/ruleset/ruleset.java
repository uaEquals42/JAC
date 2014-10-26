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
        //TODO: Load SMAC ideologies from SOCIO
        return false;
    }
    
    private int gotosection(String tag){
        
        return 0;
    }
    
    
    
    
}
