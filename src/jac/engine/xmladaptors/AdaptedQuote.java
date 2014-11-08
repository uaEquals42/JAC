/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jac.engine.xmladaptors;

import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author grjordan
 */
public class AdaptedQuote {
    @XmlElement
    public  String quote;
    @XmlElement
    public  String person;
    @XmlElement
    public  String source;
    @XmlElement
    public  String date;
    @XmlElement
    public String note;
}
