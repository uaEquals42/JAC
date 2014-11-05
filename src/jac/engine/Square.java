/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jac.engine;

/**
 *
 * @author Gregory
 */
public class Square {
    int altitude;
    boolean rock = false;
    public Square(int alt){
        altitude = alt;
    }
    public void changealtitude(int updown){
        //TODO: Do I want checks for a min altitide possible?
        altitude+= updown;
    }
}
