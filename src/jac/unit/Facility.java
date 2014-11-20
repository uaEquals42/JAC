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

import jac.engine.dialog.Quote;
import jac.engine.ruleset.Translation;
import java.util.ArrayList;
import java.util.List;

/**
 * A representation of facilities - regular and secret.
 * @author Gregory Jordan
 */
public class Facility extends UnitPart{


    private final boolean secret_project;
    private final int maintence;


        public String getName(Translation tran){
        return tran.getFacilities().get(getKey())[0];
    }
    
    public String getShortDescription(Translation tran){
        return tran.getFacilities().get(getKey())[1];
    }
    
    public boolean isSecret_project() {
        return secret_project;
    }


    public int getMaintence() {
        return maintence;
    }

    
    public static class Builder extends UnitPart.Builder<Builder> {

        // Required paramaters
        private final int maintence;  // cost in energy per turn for this.  //TODO: Should i make this part of all unitparts?  Move to UnitPart?
        private final String[] name_descript;

        // optional parameters - initiallized to default values.
        private boolean secret_project = false;
        private List<Quote> quote_list = new ArrayList<>();
        
        
        /**
         * Builder method for facilities. Using this will allow some flexibility
         * on adding future arguments when constructing Facilities.
         *
         * @param key - The lookup value for the translation tables.
         * @param tran
         * @param cost
         * @param maintence
         * @param name
         * @param short_description
         */
        public Builder(String key, Translation tran, int cost, int maintence, String name, String short_description) {
            super(tran, key, cost);
            
            this.maintence = maintence;
            this.name_descript = new String[]{name.trim(), short_description.trim()};
        }

        public Builder project() {
            secret_project = true;
            return this;
        }

        public Builder quotes(List<Quote> blurbs) {
            quote_list = blurbs;
            return this;
        }

        public Facility build() {
            return new Facility(this);
        }

    }

    private Facility(Builder build) {
        super(build);
        
   
        build.getTran().getFacilities().put(getKey(), build.name_descript);
        build.getTran().getFacilities_quotes().put(getKey(), build.quote_list);
     
        maintence = build.maintence;
        secret_project = build.secret_project;
    }
}
