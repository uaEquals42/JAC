/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jac.engine.Faction;

//import engine.dialog.QuestionYN;
import jac.engine.dialog.NounSex;
import jac.engine.dialog.Quote;
import jac.engine.dialog.SentenceFragment;
import java.util.List;
import java.util.Locale;
//import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 *
 * @author Gregory
 */


@XmlRootElement()
@XmlAccessorType(XmlAccessType.FIELD)
public class Faction_Dialog {

    @XmlJavaTypeAdapter(JAXLanguageAdaptor.class)
    Locale language;
    @XmlElement
    String faction_name_title;
    @XmlElement
    String fact_short_description_of_ideology;
    @XmlElement
    String noun; // Plural noun

    @XmlElement
    NounSex faction_name_sexP; // The faction names gender and plurality.
    
    //String fac_name_gender;
    //@XmlElement
    //String singular_plural;
    @XmlElement
    String leaders_name;
    @XmlElement
    NounSex leaders_gender;
    @XmlElement
    String leaders_title;
    @XmlElement
    String adjective_faction; // Adjective form of faction name.
    @XmlElement
    String Faction_noun;
    @XmlElement
    String assistant_name;
    @XmlElement
    String scientist_name;
    @XmlElement
    String assistant_city;
    @XmlElement
    String Leader_Title;
    @XmlElement
    String Leader_Bad_Adjective;
    @XmlElement
    String Faction_Adjective;
    @XmlElement
    String Faction_Insult;
    @XmlElement
    String Leader_Insult;
    // TODO: Need to figure out how I should store the dialog stuff.
    @XmlElement
    List<String> land_base_names;
    @XmlElement
    List<String> water_base_names;
    @XmlElement
    String Background;
    @XmlElement
    String Agenda;
// Datalinks 2 should be able to generate based on FactionSettings.
// TODO: More dialog stuff
    @XmlElement
    SentenceFragment[] fragments = new SentenceFragment[15];
    @XmlElement
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



}
