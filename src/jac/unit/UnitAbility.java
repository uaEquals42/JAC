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

import jac.unit.effectRules.OperatorOr;
import jac.unit.effectRules.OperatorAND;
import jac.unit.effectRules.HasChassis;
import jac.unit.effectRules.HasRole;
import jac.unit.effectRules.EffectNode;
import jac.unit.effectRules.RequiredDomain;
import jac.Enum.WeaponRole;
import jac.Enum.Domain;
import jac.engine.ruleset.*;
import jac.unit.partTranslation.AbilityTranslation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Gregory Jordan
 */
public class UnitAbility extends PartCodeReuse implements UnitPart {

    private final Logger log = LoggerFactory.getLogger(this.getClass().getName());
    
    private final Map<Locale, AbilityTranslation> translations;

    

  

    public UnitAbility(Builder build) {
        super(build.generalPartDetails);
        translations = build.translations;               
    }

    

    
   
    
    
    public static class Builder{

        // Required
        private final GenericPart generalPartDetails;
        private Map<Locale, AbilityTranslation> translations = new HashMap<Locale, AbilityTranslation>();;
          
        public Builder(GenericPart generalPartDetails, AbilityTranslation translation) {
           translations.put(translation.getLanguage(), translation);
           this.generalPartDetails = generalPartDetails;
           
        }
  
        public UnitAbility build() {
            return new UnitAbility(this);
        }

    }

    

    // http://strategywiki.org/wiki/Sid_Meier%27s_Alpha_Centauri/Special_Ability
}
