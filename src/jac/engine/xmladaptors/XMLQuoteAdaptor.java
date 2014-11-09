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
 * @author Gregory Jordan
 */
public class XMLQuoteAdaptor  extends XmlAdapter<AdaptedQuote, Quote>{
    
    @Override
    public Quote unmarshal(AdaptedQuote aquote) throws Exception {
        return new Quote(aquote.quote, aquote.person, aquote.source, aquote.date, aquote.note);
    }
 
    @Override
    public AdaptedQuote marshal(Quote quote) throws Exception {
        AdaptedQuote tmp = new AdaptedQuote();
        
        tmp.quote = quote.quote;
        tmp.person = quote.person;
        tmp.source = quote.source;
        tmp.date = quote.date;
        tmp.note = quote.note;
        return tmp;
    }
    
}
