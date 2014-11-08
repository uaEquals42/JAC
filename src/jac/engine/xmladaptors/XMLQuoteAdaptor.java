/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jac.engine.xmladaptors;

import jac.engine.dialog.Quote;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 *
 * @author grjordan
 */
public class XMLQuoteAdaptor  extends XmlAdapter<String[], Quote>{
    
    @Override
    public Quote unmarshal(String[] aquote) throws Exception {
        return new Quote(aquote[0],aquote[1],aquote[2],aquote[3],aquote[4]);
    }
 
    @Override
    public String[] marshal(Quote quote) throws Exception {
        String[] tmp = new String[5];
        tmp[0] = quote.quote;
        tmp[1] = quote.person;
        tmp[2] = quote.source;
        tmp[3] = quote.date;
        tmp[4] = quote.note;
        return tmp;
    }
    
}
