/*
 * JAC Copyright (C) 2016 Gregory Jordan
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
import jac.Enum.WeaponRole;
import jac.engine.HasKey;
import jac.engine.PlayerDetails;
import jac.unit.partTranslation.Unit_Part_Translation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 *
 * @author Gregory Jordan
 */
public class Unit_part implements HasKey{
    static Logger log = LoggerFactory.getLogger(Unit_part.class);

    final Part_Category category;

    private Map<Locale, Unit_Part_Translation> translations;

    private final Effect localEffects;
    private final Effect empireEffects;
    private final Domain domain;

    //private final List<HasRestrictions> restrictions;
    private final boolean restrict_for_display;  // If true, the player can see the part in build menu
    private final List<String> pre_requisite_technology;  // This also has to be true.
    private final boolean secret_project; // aka secret project

    List<Domain> reqDomains = new LinkedList<>();
    List<WeaponRole> reqRoles = new LinkedList<>();
    List<String> required_chassis = new LinkedList<>();

    private final Set<String> allowed_races;
    private final String key;



    public Unit_part(Builder build){
        this.category = build.category;
        this.localEffects = build.localEffects;
        this.empireEffects = build.empireEffects;
        this.allowed_races = build.allowed_races;
        this.restrict_for_display = build.restrict_for_display;

        this.pre_requisite_technology = build.pre_requisite_technology;
        this.key = build.key;
        this.secret_project = build.secret_project;
        this.domain = build.domain;
    }


    @Override
    public String getKey(){
        return key;
    }

    public Part_Category getCategory() {
        return category;
    }

    public Unit_Part_Translation get_translation(Locale language){
        return translations.get(language);
    }

    public List<String> getPre_requisite_technology() {
        return pre_requisite_technology;
    }

    public static List<String> createlist(String onePreRequisite){
        List<String> pre_reqs = new ArrayList<>();
        if (!onePreRequisite.trim().equalsIgnoreCase("None")) {
            pre_reqs.add(onePreRequisite.trim());
        }
        return pre_reqs;
    }

    public boolean visible(PlayerDetails player){
        // Is the pre-requisite tech needs met?
        for(String tech : pre_requisite_technology){
            if(! player.getKnownTechnologies().contains(tech)){
                return false;
            }
        }

       if(!allowed_races.isEmpty()){
           if(!allowed_races.contains(player.getFaction().getRace())){
               return false;
           }
       }

       return true;
    }



    public Effect getLocalEffects() {
        return localEffects;
    }

    public Effect getEmpireEffects() {
        return empireEffects;
    }


    public static class Builder {
         private Domain domain = null;
        private Map<Locale, Unit_Part_Translation> translations = new HashMap<>();
        private final Part_Category category;
        private final String key;
        List<Domain> reqDomains = new LinkedList<>();
        List<WeaponRole> reqRoles = new LinkedList<>();
        List<String> required_chassis = new LinkedList<>();
        private boolean secret_project=false;

        private Boolean restrict_for_display = true;

        private Effect localEffects = new Effect.Builder().build();
        private Effect empireEffects = null;

        private List<String> pre_requisite_technology = new ArrayList<String>();  // This also has to be true.
        private Set<String> allowed_races = new LinkedHashSet<>();


        public Builder(String key, Part_Category category, Unit_Part_Translation dialog) {
            this.category = category;
            this.key = key;
            translations.put(dialog.getLanguage(), dialog);
        }

        public Builder add_translation(Locale language, Unit_Part_Translation dialog){
            translations.put(language, dialog);
            return this;
        }


        public Builder set_Domain(Domain domain){
            this.domain = domain;
            return this;
        }
        
        public Builder restrictToRace(String race) {
            allowed_races.add(race);
            return this;
        }

        public Builder addPreRequisiteTech(String techkey) {
            if (!techkey.trim().equalsIgnoreCase("None")) {
                pre_requisite_technology.add(techkey.trim());
            }
            return this;
        }

        public Builder setLocalEffects(Effect localEffects) {
            this.localEffects = localEffects;
            return this;
        }

        public Builder setEmpireEffects(Effect empireEffects) {
            this.empireEffects = empireEffects;
            return this;
        }

        public Builder is_secret_project(boolean is_it){
            secret_project = is_it;
            return this;
        }
        
        public Builder smacAbilityFlags(String smacFlags) {

            smacFlags = smacFlags.trim();
            if (smacFlags.charAt(0) == '1') {
                //#TODO: increaseLandCost = true;
            }
            if (smacFlags.charAt(1) == '1') {
                required_chassis.add("0");

            }

            /*
             10          00000000001 = Allowed for Land units
             9          00000000010 = Allowed for Sea units
             8          00000000100 = Allowed for Air units
             7          00000001000 = Allowed for Combat units
             6          00000010000 = Allowed for Terraformer units
             5          00000100000 = Allowed for Noncombat units (non-terraformer)
             4          00001000000 = Not allowed for probe teams
             3          00010000000 = Not allowed for psi units
             2          00100000000 = Transport units only
             1          01000000000 = Not allowed for fast-moving units
             0          10000000000 = Cost increased for land units
             */
            if (smacFlags.charAt(3) == '0') {
                // PSI Unit Flag.  If 1 then not allowed. So if 0, then it is allowed.
                reqRoles.add(WeaponRole.PSI);

            }

            if (smacFlags.charAt(5) == '1') {
                // 5          00000100000 = Allowed for Noncombat units (non-terraformer)
                if (smacFlags.charAt(4) == '0') {
                    reqRoles.add(WeaponRole.PROBE);

                }
                reqRoles.add(WeaponRole.CONVOY);
                reqRoles.add(WeaponRole.TRANSPORT);

            }

            if (smacFlags.charAt(6) == '1') {
                //6          00000010000 = Allowed for Terraformer units
                reqRoles.add(WeaponRole.TERRAFORMER);

            }
            if (smacFlags.charAt(7) == '1') {
                reqRoles.add(WeaponRole.ENERGY);
                reqRoles.add(WeaponRole.MISSILE);
                reqRoles.add(WeaponRole.PROJECTILE);

            }

            if (smacFlags.charAt(2) == '1') {
                reqRoles.clear();
                reqRoles.add(WeaponRole.TRANSPORT);

            }

            if (smacFlags.charAt(8) == '1') {
                reqDomains.add(Domain.AIR);

            }

            if (smacFlags.charAt(9) == '1') {
                reqDomains.add(Domain.SEA);

            }
            if (smacFlags.charAt(10) == '1') {
                reqDomains.add(Domain.LAND);

            }

            // Now use this data to create the restrictions.
            if (reqDomains.size() == Domain.COUNT) {
                reqDomains.clear();  // Optimization.  The code for checking assumes all are true if the list is empty.
            }
            if (reqRoles.size() == WeaponRole.COUNT) {
                reqRoles.clear();  // optimization.  If all are allowed, then we don't need to have them tested.
            }

            return this;

        }

        public Unit_part build() {
            return new Unit_part(this);
        }

    }


}
