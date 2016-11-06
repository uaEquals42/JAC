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

import jac.Enum.SocialAreas;
import jac.engine.ruleset.SectionNotFoundException;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.EnumMap;

import static org.junit.Assert.assertEquals;

/**
 *
 * @author Gregory Jordan
 */
@Ignore public class TestFaction {
    
	
    public TestFaction(Builder build) {
    	FactionLocation = MOD_LOCATION.resolve(build.FileName);
    	this.socialTests = build.socialTests;
    	this.number_of_techs = build.number_of_techs;
    	this.list_of_techs = build.list_of_techs;
    }
    

    
    private final Path FactionLocation;
    private final EnumMap<SocialAreas, Integer> socialTests;
    private final Integer number_of_techs;
    private final ArrayList<String> list_of_techs;
    
    static org.slf4j.Logger log = LoggerFactory.getLogger(TestFaction.class);
    static Faction instance;
    static final Path MOD_LOCATION = Paths.get("src/test/resources");
    @Before
    public void setUp() throws IOException, SectionNotFoundException {

        instance = new Faction.Builder().loadSmacFactionFile(FactionLocation).build();
        instance.to_json(MOD_LOCATION);
        instance = new Faction.Builder().loadJson(MOD_LOCATION.resolve(Faction.FACTION_FOLDER).resolve(instance.getKey())).build();

    }
    
      
    @Test
    public void socialTest() {
    	if(socialTests.size() > 0){
    		for(SocialAreas key : socialTests.keySet()){
    	    	   assertEquals(key.toString(), socialTests.get(key),  instance.setting.social.get(key) );
    	       }
    	}

    }

    @Test
    public void test_tech() {
    	if(number_of_techs!=null){
    		assertEquals("Number of Techs", (int) number_of_techs, instance.setting.Free_Techs.size());
    	}
        for(String key : list_of_techs){
        	assertEquals(key, true, instance.setting.Free_Techs.contains(key));
        }
        
    }
    
    public static class Builder{
    	private final String FileName;
    	private Integer number_of_techs = null;
    	private EnumMap<SocialAreas, Integer> socialTests = new EnumMap<SocialAreas, Integer>(SocialAreas.class);
    	private ArrayList<String> list_of_techs = new ArrayList<String>();
    	
    	public Builder(String FileName){
    		this.FileName = FileName;
    	}
    	
    	public Builder addSocialTest(SocialAreas key, Integer result){
    		socialTests.put(key, result);
			return this;
    		
    	}
    	
    	public Builder numberOfTechs(int number){
    		number_of_techs = number;
    		return this;
    	}
    	
    	public Builder addFreeTech(String techKey){
    		list_of_techs.add(techKey);
    		return this;
    	}
    	
    	public Builder build(){
    		return this;
    	}
    }
    
}
