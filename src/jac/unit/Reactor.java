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
package jac.unit;

import jac.engine.dialog.Noun;
import jac.unit.partTranslation.NameTranslation;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;


/**
 *
 * @author Gregory Jordan
 */
public class Reactor extends PartCodeReuse implements UnitPart{
    private final int power;
    private final Map<Locale, NameTranslation> translations;
    


    public Reactor(Builder build){
        super(build.generalPartDetails);
        this.power = build.power;
        this.translations = build.translations;
        
    }
    
    
    public int reactor_power(){
        return power;
    }
    
    public Noun full_name(Locale language){
        return translations.get(language).getFullName();
    }
    
    public Noun short_name(Locale language){
        return translations.get(language).getShortName();
    }
    
    public static class Builder{
        private final int power;
        private final Map<Locale, NameTranslation> translations;
        private final GenericPart generalPartDetails;
        
        public Builder(GenericPart generalPartDetails, int power, Locale language, Noun full_name, Noun short_name){
            this.generalPartDetails = generalPartDetails;
            this.power = power;
            translations = new LinkedHashMap<>();
            translations.put(language, new NameTranslation(language, short_name, full_name));
        }

        public Reactor build() {
            return new Reactor(this);
        }
        
       
    
    }
    
}
