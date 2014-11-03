/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine.dialog;

/**
 *
 * @author grjordan
 */
public class Noun {
    String noun;
    NounSex sex_sp;
    
    
    public Noun(String noun, NounSex sp){
        this.noun = noun.trim();
        sex_sp = sp;
    }
    
    public Noun(String noun, String sex){
      this(noun, NounSex.convert(sex.trim()));  
    }
}
