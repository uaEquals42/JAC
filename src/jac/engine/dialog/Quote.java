/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jac.engine.dialog;

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

        private static final Logger log = LoggerFactory.getLogger(Quote.class);
	
	/**
	 *
	 * @param line
	 * @param strlist
	 */
	public Quote(int line, List<String> strlist){
		readblurb(line, strlist);
	}
        
        public Quote(){
            
        }
        
        public Quote(String quote, String person, String source){
            this.quote = quote;
            this.person = person;
            this.source = source;
        }
	
        @Override
	public String toString(){
            return quote + "\n" + person + "\n" + source;
        }
	
	public void readblurb(int line, List<String> strlist){
		String tmp_quote;
               
                if(strlist.get(line).startsWith("^")){
                    tmp_quote = strlist.get(line).substring(1).trim()+" ";
                }
                else{
                    tmp_quote = strlist.get(line).trim()+" ";
                }
                
                line++;
		while(!strlist.get(line).trim().startsWith("^")){
			tmp_quote = tmp_quote + strlist.get(line).trim()+" ";
			line++;
		}
		quote = tmp_quote.trim();
                
		if(strlist.get(line).trim().substring(1).trim().length()==0){
                    line++;
                }
		person = strlist.get(line).trim().substring(1).trim(); 
                if(person.endsWith(",")){
                    person = person.substring(0, person.length()-1);
                }
                if(person.startsWith("--")){
                    person = person.substring(2);
                }
                person=person.trim();
		line++;
                log.debug("person {}", person);
		source = strlist.get(line).trim().substring(1).trim();
                if(source.endsWith("\"") && source.startsWith("\"")){
                    source = source.substring(1, source.length()-1).trim();
                }
                log.trace(quote);
                log.trace(person);
                log.trace(source);
                
	}
}
