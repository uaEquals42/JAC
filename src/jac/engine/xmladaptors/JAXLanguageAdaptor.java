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
package jac.engine.xmladaptors;
import java.util.Locale;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 *
 * @author Gregory Jordan
 */
public class JAXLanguageAdaptor extends XmlAdapter<String, Locale>{

    /**
     * Converts a string in an xml file to a Locale.
     * @param lang_code Any language code Locale will accept.
     * @return  A locale with the correct language setting.
     * @throws Exception  If it couldn't recognize the language code.
     */
    @Override
    public Locale unmarshal(String lang_code) throws Exception {
        return new Locale(lang_code);
    }
 
    /**
     * Converts a locale to a string.
     * @param loc
     * @return ISO3 langauge code.  A 3 letter abbreviation of a language's name.
     * @throws Exception
     */
    @Override
    public String marshal(Locale loc) throws Exception {
        return loc.getISO3Language();
    }
 
}

