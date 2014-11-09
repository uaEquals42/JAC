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
