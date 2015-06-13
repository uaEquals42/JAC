/*
 * JAC Copyright (C) 2015 Iceberg7
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
package jac.unit.partTranslation;

import jac.engine.dialog.Noun;
import java.util.Locale;

/**
 *
 * @author Iceberg7
 */
public class ChassisTranslation {

    private final Locale language;
    private final Noun offensiveName1;
    private final Noun offensiveName2;
    private final Noun offensiveName3;
    private final Noun deffensiveName1;
    private final Noun deffensiveName2;
    private final Noun deffensiveName3;

    public ChassisTranslation(Locale language, Noun offensiveName1, Noun offensiveName2, Noun offensiveName3, Noun deffensiveName1, Noun deffensiveName2,  Noun deffensiveName3) {
        this.language = language;
        this.offensiveName1 = offensiveName1;
        this.offensiveName2 = offensiveName2;
        this.deffensiveName1 = deffensiveName1;
        this.deffensiveName2 = deffensiveName2;
        this.offensiveName3 = offensiveName3;
        this.deffensiveName3 = deffensiveName3;
    }

  
        
}
