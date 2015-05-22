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
import jac.engine.PlayerDetails;
import jac.unit.GenericUnit;

/**
 *
 * @author Gregory Jordan
 */
public class HasRole<T extends Comparable<T>> implements EffectNode<T>{
    private final WeaponRole role;
    private final EffectNode<T> valueIfTrue;
    private final EffectNode<T> valueIfFalse;
    
    public HasRole(WeaponRole role, EffectNode<T> valueIfTrue, EffectNode<T> valueIfFalse){
        this.role = role;
        this.valueIfTrue = valueIfTrue;
        this.valueIfFalse = valueIfFalse;
    }
    
    public static HasRole<Boolean> bool(WeaponRole role){
        return new HasRole<>(role, Value.True(), Value.False());
    }

    @Override
    public T result(GenericUnit unit, PlayerDetails player) {
        if(unit.getWeapon().getWeaponsRole() == role){
            return valueIfTrue.result(unit, player);
        }
        else{
            return valueIfFalse.result(unit, player);
        }
    }
}