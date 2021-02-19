/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.persistence.impl;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.BlueprintsPersistence;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author hcadavid
 */
@Component("inMemoryBluePrintPersistence")
public class InMemoryBlueprintPersistence implements BlueprintsPersistence{

    private final Map<Tuple<String,String>,Blueprint> blueprints=new HashMap<>();

    public InMemoryBlueprintPersistence() {
        //load stub data
        Point[] pts = new Point[] { new Point(140, 140), new Point(115, 115),new Point(140, 140), new Point(105, 90) };
        Blueprint bp = new Blueprint("_authorname_", "_bpname_", pts);
        blueprints.put(new Tuple<>(bp.getAuthor(), bp.getName()), bp);
        Point[] pts1 = new Point[] { new Point(140, 140), new Point(115, 115) };
        Blueprint bp1 = new Blueprint("_authorname_", "_bpname1_", pts1);
        blueprints.put(new Tuple<>(bp1.getAuthor(), bp1.getName()), bp1);
        Point[] pts2 = new Point[] { new Point(140, 140), new Point(115, 115) };
        Blueprint bp2 = new Blueprint("_authorname_", "_bpname_2", pts2);
        blueprints.put(new Tuple<>(bp2.getAuthor(), bp2.getName()), bp2);
        Point[] pts3 = new Point[] { new Point(140, 140), new Point(115, 115) };
        Blueprint bp3 = new Blueprint("jhon", "Example1", pts3);
        blueprints.put(new Tuple<>(bp3.getAuthor(), bp3.getName()), bp3);
        Point[] pts4 = new Point[] { new Point(140, 140), new Point(115, 115) };
        Blueprint bp4 = new Blueprint("jhon", "Example2", pts4);
        blueprints.put(new Tuple<>(bp4.getAuthor(), bp4.getName()), bp4);
        
    }    
    
    @Override
    public void saveBlueprint(Blueprint bp) throws BlueprintPersistenceException {
        if (blueprints.containsKey(new Tuple<>(bp.getAuthor(),bp.getName()))){
            throw new BlueprintPersistenceException("The given blueprint already exists: "+bp);
        }
        else{
            blueprints.put(new Tuple<>(bp.getAuthor(),bp.getName()), bp);
        }        
    }

    @Override
    public Blueprint getBlueprint(String author, String bprintname) throws BlueprintNotFoundException {
        if(blueprints.containsKey(new Tuple<>(author,bprintname))) {
            return blueprints.get(new Tuple<>(author, bprintname));
        }else {
            throw new BlueprintNotFoundException("No hay planos de este autor " + author);
        }
    }

    @Override
    public Set<Blueprint> getBluePrintsByAuthor (String author) throws BlueprintNotFoundException {
        Set<Blueprint> bluePrintAuthor = new HashSet<>();
        for(Blueprint bPrint : blueprints.values()){
            if(bPrint.getAuthor().equals(author)){
                bluePrintAuthor.add(bPrint);
            }
        }
        if(bluePrintAuthor.isEmpty()){
            throw new BlueprintNotFoundException("No hay planos de este autor "+author);
        }
        return bluePrintAuthor;
    }
    
    
}
