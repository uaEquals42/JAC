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
package jac.engine.Faction;

/**
 *
 * @author Iceberg7
 */
public class ExceptionNoFactionDialog extends Exception {

    /**
     * Creates a new instance of <code>ExceptionNoFactionDialog</code> without
     * detail message.
     */
    public ExceptionNoFactionDialog() {
    }

    /**
     * Constructs an instance of <code>ExceptionNoFactionDialog</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ExceptionNoFactionDialog(String msg) {
        super(msg);
    }
}
