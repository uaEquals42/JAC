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

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gregory Jordan
 */
public class QuestionYN {
	String key;
	String dialog;
	String agree;
	String dis_agree;
	List<String> tags;
	
	public QuestionYN(){
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
