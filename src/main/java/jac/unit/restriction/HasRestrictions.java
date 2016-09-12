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

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class HasRestrictions implements Restriction{
	private static final Logger LOG = LoggerFactory.getLogger(HasRestrictions.class);


	final List<Domain> reqDomains;
	final List<WeaponRole> reqRoles;
	private final Set<String> allowed_races;

	public boolean allowed(List<Domain> domains, List<WeaponRole> roles, String race){
		boolean test = true;
		if(!reqDomains.isEmpty()){
			test = test && domains.containsAll(reqDomains);
		}
		if(!reqRoles.isEmpty()){
			test = test && roles.containsAll(reqRoles);
		}

		if(!allowed_races.isEmpty()){
			test = test && allowed_races.contains(race);
		}
		return test;
	}


	HasRestrictions(Builder build){
		this.allowed_races = build.allowed_races;
		this.reqDomains = build.reqDomains;
		this.reqRoles = build.reqRoles;
	}


	public static class Builder{
		Set<String> allowed_races = new HashSet<>();
		List<Domain> reqDomains = new LinkedList<>();
		List<WeaponRole> reqRoles = new LinkedList<>();

		public Builder restrictToSpecificRaces(String racename){
			allowed_races.add(racename);
			return this;
		}

		public Builder restrictToSpecicDomains(Domain domain){
			reqDomains.add(domain);
			return this;
		}

		public Builder restrictToSpecificRoles(WeaponRole role){
			reqRoles.add(role);
			return this;
		}

		HasRestrictions build(){
			return new HasRestrictions(this);
		}
	}

}
