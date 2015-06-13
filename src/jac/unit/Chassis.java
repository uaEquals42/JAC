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

import jac.Enum.Domain;
import jac.engine.PlayerDetails;
import jac.engine.ruleset.*;
import jac.engine.dialog.Noun;
import jac.unit.effectRules.EffectNode;
import jac.unit.partTranslation.ChassisTranslation;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author Gregory Jordan
 */
public class Chassis extends PartCodeReuse {

       private final Domain domain;
    private final boolean missle;
    private final int range;  // how many turns from base can it go before becoming damaged/destroyed.
    private final int percentDamageWhenOutOfRange;
    

    private Chassis(Builder build) {
        super(build.generalPartDetails);
        


        this.missle = build.missle;
              
        this.domain = build.domain;

        this.range = build.range;
        this.percentDamageWhenOutOfRange = build.percentDamageWhenOutOfRange;
    }

    public List<Noun> names(Translation tran) {
        return tran.getChasis().get(getKey());
    }


    public Domain getDomain() {
        return domain;
    }

    public boolean isMissle() {
        return missle;
    }
    
   

    public static class Builder {
        private final GenericPart generalPartDetails;
        private final Domain domain;
        
       
        private final ChassisTranslation names;

        private Integer range;  // how many turns from base can it go before becoming damaged/destroyed.
        private int percentDamageWhenOutOfRange = 0;

        private boolean missle = false;
       

        public Builder(GenericPart generalPartDetails, Domain domain, ChassisTranslation names) {
            this.generalPartDetails = generalPartDetails;
            this.domain = domain;
     
            this.names = names;
        }

        public Builder setRange(int range) {
            this.range = range;
            return this;
        }

        public Builder damageDonewhenOutofrange(int percent) {
            this.percentDamageWhenOutOfRange = percent;
            return this;
        }

        public Builder ismissle(boolean missle) {
            this.missle = missle;
            return this;
        }

        public Chassis build() {
            return new Chassis(this);
        }

    }

}
