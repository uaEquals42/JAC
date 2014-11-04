/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine.Faction;
import java.util.Locale;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 *
 * @author grjordan
 */
public class JAXLanguageAdaptor extends XmlAdapter<String, Locale>{

    /**
     * Converts a string in an xml file to a Locale.
     * @param lang_code Any language code Locale will accept.
     * @return  A locale with the correct language setting.
     * @throws Exception  If it couldn't recognize the language code.
     */
    @Override
    public Locale unmarshal(String lang_code) throws Exception {
        return new Locale(lang_code);
    }
 
    /**
     * Converts a locale to a string.
     * @param loc
     * @return ISO3 langauge code.  A 3 letter abbreviation of a language's name.
     * @throws Exception
     */
    @Override
    public String marshal(Locale loc) throws Exception {
        return loc.getISO3Language();
    }
 
}

