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
package jac.engine.dialog;

import jac.engine.xmladaptors.XMLQuoteAdaptor;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Gregory Jordan
 */
@XmlJavaTypeAdapter(XMLQuoteAdaptor.class)
public class Quote {

    
    public final String quote;
    public final String person;
    public final String source;
    public final String date;
    public final String note;

    private static final Logger log = LoggerFactory.getLogger(Quote.class);

    
    /**
     * Most quotes only need this.
     * @param quote
     * @param person
     * @param source 
     */
    public Quote(String quote, String person, String source) { 
        this(quote, person, source, "", "");    
    }

    /**
     * Holds the various quotes in the game in an easy to read format.
     * @param quote 
     * @param person
     * @param source - The book, article, etc that it came from.
     * @param date - If available.
     * @param note
     */
    public Quote(String quote, String person, String source, String date, String note) {
        this.quote = quote;
        this.person = person;
        this.source = source;
        this.date = date;
        this.note = note;
        
        log.trace("Quote :{}", quote);
        log.trace("By    :{}", person);
        log.trace("Source:{}", source);
        if(date.length()!=0){
            log.trace("Date  :{}", date);
        }
        if(note.length()!=0){
            log.trace("Note  :{}", note);
        }
        
        
    }

    @Override
    public String toString() {
        return quote + "\n" + person + "\n" + source;
    }

    private static String clean(String input){
        if (input.length() > 0) {
            if (input.endsWith(",")) {
                input = input.substring(0, input.length() - 1);
            }
            if (input.startsWith("--")) {
                input = input.substring(2);
            }
            input = input.trim();
        }

        if (input.endsWith("\"") && input.startsWith("\"")) {
            input = input.substring(1, input.length() - 1).trim();
        }
        return input;
    }
    
    /**
     * Helper function for reading in Blurb.txt.  Or the factions.  Oh god did this function suck to write. 
     * No consistancy in how the quotes where displayed.  So parsing them means lots of corner cases.
     * @param line - The line in the String list to start on.
     * @param strlist
     * @return Is a list of quotes that it has found for the given subject.  Necessary since there is one that has two quotes.
     */
    public static List<Quote> readblurb(int line, List<String> strlist) {
        String quote;
        String person;
        String source;
        List<Quote> l_quotes = new ArrayList<>();
        
        
        
        if (strlist.get(line).startsWith("^")) {
            quote = strlist.get(line).substring(1).trim() + " ";
        } else {
            quote = strlist.get(line).trim() + " ";
        }

        line++;
        
        // Took way longer than I want to think about to come up with a loop that would work for all the quotes.
        while (strlist.get(line).trim().length() > 1 && !strlist.get(line).trim().substring(1).trim().startsWith("--")) {
            if (strlist.get(line).startsWith("^")) {
                quote = quote + strlist.get(line).substring(1).trim() + " ";
            } else {
                quote = quote + strlist.get(line).trim() + " ";
            }
            line++;
        }
        
        quote = quote.trim();
        

        List<String> tmp_string = new ArrayList<>();

        for (; strlist.get(line).trim().startsWith("^"); line++) {
            if (strlist.get(line).trim().substring(1).trim().length() > 0) {
                tmp_string.add(strlist.get(line).trim().substring(1).trim());
            }
        }

       
        if (tmp_string.size() >= 5) {
            person = tmp_string.get(0);
            source = tmp_string.get(1);
            l_quotes.add(new Quote(quote,person, source));
            
            l_quotes.addAll(readblurb(line,strlist));

        }
        if (tmp_string.size() == 3) {
            // To handle project PYRRHO.  Its given me so much trouble.
            String[] split = tmp_string.get(0).split(",");
            person = clean(split[1].trim() + ", " + split[2].trim());
            source = clean(split[0]);
            String date = tmp_string.get(1);
            String note = tmp_string.get(2);
            
            
            l_quotes.add(new Quote(quote,person, source, date,note));
        }
        
        if (tmp_string.size() == 2) {
            person = clean(tmp_string.get(0));
            source = clean(tmp_string.get(1));
            l_quotes.add(new Quote(quote,person, source));
        }
        
        if (tmp_string.size() == 1) {
            if (tmp_string.get(0).contains(",")) {
                String[] split = tmp_string.get(0).split(",");
                person = clean(split[0]);
                source = clean(split[1]);
                l_quotes.add(new Quote(quote,person, source));
            } else {
                person = "";
                source = clean(tmp_string.get(0));
                l_quotes.add(new Quote(quote,person, source));
            }
        }
        

        


        return l_quotes;
    }
}
