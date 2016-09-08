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
package jac.unit;

import jac.engine.mapstuff.Square;

/**
 *
 * @author Gregory Jordan
 */
public class MoveTask {
    

    private final Square destination;
    private final GenericUnit unit;
    
    private int totalMovementPointCost;
    private int remainingMPneeded;
    
    
    MoveTask(Square destination, GenericUnit unit){
        this.destination = destination;
        this.unit = unit;
        this.totalMovementPointCost = destination.calculateMovementPointCost(unit);
        this.remainingMPneeded = totalMovementPointCost;
    }
    
    Square getDestination(){
        return destination;
    }
    
 
    public int getMotionPointCost() {
        return totalMovementPointCost;
    }


    public int getTaskProgress() {
        return remainingMPneeded;
    }
    
    public boolean finished(){
        return remainingMPneeded==0;
    }


    public int workOnTask(int availableMotionPoints) {
        totalMovementPointCost = destination.calculateMovementPointCost(unit);
        int mpUnused;
        if(remainingMPneeded <= availableMotionPoints){
            mpUnused = availableMotionPoints-remainingMPneeded;
            remainingMPneeded = 0;
        }
        else{
            mpUnused = 0;
            remainingMPneeded = remainingMPneeded - availableMotionPoints;
        }
        return mpUnused;
    }


    
}
