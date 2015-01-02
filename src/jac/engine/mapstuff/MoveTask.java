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
package jac.engine.mapstuff;

import java.awt.Point;

/**
 *
 * @author Gregory Jordan
 */
public class MoveTask implements Tasks{
    
    private final String key;
    private final Square goal;
    private final int apCost;
    private int remainingAPneeded;
    
    MoveTask(String key, Square goal, int apCost){
        this.key = key;
        this.goal = goal;
        this.apCost = apCost;
        this.remainingAPneeded = apCost;
    }
    
    Square getGoal(){
        return goal;
    }
    
    @Override
    public int getActionPointCost() {
        return apCost;
    }

    @Override
    public int getTaskProgress() {
        return remainingAPneeded;
    }

    @Override
    public int workOnTask(int availableActionPoints) {
        int apUsed;
        if(remainingAPneeded <= availableActionPoints){
            apUsed = availableActionPoints-remainingAPneeded;
            remainingAPneeded = 0;
        }
        else{
            apUsed = availableActionPoints;
            remainingAPneeded = remainingAPneeded - availableActionPoints;
        }
        return apUsed;
    }

    @Override
    public String getKey() {
        return key;
    }
    
}
