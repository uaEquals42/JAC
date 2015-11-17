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
package jac.unit.effectRules;

import jac.Enum.WeaponRole;
import jac.engine.ruleset.Ruleset;
import jac.unit.Unit;

/**
 *
 * @author Gregory Jordan
 */
public class HasRole<T extends Comparable<T>> implements EffectNode<Boolean>{
    private final WeaponRole role;
    
    
    public HasRole(WeaponRole role){
        this.role = role;
    }

    @Override
    public Boolean result(Unit unit, Ruleset rules) {
        return rules.getWeapons().get(unit.getWeaponKey()).getWeaponsRole() == role;     
    }
}
