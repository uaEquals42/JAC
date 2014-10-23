/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package engine.Faction;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Gregory
 */

@XmlRootElement()
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
	List<String> Techs; //TODO: List of tech names this faction gets for free.  (What happens when someone uses this with a modded techtree that doesn't have that tech?
	
        @XmlElement
        int num_of_free_techs=0; // A number of techs that the player gets to choose for free at the game start.
        
        @XmlElement
	List<String> social; // social modifiers.
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
	List<String> free_facilitys;  // List of free facilitiy types.
	@XmlElement
	List<String> free_facility_prereq; // List of free facilities given to player WHEN they get the technology to support it.
	@XmlElement
	int research=0; //free research points per base per turn.
	@XmlElement
	int drone_bonus=-1;  // University hits 4 people, one will then be a drone. If -1, then off.
	@XmlElement
	int talent=-1; //???
	@XmlElement
	int energy=0; // extra energy at game start
	
	// Removed interest rate because it wasn't used. And seems like it would really unbalance the game if it was.
	@XmlElement
	int commerce;
	@XmlElement
	int pop_cap_difference=0;  // Change from the defaults from the population caps for hab domes, etc calculations
	@XmlElement
	int hurry; // Percentage of hurry costs.  100% results in no change.
	@XmlElement
	String unit; // Free starting unit
	@XmlElement
	int techcost; // Tech modifier rate.
	@XmlElement
	int sharetech;
	@XmlElement
	List<String> immunity;
	@XmlElement
	List<String> impunity;
	@XmlElement
	List<String> penalty;
	@XmlElement
	int fungus_nutrient=0;
	@XmlElement
	int fungus_minerals=0; 
	@XmlElement
	int fungus_energy=0;
	@XmlElement
	boolean extra_frequency=false;
	@XmlElement
	boolean mind_control_immunity=false; // wtf... there's mind control? Is this somehting SMAX related?
	@XmlElement
	boolean fanatic=false;
	@XmlElement
	int votes;  //TODO: look up what this was supposed to be...
	@XmlElement
	boolean freeproto=false;
	@XmlElement
	List<String> pro_ideologies;
	@XmlElement
	List<String> anti_ideologies;
	@XmlElement
	String free_ability; //Gives all applicable units of a faction the chosen special ability for free after discovering the abilities pre-requisite technology, using the ability's number in alpha(x).txt as the parameter. For example, FREEABIL, 1, would give all of your units the Deep Radar special ability for free after discovering Advanced Military Algorithms. This can be used to allow units to have more than two special abilities at once.
	@XmlElement
	int revolt_success_modifier; //Give an additional percent chance that other faction's bases with drone revolts will join a faction. For Example, REVOLT, 25, means that a faction is 25% more likely to get a enemy faction's revolting base. There are many factors that go into who gets a revolting base.
	@XmlElement
	int probe_cost; //This rule alters the percentage cost of your faction's probe team actions. For example, PROBECOST, 75, would make a faction's probe team cost 75% of normal and PROBECOST, 150, would make a faction's probe team cost 150% of normal. The default cost is 100%.
	@XmlElement
	int drone_reduction; //This rule makes a number of drones at each of your bases into normal workers. For example, NODRONE, 2, would make two drones at each of your bases into citizens.
	@XmlElement
	int offence; // This rule alters the strength of the faction's attacking units (including PSI Combat). For example, OFFENSE, 125, would give a strength of 125% (25% above normal) to the faction's attacking units.
	@XmlElement
	int defence; //This rule alters the strength of the faction's defending units (including PSI Combat). For example, DEFENSE, 75, would give a strength of 75% (25% below normal) to the faction's defending units. 
	

	public void set_pro_ideologies(String addme){
		if(!addme.equalsIgnoreCase("nil")){
			if (pro_ideologies == null){
				pro_ideologies = new ArrayList<String>();
			}
			pro_ideologies.add(addme);
		}
			
	}
	
	
	public void set_anti_ideologies(String addme){
		if(!addme.equalsIgnoreCase("nil")){
			if (anti_ideologies == null){
				anti_ideologies = new ArrayList<String>();
			}
			anti_ideologies.add(addme);
		}
			
	}
}
