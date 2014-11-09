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
package jac.engine.ruleset;

import jac.engine.dialog.Quote;
import java.util.ArrayList;
import java.util.List;

/**
 * A representation of facilities - regular and secret.
 * @author Gregory Jordan
 */
public class Facility {

    private final String key;
    private final boolean secret_project;
    private final int cost;
    private final int maintence;
    private final List<String> pre_reqs;
    private Translation tran;

    public static class Builder {

        // Required paramaters
        private final String key;
        private final int cost;
        private final int maintence;
        private final Translation tran;
        private final String[] name_descript;

        // optional parameters - initiallized to default values.
        private boolean secret_project = false;
        private List<String> pre_reqs = new ArrayList<>();
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
            this.key = key.trim();
            this.tran = tran;
            this.cost = cost;
            this.maintence = maintence;
            this.name_descript = new String[]{name.trim(), short_description.trim()};
        }

        public Builder project() {
            secret_project = true;
            return this;
        }

        public Builder pre_req(String pre_requisite) {
            if(!pre_requisite.trim().equalsIgnoreCase("None")){
                pre_reqs.add(pre_requisite.trim());
            }
            
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

    private Facility(Builder builder) {
        key = builder.key;
        tran = builder.tran;
        tran.facilities.put(key, builder.name_descript);
        tran.facilities_quotes.put(key, builder.quote_list);
        cost = builder.cost;
        maintence = builder.maintence;
        pre_reqs = builder.pre_reqs;
        secret_project = builder.secret_project;
    }

    public String getName(){
        return tran.facilities.get(key)[0];
    }
    
    public String getShortDescription(){
        return tran.facilities.get(key)[1];
    }
    
    public boolean isSecret_project() {
        return secret_project;
    }

    public int getCost() {
        return cost;
    }

    public int getMaintence() {
        return maintence;
    }

    public List<String> getPre_reqs() {
        return pre_reqs;
    }
    
    
    /**
     * Used to set/change the translation being used.
     *
     * @param tran
     */
    public void set_Translation(Translation tran) {
        this.tran = tran;
    }


    
}
