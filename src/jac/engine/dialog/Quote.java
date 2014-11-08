/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jac.engine.dialog;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Gregory
 */
public class Quote {

    @XmlElement
    String quote;
    @XmlElement
    String person;
    @XmlElement
    String source;
    @XmlElement
    String date;

    private static final Logger log = LoggerFactory.getLogger(Quote.class);

    

    public Quote(String quote, String person, String source) {
        this.quote = quote;
        this.person = person;
        this.source = source;
        
        log.debug("Q  :{}", quote);
        log.debug("By :{}", person);
        log.debug("F  :{}", source);
    }
    
    public Quote(String quote, String person, String source, String date) {
        this.quote = quote;
        this.person = person;
        this.source = source;
        this.date = date;
        
        log.debug("Q  :{}", quote);
        log.debug("By :{}", person);
        log.debug("F  :{}", source);
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
     * @return List<Quote> Is a list of quotes that it has found for the given subject.  Necessary since there is one that has two quotes.
     */
    public static List<Quote> readblurb(int line, List<String> strlist) {
        String quote="";
        String person="";
        String source="";
        List<Quote> l_quotes = new ArrayList<>();
        
        
        
        if (strlist.get(line).startsWith("^")) {
            quote = strlist.get(line).substring(1).trim() + " ";
        } else {
            quote = strlist.get(line).trim() + " ";
        }

        line++;
        
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
            person = clean(tmp_string.get(0));
            source = clean(tmp_string.get(1));
            String date = tmp_string.get(2);
            
            l_quotes.add(new Quote(quote,person, source, date));
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
