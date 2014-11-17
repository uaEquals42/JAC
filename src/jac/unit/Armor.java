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

import jac.Enum.DefenceMode;
import jac.engine.ruleset.Translation;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gregory Jordan
 */
public class Armor extends UnitPart{
    
 
    private final String key;
    private int armor;
    private DefenceMode mode;
    private int cost;
   
    
    public Armor(Translation tran, String key, int armor, DefenceMode mode, int cost, String pre_req, String name1, String name2){
        super(new ArrayList<Effect>(), new ArrayList<Restriction>(),createlist(pre_req));
        this.key = key;
        
        this.armor = armor;
        this.mode = mode;
        String[] names = new String[2];
        names[0] = name1.trim();
        names[1] = name2.trim();
        tran.getArmor().put(key, names);
    }
    
    
    public String name1(Translation tran){
        return tran.getArmor().get(key)[0];
    }
    
    public String name2(Translation tran){
        return tran.getArmor().get(key)[1];
    }

    public int getArmor() {
        return armor;
    }

    public DefenceMode getMode() {
        return mode;
    }

    public int getCost() {
        return cost;
    }
    
    
}
