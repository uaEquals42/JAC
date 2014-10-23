/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package engine.Faction;

/**
 *
 * @author Gregory
 */
public class TestFaction {
	 public static void main(String[] args) {
       Faction test = new Faction();
	   test.load_alpha_fac_file("/home/grjordan/.wine/drive_c/GOG Games/Sid Meier's Alpha Centauri/univ.txt");
	   test.saveXML();
    }
}
 