/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package engine.dialog;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;

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
	
	public void print(){
		System.out.println(quote);
		System.out.println(person);
		System.out.println(source);
	}
	
	public void readblurb(int line, List<String> strlist){
		String tmp_quote;
                tmp_quote = strlist.get(line).substring(1).trim()+" ";
                line++;
		while(!strlist.get(line).startsWith("^")){
			tmp_quote = tmp_quote + strlist.get(line).trim()+" ";
			line++;
		}
		quote = tmp_quote.trim();
		line++;
		person = strlist.get(line).substring(1).trim(); //TODO:  Make sure to get rid of , -- etc from this line.
                if(person.endsWith(",")){
                    person = person.substring(0, person.length()-1);
                }
                if(person.startsWith("--")){
                    person = person.substring(2);
                }
                person=person.trim();
		line++;
		source = strlist.get(line).substring(1).trim(); //TODO: Is it always in quotes?
                if(source.endsWith("\"") && source.startsWith("\"")){
                    source = source.substring(1, source.length()-1).trim();
                }
	}
}
