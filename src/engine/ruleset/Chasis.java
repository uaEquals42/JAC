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
public class Chasis {

    private String name1;
    private String name2;  // These should be stored or accessed in a translation location.
    private String name3;
    private String name4;
    private String name5;
    private String name6;
    
    int speed;
    MovementType triad;
    boolean missle;
    private int base_cargo;
    int cost;
    List<String> pre_req_str = new ArrayList<>();
    int key;  // for looking up translations.
    
    
    
    
}
