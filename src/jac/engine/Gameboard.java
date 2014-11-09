/*
 * JAC Copyright (C) 2014 Gregory Jordan
 *
 * This file is part of JAC.   
 * 
 * JAC is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
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
