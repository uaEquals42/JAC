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
import jac.engine.Faction.FactionSettings;
import jac.engine.ruleset.SectionNotFoundException;
import java.io.File;
import java.io.FileReader;
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
    
    
            /**
         * Will find the first line that equals the string key.
         *
         * @param key The line you are looking for.
         * @param input The list of strings you are searching.
         * @return The line number where key was found. Returns -1 if it wasn't
 found.
         */
        public static int findKey(String key, List<String> input) throws SectionNotFoundException {
            for (int line = 0; line < input.size(); line++) {
                if (input.get(line).trim().toLowerCase().startsWith(key.toLowerCase())) {
                    log.trace("gotosection tag: {}  line: {}", key, line);
                    return line;
                }
            }

            log.error("Section {} not found!", key);
            throw new SectionNotFoundException();  // TODO:  Change this to an exception?
        }
    
        
        
        
    public static List<String> readSection(List<String> strlist, String code) throws SectionNotFoundException {

        ArrayList<String> tmp = new ArrayList<>();
        int line = findKey(code, strlist);

        // now we read in strings until we hit #END
        line++;
        while (line < strlist.size() && !strlist.get(line).startsWith("#")) {
            tmp.add(strlist.get(line).trim());
            line++;
        }

        return tmp;
    }



    public static int nextLine(int line, List<String> strlist) {
        line++;
        while (strlist.get(line).startsWith(";")) {
            line++;
        }
        return line;
    }
    
    
    
    public static List<Path> listFiles(Path location, String glob) throws IOException{
        List<Path> paths = new ArrayList<>();
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
        List<Path> paths = new ArrayList<>();
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
    
    
    
    
    public static String object_to_json(Object some_object){
        GsonBuilder builder = new GsonBuilder();
        builder = builder.setPrettyPrinting().serializeNulls();
        Gson gson = builder.create();

        return gson.toJson(some_object);
    }
    
    
    public static void to_json_file(Path location, String filename, Object item) throws IOException{
        create_folder(location);
        try (FileWriter file = new FileWriter(location.resolve(filename).toString()+".json")) {
                file.write(FileHelpers.object_to_json(item));        
            }
    }
    
    public static void hasKey_to_json_file(Path location, HasKey item) throws IOException{
        to_json_file(location, item.getKey(), item);   
    }
    
    
    public static void map_to_Json(Path location, Map<?, ? extends HasKey> map) throws IOException {       
        for (HasKey value : map.values()) {
            hasKey_to_json_file(location, value);
        }
    }

    /**
     * Used for saving each item in a list to it's own json file.
     *
     * @param location
     * @param the_list
     * @throws IOException
     */
    public static void list_to_json(Path location, List<? extends HasKey> the_list) throws IOException {
        create_folder(location);
        for (HasKey item : the_list) {
            hasKey_to_json_file(location, item);
        }
    }

}
