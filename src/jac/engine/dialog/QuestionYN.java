/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jac.engine.dialog;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gregory
 */
public class QuestionYN {
	String key;
	String dialog;
	String agree;
	String dis_agree;
	List<String> tags;
	
	public void QuestionYN(){
		tags = null;
	}
	
	/**
	 * Configure the variables.
         * @param key
	 * @param dialog The question being asked.
	 * @param agree What is said when agreeing to the question.
	 * @param dis_agree What is said when dis-agreeing to the question.
	 */
	public void set(String key, String dialog, String agree, String dis_agree){
		this.key = key;
		this.dialog = dialog;
		this.agree = agree;
		this.dis_agree = dis_agree;
	}
	public void addtag(String tag){
		if(tags==null){
			tags = new ArrayList<String>();
		}
		tags.add(tag);
	}
}
