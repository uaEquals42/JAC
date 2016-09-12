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

import jac.Enum.Domain;

import java.util.HashMap;

/**
 * @author Gregory Jordan
 */
public class TerrainModifier implements Terrainstat{
	private final String key;

	private final HashMap<Domain, Integer> regularMoveCosts;

	private final HashMap<Domain, Integer> maxMoveCost;  // If set, will set the maximum, regarless of other modifiers to this value for this square.


	TerrainModifier(Builder build) {
		this.key = build.key;
		regularMoveCosts = build.regularMoveCosts;
		maxMoveCost = build.maxMoveCost;
	}



	public HashMap<Domain, Integer> getMaxMoveCost() {
		return maxMoveCost;
	}

	@Override
	public String name() {
		return key;
	}

	@Override
	public int getNormalMovementPoints(Domain domain) {
		return regularMoveCosts.get(domain);
	}

	@Override
	public Integer getMaxMovementPoints(Domain domain) {
		return maxMoveCost.get(domain);
	}

	public static class Builder {
		private final String key;
		private HashMap<Domain, Integer> regularMoveCosts = new HashMap<>();

		private HashMap<Domain, Integer> maxMoveCost = new HashMap<>();

		public Builder(String key) {
			this.key = key;
		}

		/**
		 * Declare the move cost for this particular terrain type.
		 * If not set for a particular Domain, it will assume it doesn't affect it.  (Equavalent of setting it to 0)
		 *
		 * @param domain Does this affect Land, Sea or Air travel?
		 * @param value  The cost of moving.
		 * @return
		 */
		public Builder setRegularMoveCost(Domain domain, Integer value) {
			regularMoveCosts.put(domain, value);
			return this;
		}

		public Builder setmaxMoveCost(Domain domain, Integer value) {
			maxMoveCost.put(domain, value);
			return this;
		}


		public TerrainModifier build() {
			return new TerrainModifier(this);
		}
	}

}