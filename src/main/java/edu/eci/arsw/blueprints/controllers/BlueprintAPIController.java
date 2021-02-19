/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.controllers;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.services.BlueprintsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 *
 * @author hcadavid
 */
@RestController
@RequestMapping(value = "/blueprints")
public class BlueprintAPIController {

    @Autowired
    @Qualifier("bluePrintServices")
    BlueprintsServices bp;


    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> manejadorGetRecursoAllBlueprints(){
        try {
            //obtener datos que se enviarán a través del API
            return new ResponseEntity<>(bp.getAllBlueprints(), HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error bla bla bla",HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path  = "/{author}",method = RequestMethod.GET)
    public ResponseEntity<?> manejadorGetRecursoAllBluePrintsByAuthor(@PathVariable("author") String author){

        try {
            //obtener datos que se enviarán a través del API
            return new ResponseEntity<>(bp.getBlueprintsByAuthor(author),HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.valueOf(404));
        }
    }

    @RequestMapping(path  = "/{author}/{bpname}",method = RequestMethod.GET)
    public ResponseEntity<?> manejadorGetRecursoAllBluePrintsByAuthor(@PathVariable("author") String author,@PathVariable("bpname") String bpname){

        try {
            //obtener datos que se enviarán a través del API
            return new ResponseEntity<>( bp.getBlueprint(author, bpname),HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.valueOf(404));
        }
    }

    @RequestMapping(path = "/create",method = RequestMethod.POST)
    public ResponseEntity<?> manejadorPostRecursoAddNewBlueprint(@Valid @RequestBody Blueprint bluePrint){
        try {
            bp.addNewBlueprint(bluePrint);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception ex) {
            Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.FORBIDDEN);
        }
    }

    @RequestMapping(path = "/{author}/{bpname}",method = RequestMethod.PUT)
    public ResponseEntity<?> manejadorPostRecursoAddNewBlueprint(@PathVariable("author") String author,@PathVariable("bpname") String name,@Valid @RequestBody Blueprint bluePrint){
        try {
            bp.updatePoints(author, name, bluePrint);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.FORBIDDEN);
        }
    }


}

