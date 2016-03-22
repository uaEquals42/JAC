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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jac.engine.Faction.Faction_Dialog;
import jac.engine.ruleset.Ruleset;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    
    static Logger log = LoggerFactory.getLogger(FileHelpers.class);
    
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
    
    public static void create_folder(Path location){
         File folder = location.toFile();
        boolean test = folder.mkdirs();
        if (test) {
            log.debug("Created Folder: {}", location.toString());
        }
    }
    
    public static String toJson(Object some_object){
        GsonBuilder builder = new GsonBuilder();
        builder = builder.setPrettyPrinting().serializeNulls();
        Gson gson = builder.create();

        return gson.toJson(some_object);
    }
    
    public static void map_to_Json(Path location, Map map) throws IOException {
        create_folder(location);
        for (Object key : map.keySet()) {
            try (FileWriter file = new FileWriter(location.resolve(key.toString()).toString()+".json")) {
                file.write(FileHelpers.toJson(map.get(key)));
            }
        }
        
    }
    
    /**
     * Used for saving each item in a list to it's own json file.
     * @param location
     * @param the_list
     * @throws IOException 
     */
    public static void list_to_json(Path location, List<? extends HasKey> the_list) throws IOException{    
        create_folder(location);
            for(HasKey item : the_list){
                try (FileWriter file = new FileWriter(location.resolve(item.getKey()).toString()+".json")) {
                file.write(FileHelpers.toJson(item));        
            }
            
        
    }
    }

   
    
}
