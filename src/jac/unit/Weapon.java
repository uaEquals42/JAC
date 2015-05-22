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

import jac.Enum.WeaponRole;
import jac.engine.dialog.Noun;
import jac.unit.partTranslation.NameTranslation;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 *
 * @author Gregory Jordan
 */
public class Weapon extends PartCodeReuse {
       
    private final int offence; // -1 if psi combat.
    private final WeaponRole weaponsRole;
    private final Map<Locale, NameTranslation> translations;
   
    public int getOffence() {
        return offence;
    }

    public WeaponRole getWeaponsRole() {
        return weaponsRole;
    }

    public Noun getFullName(Locale language){
        return translations.get(language).getFullName();
    }
    
    public Noun getShortName(Locale language){
        return translations.get(language).getShortName();
    }
    
    private Weapon(Builder build){
        super(build.generalPartDetails);
        this.translations = build.translations;
        
        
        this.offence = build.offence;
        this.weaponsRole = build.com_mode;
        
        
    }
    
    public static class Builder{
        
        private final int offence; // -1 if psi combat.
     
        private final WeaponRole com_mode;
        private final Map<Locale, NameTranslation> translations;
        
        private final GenericPart generalPartDetails;
        
         
        public Builder(GenericPart generalPartDetails, int offence, WeaponRole com_mode, Locale language, Noun fullName, Noun shortName){
            translations = new HashMap<Locale, NameTranslation>(); 
            translations.put(language, new NameTranslation(language, fullName, shortName));
           
            this.offence = offence; 
            this.com_mode = com_mode;
            this.generalPartDetails = generalPartDetails;
        }
        

        
        public Weapon build(){
            return new Weapon(this);
        }
    }
    
    
    
}
