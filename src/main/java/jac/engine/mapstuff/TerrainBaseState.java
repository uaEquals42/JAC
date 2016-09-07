/*
 * JAC Copyright (C) 2015 Gregory Jordan
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

/**
 *
 * @author Gregory Jordan
 */
public class TerrainBaseState implements Terrainstat{
    private final String key;
    private final int normalMPCost;
    private final Integer minMPCost;
    
    TerrainBaseState(Builder build){
        this.key = build.key;
        this.minMPCost = build.minMPCost;
        this.normalMPCost = build.normalMPCost;
    }
    
    @Override
    public String name() {
        return key;
    }

    @Override
    public int getNormalMovementPoints(int altitude) {
        return this.normalMPCost;
    }

    @Override
    public Integer getMinMovementPoints(int altitude) {
        return this.minMPCost;
    }
    
    public static class Builder {
        private final String key;
        
        private final int normalMPCost;
        private Integer minMPCost;
        
        public Builder(String key, int normalMPCost){
            this.key = key;
            this.normalMPCost = normalMPCost;
        }

        public Builder setMinMPCost(Integer minMPCost) {
            this.minMPCost = minMPCost;
            return this;
        }
        
        public TerrainBaseState build(){
            return new TerrainBaseState(this);
        }
    }

}
