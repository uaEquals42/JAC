/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package engine.ruleset;

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
        return true;
    }
    
    public boolean loadalpha_txt(){
        
        return true;
    }
    
    public boolean loadalphax_txt(){
        //TODO: implement load alphax.txt
        //TODO: Load SMAC ideologies from SOCIO
        return true;
    }
    
    private int gotosection(String tag){
        
        return 0;
    }
    
    
    
    
}
