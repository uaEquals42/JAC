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

import jac.engine.HasKey;
import jac.engine.PlayerDetails;
import jac.engine.ruleset.Ruleset;
import java.util.List;

/**
 * Created this to make programming easier.
 * @author Gregory Jordan
 */
abstract class PartCodeReuse implements UnitPart, HasKey{
    
    private final GenericPart generalPartDetails;

    public PartCodeReuse(GenericPart generalPartDetails) {
        this.generalPartDetails = generalPartDetails;
    }
    
    
        @Override
    public boolean available(GenericUnit unit, PlayerDetails player, Ruleset rules) {
        return generalPartDetails.available(unit, player, rules);
    }

    @Override
    public Effect getEmpireEffects() {
return generalPartDetails.getEmpireEffects();
    }

    @Override
    public int getFlatcost() {
        return generalPartDetails.getFlatcost();
    }

    @Override
    public String getKey() {
        return generalPartDetails.getKey();
    }

    @Override
    public Effect getLocalEffects() {
        return generalPartDetails.getLocalEffects();
    }

    @Override
    public List<String> getPre_requisite_technology() {
        return generalPartDetails.getPre_requisite_technology();
    }

    @Override
    public boolean visible(GenericUnit unit, PlayerDetails player) {
       return generalPartDetails.visible(unit, player);
    }
}
