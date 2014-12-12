package com.epam.brest.taskproject.rest;

import com.epam.brest.taskproject.domain.Automobile;
import com.epam.brest.taskproject.service.AutomobileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by alesya on 23.11.14.
 */
@Controller
@RequestMapping("/automobiles")
public class AutomobileRestController {

    @Autowired
    AutomobileService automobileService;

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Long> addAutomobile(@RequestBody Automobile automobile){
        try {
            Long automobileId = automobileService.addAutomobile(automobile);
            return new ResponseEntity(automobileId, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(null, HttpStatus.BAD_REQUEST);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/{automobileId}",method = RequestMethod.DELETE)
    public ResponseEntity removeAutomobile(@PathVariable Long automobileId){
        automobileService.removeAutomobile(automobileId);
        return new ResponseEntity("", HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity updateAutomobile(@RequestBody Automobile automobile){

        try {
            automobileService.updateAutomobile(automobile);
            return new ResponseEntity("", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @ResponseBody
    @RequestMapping(value = {"/{automobileId}"}, method = RequestMethod.GET)
    public ResponseEntity<Automobile> getAutomobileById(@PathVariable Long automobileId){
        Automobile automobile = automobileService.getAutomobileById(automobileId);
        if( automobile == null ){
            return new ResponseEntity("Automobile not found for id: "+automobileId, HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity(automobile, HttpStatus.OK);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/number/{number}", method = RequestMethod.GET)
    public ResponseEntity<Automobile>  getAutomobileByNumber(@PathVariable String number) {
        Automobile automobile = automobileService.getAutomobileByNumber(number);
        if( automobile == null ){
            return new ResponseEntity("(rest)Automobile not found for number: " + number,HttpStatus.NOT_FOUND);
        } else
        return new ResponseEntity(automobile, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Automobile>>  getAllAutomobiles(){
        List automobiles = automobileService.getAllAutomobiles();
        return new ResponseEntity(automobiles, HttpStatus.OK);
    }

}
