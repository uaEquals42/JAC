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
package jac.engine.ruleset;

import jac.engine.dialog.Quote;
import jac.unit.Effect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Gregory Jordan
 */
public class Tech {
	static Logger LOG = LoggerFactory.getLogger(Tech.class);

	final String key;
	final List<Effect> effects;
	/**
	 * An empty list means there are no pre-requisites.
	 */
	final List<String> pre_requisites_names;

	Tech(Builder build){
		key = build.key;
		effects = build.effects;
		pre_requisites_names = build.pre_requisites_names;
		LOG.trace("Tech {} created.  Pre-reqs {}", key, pre_requisites_names);
	}


	// these 4 values are hints to the ai to indicate how good the particular tech is in each category.
	// Determines how much a particular ai wants each tech.
	int power;
	int tech;
	int infrastructure;
	int colonize;






	// Smac/x does reaserch costs via a formula.
	boolean cost_from_formula = true;
	int reasearch_cost = 5;  // Added this here for modders, so that if they want to have fixed research costs, they can.



	public String getKey() {
		return key;
	}


	public String getName(Translation tran) {
		return tran.technames.get(key);
	}

	public List<Quote> getQuotes(Translation tran) {
		return tran.tech_quotes.get(key);
	}


	public static class Builder {

		public List<Effect> effects = new ArrayList<>();
		final String key;
		List<String> pre_requisites_names = new ArrayList<>();

		Builder(String key) {
			this.key = key.trim();

		}

		Builder fromSmacConfig(String[] line) {

			if (!line[6].trim().equalsIgnoreCase("Disable")) {
				if (!line[6].trim().equalsIgnoreCase("None")) {
					pre_requisites_names.add(line[6].trim());
				}
				if (!line[7].trim().equalsIgnoreCase("None")) {
					pre_requisites_names.add(line[7].trim());
				}

				char[] flags = line[8].trim().toCharArray();
				int probe = Integer.parseInt(line[8].trim().substring(7, 8));
				int commerce_bonus = Integer.parseInt(line[8].trim().substring(6, 7));
				int fungus_nutrient_bonus = Integer.parseInt(line[8].trim().substring(0, 1));
				int fungus_mineral_bonus = Integer.parseInt(line[8].trim().substring(1, 2));
				int fungus_energy_bonus = Integer.parseInt(line[8].trim().substring(2, 3));
// TODO: Add these to the effects file.

			}
			return this;
		}



		Builder addPreRequisiteTech(String key){
			pre_requisites_names.add(key);
			return this;
		}

		public Tech build(){

				return new Tech(this);
		}
	}
}
