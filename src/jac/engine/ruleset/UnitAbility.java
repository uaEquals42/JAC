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

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Gregory Jordan
 */
public class UnitAbility {
    private final Logger log = LoggerFactory.getLogger(this.getClass().getName());
    
    //Required 
    private final String key;
  
    //Optional
    // Doing this differently.  // TODO: Figure out a better way to store the cost, make it more flexible (or clear) as to what effect we want.
    private final int cost_code;
    
    
    private final List<String> pre_reqs;
    private final boolean land_unit;  // allowed for land units.
    private final boolean sea_unit;
    private final boolean air_unit;
    private final boolean combat_unit;
   
    

    // Decided to remove the double negatives.
    private final boolean psi_unit;  // TODO: allowed for psi based units (is this for both offence and defence?) will need to check in game.

    // Broke it up into individual non-combat types.
    private final boolean terraformer_units; // allowed for terraformers
    private final boolean probe_units;
    private final boolean transport_units;
    private final boolean convoy_unit;

    private final int max_speed_allowed;  // there was a not allowed for fast-moving units flag.  Need to know what speed was considered fast.  -1 means no limit.

    private final boolean cost_increased_land; // increased cost for land units.  TODO: figure out the formula for this to use in teh unit_plan section.

    
     public UnitAbility(Builder build){
        Translation tran = build.tran;
        key = build.key;
        cost_code = build.cost_code;
        pre_reqs = build.pre_reqs;
        land_unit = build.land_unit;
        sea_unit = build.sea_unit;
        air_unit = build.air_unit;
        combat_unit = build.combat_unit;
        psi_unit = build.psi_unit;
        terraformer_units = build.terraformer_units;
        probe_units = build.probe_units;
        transport_units = build.transport_units;
        convoy_unit = build.convoy_unit;
        max_speed_allowed = build.max_speed_allowed;
        cost_increased_land = build.cost_increased_land;
        
    }
    
    
    
    public static class Builder {

        // Required
        private final String key;
        private final String[] namedescrip;
        private final Translation tran;
                
        // Optional
        private int cost_code = 0;
        private List<String> pre_reqs = new ArrayList<>();
        private boolean land_unit = true;  // allowed for land units.
        private boolean sea_unit = true;
        private boolean air_unit = true;
        private boolean combat_unit = true;

        // Decided to remove the double negatives.
        private boolean psi_unit = true;  // TODO: allowed for psi based units (is this for both offence and defence?) will need to check in game.

        // Broke it up into individual non-combat types.
        boolean terraformer_units = true; // allowed for terraformers
        boolean probe_units = true;
        boolean transport_units = true;
        boolean convoy_unit = true;

        int max_speed_allowed = -1;  // there was a not allowed for fast-moving units flag.  Need to know what speed was considered fast.  -1 means no limit.

        boolean cost_increased_land = false;
        
        public Builder(String key, Translation tran, String name, String code, String description){
            this.key = key;
            this.tran = tran;
            namedescrip = new String[]{name.trim(), code.trim(), description.trim()};   
        }

        public Builder setCost_code(int cost_code) {
            if(cost_code<-7){
                 throw new IllegalArgumentException("Reached end of switch statement.  Invalid number supplied.");
            }
            this.cost_code = cost_code;
            return this;
        }

        public Builder addPreReq(String pre_req){
            if(!pre_req.trim().equalsIgnoreCase("None")){
                pre_reqs.add(pre_req.trim());  
            }        
            return this;
        }           

        public Builder setLand_unit(boolean land_unit) {
            this.land_unit = land_unit;
            return this;
        }

        public Builder setSea_unit(boolean sea_unit) {
            this.sea_unit = sea_unit;
            return this;
        }

        public Builder setAir_unit(boolean air_unit) {
            this.air_unit = air_unit;
            return this;
        }

        public Builder setCombat_unit(boolean combat_unit) {
            this.combat_unit = combat_unit;
            return this;
        }

        public Builder setPsi_unit(boolean psi_unit) {
            this.psi_unit = psi_unit;
            return this;
        }

        public Builder setTerraformer_units(boolean terraformer_units) {
            this.terraformer_units = terraformer_units;
            return this;
        }

        public Builder setProbe_units(boolean probe_units) {
            this.probe_units = probe_units;
            return this;
        }

        public Builder setTransport_units(boolean transport_units) {
            this.transport_units = transport_units;
            return this;
        }

        public Builder setConvoy_unit(boolean convoy_unit) {
            this.convoy_unit = convoy_unit;
            return this;
        }

        public Builder setMax_speed_allowed(int max_speed_allowed) {
            this.max_speed_allowed = max_speed_allowed;
            return this;
        }

        public Builder setCost_increased_land(boolean cost_increased_land) {
            this.cost_increased_land = cost_increased_land;
            return this;
        }
        
        public Builder smacAbilityFlags(String smacFlags){
            smacFlags = smacFlags.trim();
        if (smacFlags.charAt(0) == '1') {
            cost_increased_land = true;
        }
        if (smacFlags.charAt(1) == '1') {
            max_speed_allowed = 1;
        }
        if (smacFlags.charAt(2) == '1') {
            combat_unit = false;
            terraformer_units=false;
            probe_units=false;
            convoy_unit=false;
        }
        if (smacFlags.charAt(3) == '1') {
            //Not allowed for psi units
            psi_unit = false;

        }
        if (smacFlags.charAt(4) == '1') {
            probe_units=false;
        }
        if (smacFlags.charAt(5) == '0') {
            probe_units=false;
            convoy_unit=false;
            transport_units = false;

        }
        if (smacFlags.charAt(6) == '0') {
            terraformer_units=false;
        }
        if (smacFlags.charAt(7) == '0') {
            combat_unit = false;

        }
        if (smacFlags.charAt(8) == '0') {
            air_unit=false;

        }
        if (smacFlags.charAt(9) == '0') {
            sea_unit=false;

        }
        if (smacFlags.charAt(10) == '0') {
            land_unit=false;
        }
            return this;
        }
        
        
        public UnitAbility build() {
            return new UnitAbility(this);
        }
        
        
    }

    public int calc_cost(int base_cost, Weapon wep, Chasis chas, Armor arm) {
        
        
        /*
         ; Special Unit Abilities

         ; Cost   = Cost factor of ability
         ;          1+ = Straight Cost; 25% increase per unit of cost
         ;           0 = None
         ;          -1 = Increases w/ ratio of weapon to armor: 0, 1, or 2.
         ;               Rounded DOWN. Never higher than 2.
         ;               Examples: For a W1,A2 unit, cost is 0
         ;                         For a W3,A2 unit, cost is 1 (3/2 rounded down)
         ;                         For a W6,A3 unit, cost is 2
         ;          -2 = Increases w/ weapon value
         ;          -3 = Increases w/ armor value
         ;          -4 = Increases w/ speed value
         ;          -5 = Increases w/ weapon+armor value
         ;          -6 = Increases w/ weapon+speed value
         ;          -7 = Increases w/ armor+speed value
         */
        // http://alphacentauri2.info/index.php?topic=12897.msg61597#msg61597
        /*
        Acording to Yitzi
        Ok (weapon, armor, and chassis all refer to the weapon/armor/chassis cost):
         -1 costs 0 if weapon<armor, 1 if 2*armor>weapon>=armor, and 2 if weapon>=2*armor.
         -2 has cost equal to weapon-1.
         -3 has cost equal to armor-1.
         -4 has cost equal to chassis-1.
         -5 has cost equal to weapon+armor-2.
         -6 has cost equal to weapon+chassis-2.
         -7 has cost equal to armor+chassis-2.
        
        It's used as if that were the positive ability cost, i.e. positive total_unit_cost = (unit_cost *(1+ability_cost / 4))
         */

        
        
        
        int value=0;
        if (cost_code > 0) {
            value = cost_code;
        }
        switch (cost_code) {
            case 0:
                return 0;
            case -1:
                value = wep.getCost() / arm.getCost();
                break;
            case -2:
                 value = wep.getCost() - 1;
                break;
            case -3:
                value = arm.getCost() - 1;
                break;
            case -4:
                value = chas.getCost() - 1;
                break;
            case -5:
                value = wep.getCost() + arm.getCost() - 2;
                break;
            case -6:
                value = wep.getCost() + chas.getCost() - 2;
                break;
            case -7:
                value = arm.getCost() + chas.getCost() - 2;
                break;
            default:
                log.error("Reached end of switch statement.  Invalid number supplied.");
                // This could should never be reached.  Its mostly here in cose someone accidently removes a check someplace.
                throw new IllegalArgumentException("Reached end of switch statement.  Invalid number supplied.");
                
                

        }
        return base_cost * value / 4;     
    }
   

    // http://strategywiki.org/wiki/Sid_Meier%27s_Alpha_Centauri/Special_Ability


}
