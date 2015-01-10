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
package jac.unit;

import jac.unit.effectRules.EffectNode;
import jac.unit.effectRules.Value;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Gregory Jordan
 */
public class EffectMaker {
      EffectNode<Boolean> isitabase = Value.False();
         EffectNode<Boolean> can_make_facilities = Value.False();
         EffectNode<Boolean> can_make_units = Value.False();
         EffectNode<Boolean> cant_attack = Value.False();
         EffectNode<Boolean> cant_defend = Value.False();
         EffectNode<Boolean> capture_when_defeated = Value.False();

         Map<String, ? extends EffectNode<Unit_Plan>> converts_to = new HashMap<>();

         EffectNode<Integer> speed_boost  = Value.zero();

         EffectNode<Boolean> amphibious = Value.False();
        
        // Resources Effects
         EffectNode<Integer> unitMineralProduction = Value.zero();

         EffectNode<Integer> workerMineralProduction = Value.zero(); // How much minerals each square produces.
}
