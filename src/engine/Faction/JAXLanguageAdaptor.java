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

 
 
    @Override
    public Locale unmarshal(String v) throws Exception {
        return new Locale(v);
    }
 
    @Override
    public String marshal(Locale v) throws Exception {
        return v.getISO3Language();
    }
 
}

