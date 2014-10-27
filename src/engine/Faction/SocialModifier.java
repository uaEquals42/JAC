/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine.Faction;

/**
 *
 * @author grjordan
 */
public class SocialModifier {
    int mod = 0;
    SocialAreas area;
    
    SocialModifier(int mod, SocialAreas area){
        this.mod = mod;
        this.area = area;
    }
}
