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

//import engine.dialog.QuestionYN;

import jac.Enum.NounSex;
import jac.engine.HasKey;
import jac.engine.dialog.Quote;
import jac.engine.dialog.SentenceFragment;

import java.util.List;
import java.util.Locale;



/**
 *
 * @author Gregory Jordan
 */


public class Faction_Dialog implements HasKey {
 
    public Locale getLanguage() {
        return language;
    }
    
    Locale language;

    String faction_name_title;
    String fact_short_description_of_ideology;
    String noun; // Plural noun

    NounSex faction_name_sexP; // The faction names gender and plurality.
    

    String leaders_name;
    NounSex leaders_gender;
    String leaders_title;
    String adjective_faction; // Adjective form of faction name.
    String Faction_noun;
    String assistant_name;
    String scientist_name;
    String assistant_city;
    String Leader_Title;
    String Leader_Bad_Adjective;
    String Faction_Adjective;
    String Faction_Insult;
    String Leader_Insult;
    // TODO: Need to figure out how I should store the dialog stuff.
    List<String> land_base_names;
    List<String> water_base_names;
    String Background;
    
    String Agenda;
// Datalinks 2 should be able to generate based on FactionSettings.
// TODO: More dialog stuff
    SentenceFragment[] fragments = new SentenceFragment[15];
    Quote faction_blurb;
	//Map<String,List<QuestionYN>> com_questions;

    /**
     * Do Not Use!  Exists for the xml parser to not freak out.
     */
    public Faction_Dialog() {
    }
    
    /**
     * Use to create and set the language of the dialog.
     * @param lang
     */
    public Faction_Dialog(Locale lang) {
        language = lang;
    }

    @Override
    public String getKey() {
        return language.getISO3Language();
    }



}
