/*
 * JAC Copyright (C) 2016  - Gregory Jordan
 *
 * This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package jac.unit.restriction;/**
 * Created by Gregory Jordan on 9/8/2016.
 */

import jac.Enum.Domain;
import jac.Enum.WeaponRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class NoRestriction implements Restriction{
	private static final Logger LOG = LoggerFactory.getLogger(NoRestriction.class);

	@Override
	public boolean allowed(List<Domain> domains, List<WeaponRole> roles, String race) {
		return true;
	}
}
