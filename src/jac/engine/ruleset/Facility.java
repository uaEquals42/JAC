/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jac.engine.ruleset;

import jac.engine.dialog.Quote;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author grjordan
 */
public class Facility {

    private final String key;
    boolean secret_project = false;
    int cost;
    int maintence;
    List<String> pre_reqs = new ArrayList<>();
    Translation tran;

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
            this.key = key;
            this.tran = tran;
            this.cost = cost;
            this.maintence = maintence;
            this.name_descript = new String[]{name, short_description};
        }

        public Builder project() {
            secret_project = true;
            return this;
        }

        public Builder pre_req(String pre_requisite) {
            pre_reqs.add(pre_requisite);
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

    /**
     * Used to set/change the translation being used.
     *
     * @param tran
     */
    public void set_Translation(Translation tran) {
        this.tran = tran;
    }


    
}
