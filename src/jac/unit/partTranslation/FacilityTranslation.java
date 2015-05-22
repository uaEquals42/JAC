/*
 * JAC Copyright (C) 2015 Gregory Jordan
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
package jac.unit.partTranslation;

import jac.engine.dialog.Noun;
import jac.engine.dialog.Quote;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author Gregory Jordan
 */
public class FacilityTranslation {
    private final Noun fullName;
    private final String shortDescription;
    private final List<Quote> quote_list;
    private final Locale language;

    public FacilityTranslation(Locale language, Noun fullName, String shortDescription, List<Quote> quote_list) {
        this.language = language;
        this.fullName = fullName;
        this.shortDescription = shortDescription;
        this.quote_list = quote_list;
    }

    public Locale getLanguage() {
        return language;
    }

    public Noun getFullName() {
        return fullName;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public List<Quote> getQuote_list() {
        return quote_list;
    }
    
    
}
