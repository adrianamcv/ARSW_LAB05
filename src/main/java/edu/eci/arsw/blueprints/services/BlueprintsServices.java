/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.services;

import edu.eci.arsw.blueprints.filter.BluePrintFilter;
import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.BlueprintsPersistence;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author hcadavid
 */
@Service("bluePrintServices")
public class BlueprintsServices {
   
    @Autowired
    @Qualifier("inMemoryBluePrintPersistence")
    BlueprintsPersistence bpp=null;

    @Autowired
    @Qualifier("subsamplingFilter")

    BluePrintFilter f;

    Set<Blueprint> bluePrints = new HashSet<>();

    public void addNewBlueprint(Blueprint bp){
        try{
            bpp.saveBlueprint(bp);
        } catch (BlueprintPersistenceException e) {
            e.printStackTrace();
        }
        bluePrints.add(bp);
    }
    
    public Set<Blueprint> getAllBlueprints() throws BlueprintNotFoundException {
        Set<Blueprint> allBlue=null;
        allBlue = bpp.getBlueprints();
        return allBlue;
    }
    
    /**
     * 
     * @param author blueprint's author
     * @param name blueprint's name
     * @return the blueprint of the given name created by the given author
     * @throws BlueprintNotFoundException if there is no such blueprint
     */
    public Blueprint getBlueprint(String author,String name) throws BlueprintNotFoundException{
        try{
            return  f.filter(bpp.getBlueprint(author,name));
        } catch (BlueprintNotFoundException e) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }
    
    /**
     * 
     * @param author blueprint's author
     * @return all the blueprints of the given author
     * @throws BlueprintNotFoundException if the given author doesn't exist
     */
    public Set<Blueprint> getBlueprintsByAuthor(String author) throws BlueprintNotFoundException{
        try{
            return bpp.getBluePrintsByAuthor(author);
        } catch (BlueprintNotFoundException e) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }

    public void updatePoints(String author, String name,Blueprint blue) throws BlueprintNotFoundException{
        Blueprint bluePrint =bpp.getBlueprint(author, name);
        bluePrint.setPoints(blue.getPoints());
    }
    
}
