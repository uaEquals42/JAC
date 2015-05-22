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
import jac.engine.dialog.Quote;
import jac.unit.partTranslation.FacilityTranslation;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * A representation of facilities - regular and secret.
 *
 * @author Gregory Jordan
 */
public class Facility extends PartCodeReuse {

    private final boolean secret_project;
    private final int maintence;
    private Map<Locale, FacilityTranslation> translations;

    private Facility(Builder build) {
        super(build.generalPartDetails);
        this.translations = build.translations;

        maintence = build.maintence;
        secret_project = build.secret_project;
    }

    public Noun getName(Locale language) {
        return translations.get(language).getFullName();
    }

    public String getShortDescription(Locale language) {
        return translations.get(language).getShortDescription();
    }

    public List<Quote> getQuotes(Locale language) {
        return translations.get(language).getQuote_list();
    }

    public boolean isSecret_project() {
        return secret_project;
    }

    public int getMaintence() {
        return maintence;
    }

    public static class Builder {

        // Required paramaters
        private final int maintence;  // cost in energy per turn for this.  //TODO: Should i make this part of all unitparts?  Move to OldUnitPart?

        private final GenericPart generalPartDetails;

        private Map<Locale, FacilityTranslation> translations;

        // optional parameters - initiallized to default values.
        private boolean secret_project = false;

        /**
         * Builder method for facilities. Using this will allow some flexibility
         * on adding future arguments when constructing Facilities.
         *
         * @param generalPartDetails
         * @param cost
         * @param maintence
         */
        public Builder(GenericPart generalPartDetails, int cost, int maintence, FacilityTranslation translation) {
            this.generalPartDetails = generalPartDetails;
            this.maintence = maintence;
            this.translations.put(translation.getLanguage(), translation);
        }

        public Builder project() {
            secret_project = true;
            return this;
        }

        public Facility build() {

            return new Facility(this);
        }

    }

}
