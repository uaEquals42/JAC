/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jac.engine;

/**
 *
 * @author Gregory Jordan
 */
public class Gameboard {
    int width;
    int height;
    Square[][] map;
    int waterheight = 10000; // meters, everything is in meters for height.
    //  Terrain generated above this level will be above water, terrain below will be underwater.
    
    public Gameboard(int width, int height, int percent_land){
        this.width = width;
        this.height = height;
        map = new Square[width][height];
        generate_flat_map();
        
    }
	public void generate_flat_map(){
            for(int ww=0; ww < width; ww++){
                for(int hh=0; ww < height; hh++){
                    map[ww][hh]=new Square(100);
                }
            }
        }
	public void generateradom_map(){
		
	}
}
