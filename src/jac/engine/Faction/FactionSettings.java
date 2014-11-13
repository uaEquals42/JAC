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
package jac.engine.Faction;

import jac.Enum.FreeUnitType;
import jac.Enum.SocialAreas;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author Gregory Jordan
 */
@
XmlRootElement()
@XmlAccessorType(XmlAccessType.FIELD)
public class FactionSettings {
	
	// RULES:
	@XmlElement
        int ai_fight; // willingness to fight.
        
	@XmlElement
	int ai_power; // interest in power
	@XmlElement
	int ai_tech; // interest in tech
	@XmlElement
	int ai_wealth; // interest in wealth
	@XmlElement
	int ai_growth; // Not sure if I will use this variable the same way.  Might just make them more or less agressive on growth.
	@XmlElement
	List<String> Free_Techs = new ArrayList<>(); //TODO: List of tech names this faction gets for free.  (What happens when someone uses this with a modded techtree that doesn't have that tech?
	
        @XmlElement
        int num_of_free_techs=0; // A number of techs that the player gets to choose for free at the game start.
        
        @XmlElement
	Map<SocialAreas, Integer> social = new EnumMap<>(SocialAreas.class); // social modifiers.
	@XmlElement
	int morale=0;
	/*
	 * Morale affects the training and determination of combat units, a higher score here translates directly into morale upgrades for newly built units:

    -4, -3 Morale; + modifiers halved
    -3, -2 Morale; + modifiers halved
    -2, -1 Morale; + modifiers halved
    -1, -1 Morale
    0, Normal Morale
    1, +1 Morale
    2, +1 Morale (+2 on defense in base)
    3, +2 Morale! (+3 on defense in base)
    4, +3 Morale!! 
	 */
	@XmlElement
	int psi=0; // Percentage combat bonus for PSI.  0+;
	@XmlElement
	List<String> free_facilitys = new ArrayList<>();  // List of free facilitiy types.
	@XmlElement
	List<String> free_facility_prereq = new ArrayList<>(); // List of free facilities given to player WHEN they get the technology to support it.
	@XmlElement
	int research=0; //free research points per base per turn.
	@XmlElement
	int drone_bonus=-1;  // University hits 4 people, one will then be a drone. If -1, then off.
	@XmlElement
	int talent=-1; // Free talent per x number of citizens per base.
	@XmlElement
	int energy=0; // extra energy at game start
	
	
        @XmlElement
        int interest = 0;  // Bonus or negative money earned each turn per base!.
        
        
	@XmlElement
	int commerce;  //It gives +X commerce for that faction, same as learning X "boosts commerce rate" techs or having X+2 ECONOMY (although ECONOMY can't give more than +3).
	@XmlElement
	int pop_cap_difference=0;  // Change from the defaults from the population caps for hab domes, etc calculations
	@XmlElement
	int hurry=100; // Percentage of hurry costs.  100% results in no change.
	@XmlElement
	FreeUnitType unit = FreeUnitType.NONE; // Free starting unit
	@XmlElement
	int techcost=100; // Tech modifier rate.
	@XmlElement
	int sharetech = -1;  // Get a free tech when x people know the tech already.  
        
        @XmlElement
        boolean tech_share = false;  //If true, then you have to be spying on the other factions to gain the tech in sharetech..  (via probe, empath, or govener)
        
        @XmlElement
        int terraform_cost = 100;  // Modifier to the terraform rate.  100% is default.  200% will increase time taken by 2.  50 will decrease the time in half.
        
        @XmlElement
        List<SocialAreas> robust = new ArrayList<>(); // Halves the intensity of minus effects in the named social area ("ROBUST, EFFIC" halves minus efficiency effects in social model) 
        
	@XmlElement
	List<SocialAreas> immunity= new ArrayList<>();
	@XmlElement
	List<SocialAreas> impunity= new ArrayList<>();
	@XmlElement
	List<SocialAreas> penalty= new ArrayList<>();
	@XmlElement
	int fungus_nutrient=0;
	@XmlElement
	int fungus_minerals=0; 
	@XmlElement
	int fungus_energy=0;
	@XmlElement
	int extra_frequency=0;  // 0 no extra frequences at game start.  1 = 1 extra frequency at game start, etc.
	@XmlElement
	boolean mind_control_immunity=false; // wtf... there's mind control? Is this somehting SMAX related?
	@XmlElement
	boolean fanatic=false;
	@XmlElement
	int votes;  //TODO: look up what this was supposed to be...
	@XmlElement
	boolean freeproto=false;
        @XmlElement
        boolean aquatic_faction=false;
        @XmlElement
        boolean alien_faction=false;
        
	@XmlElement
	List<String[]> pro_ideologies = new ArrayList<>();
	@XmlElement
	List<String[]> anti_ideologies = new ArrayList<>();
        @XmlElement
        AI_Emphesis ai_emphesis = AI_Emphesis.NIL;
	@XmlElement
	String free_ability; //Gives all applicable units of a faction the chosen special ability for free after discovering the abilities pre-requisite technology, using the ability's number in alpha(x).txt as the parameter. For example, FREEABIL, 1, would give all of your units the Deep Radar special ability for free after discovering Advanced Military Algorithms. This can be used to allow units to have more than two special abilities at once.
	@XmlElement
	int revolt_success_modifier=0; //Give an additional percent chance that other faction's bases with drone revolts will join a faction. For Example, REVOLT, 25, means that a faction is 25% more likely to get a enemy faction's revolting base. There are many factors that go into who gets a revolting base.
	@XmlElement
	int probe_cost=100; //This rule alters the percentage cost of your faction's probe team actions. For example, PROBECOST, 75, would make a faction's probe team cost 75% of normal and PROBECOST, 150, would make a faction's probe team cost 150% of normal. The default cost is 100%.
	@XmlElement
	int drone_reduction=0; //This rule makes a number of drones at each of your bases into normal workers. For example, NODRONE, 2, would make two drones at each of your bases into citizens.
	@XmlElement
	int offence=100; // This rule alters the strength of the faction's attacking units (including PSI Combat). For example, OFFENSE, 125, would give a strength of 125% (25% above normal) to the faction's attacking units.
	@XmlElement
	int defence=100; //This rule alters the strength of the faction's defending units (including PSI Combat). For example, DEFENSE, 75, would give a strength of 75% (25% below normal) to the faction's defending units. 
	@XmlElement
        int wormpolice = 1;  //
        @XmlElement
        boolean techsteal = false; // Faction steal a tech when it captures a base.... no matter what the rules are set to.

	
}
