package jac.gui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;


public class Launcher {
	private static final Logger log = LoggerFactory.getLogger(Launcher.class);
	public Launcher() {
		// TODO Auto-generated constructor stub
		
		String[] possibilities = {"Start Game", "Set Ruleset", "Quit"};
		int test = JOptionPane.showOptionDialog(null, 
				"Welcome to Jac", 
				"JAC",
		        JOptionPane.DEFAULT_OPTION, 
		        JOptionPane.PLAIN_MESSAGE ,
		        null, 
		        possibilities, 
		        possibilities[0]);
		log.debug("JOptionPane Output {}", test);


	
	}

}
