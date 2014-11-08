/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jac.engine.ruleset;

/**
 * If a section isn't found in a data file thats being read in.
 * @author grjordan
 */
public class SectionNotFoundException extends Exception {

    /**
     * Creates a new instance of <code>SectionNotFoundException</code> without
     * detail message.
     */
    public SectionNotFoundException() {
    }

    /**
     * Constructs an instance of <code>SectionNotFoundException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public SectionNotFoundException(String msg) {
        super(msg);
    }
}
