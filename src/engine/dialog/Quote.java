/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package engine.dialog;

import java.util.List;

/**
 *
 * @author Gregory
 */
public class Quote {
	String quote;
	String person;
	String source;

	
	/**
	 *
	 * @param line
	 * @param strlist
	 */
	public Quote(int line, List<String> strlist){
		readblurb(line, strlist);
	}
	
	public void print(){
		System.out.println(quote);
		System.out.println(person);
		System.out.println(source);
	}
	
	public void readblurb(int line, List<String> strlist){
		String tmp_quote = "";
		while(!strlist.get(line).startsWith("^")){
			tmp_quote = tmp_quote + strlist.get(line).trim()+" ";
			line++;
		}
		quote = tmp_quote;
		line++;
		person = strlist.get(line).substring(1).trim();
		line++;
		source = strlist.get(line).substring(1).trim();
	}
}
