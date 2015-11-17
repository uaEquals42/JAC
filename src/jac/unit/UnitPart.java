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

import jac.engine.PlayerDetails;
import jac.engine.ruleset.Ruleset;
import java.util.List;

/**
 *
 * @author Gregory Jordan
 */
public interface UnitPart {

    /**
     * Is this part usable for the current configuration?  Is there a reason you can't use this part?
     * @param unit the unit this is a part of.
     * @param player
     * @return
     */
    boolean available(GenericUnit unit, PlayerDetails player, Ruleset rules);

    Effect getEmpireEffects();

    int getFlatcost();

    String getKey();

    Effect getLocalEffects();

    List<String> getPre_requisite_technology();

    boolean visible(GenericUnit unit, PlayerDetails player);
    
}
