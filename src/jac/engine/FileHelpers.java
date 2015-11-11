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
package jac.engine;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Iceberg7
 */
public class FileHelpers {
    /**
     * Returns a list of files that match the given criteria for the folder.
     * @param location - Folder location to search.
     * @param glob - The technical term for filtering in this method.
     * @return List of files that match the filter
     * @throws java.io.IOException
     */
    public static List<Path> listFiles(Path location, String glob) throws IOException{
        ArrayList paths = new ArrayList<>();
        DirectoryStream<Path> stream = Files.newDirectoryStream(location, glob);
        
        for(Path pp : stream){
            paths.add(pp);
        }
        return paths;
    }
    
    /**
     * Returns a list of all files for the given folder.
     * @param location
     * @return
     * @throws IOException
     */
    public static List<Path> listFiles(Path location) throws IOException{
        ArrayList paths = new ArrayList<>();
        DirectoryStream<Path> stream = Files.newDirectoryStream(location);
        
        for(Path pp : stream){
            paths.add(pp);
        }
        return paths;
    }
    
}
