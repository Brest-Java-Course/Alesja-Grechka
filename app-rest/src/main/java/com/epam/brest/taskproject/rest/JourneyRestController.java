package com.epam.brest.taskproject.rest;

import com.epam.brest.taskproject.domain.Journey;
import com.epam.brest.taskproject.domain.AutomobileSummary;
import com.epam.brest.taskproject.service.JourneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
/**
 * Created by alesya on 24.11.14.
 */

@Controller
@RequestMapping("/journeys")
public class JourneyRestController {
    //TODO: reg exp for date
    //TODO: fix problem with date

    @Autowired
    JourneyService journeyService;

    public static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");
    public static final String regExpDateFormat =
            "(19|20)\\d\\d-((0[1-9]|1[012])-(0[1-9]|[12]\\d)|(0[13-9]|1[012])-30|(0[13578]|1[02])-31) ";

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Long> addJourney(@RequestBody Journey journey){
        try{
            Long journeyId = journeyService.addJourney(journey);
            return new ResponseEntity(journeyId,HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/{journeyId}", method = RequestMethod.DELETE)
    public ResponseEntity removeJourney(@PathVariable Long journeyId){
        journeyService.removeJourney(journeyId);
        return new ResponseEntity("", HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity updateJourney(@RequestBody Journey journey){
        try{
            journeyService.updateJourney(journey);
            return new ResponseEntity("", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/{journeyId}", method = RequestMethod.GET)
    public ResponseEntity<Journey> getJourneyById(@PathVariable Long journeyId){
        Journey journey = journeyService.getJourneyById(journeyId);
        if(journey != null){
            return new ResponseEntity(journey, HttpStatus.OK);
        } else{
            return new ResponseEntity(journey, HttpStatus.NOT_FOUND);
        }
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Journey>> getAllJourneys(){
        List journeys =  journeyService.getAllJourneys();
        return new ResponseEntity(journeys, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/date1/{date1}/date2/{date2}", method = RequestMethod.GET)
    public ResponseEntity<List<Journey>> getJourneys(
            @PathVariable String date1,
            @PathVariable String date2) throws Exception{
        Date dateFrom = SDF.parse(date1);
        Date dateTo = SDF.parse(date2);
        List journeys =  journeyService.getJourneys(dateFrom, dateTo);
        return new ResponseEntity(journeys, HttpStatus.OK);

    }

    @ResponseBody
    @RequestMapping(value = "/automobileId/{automobileId}", method = RequestMethod.GET)
    public ResponseEntity<List<Journey>> getAllJourneysOfAutomobile(@PathVariable Long automobileId){
        List journeys =  journeyService.getAllJourneysOfAutomobile(automobileId);
        return new ResponseEntity(journeys, HttpStatus.OK);
    }

//    @ResponseBody
//    @RequestMapping(value = "/automobileId/{automobileId}/date1/{date1:" +regExpDateFormat+
//            "}/date2/{date2:" +regExpDateFormat+"}", method = RequestMethod.GET)
    @RequestMapping(value = "/automobileId/{automobileId}/date1/{date1}/date2/{date2}",
            method = RequestMethod.GET)
    public ResponseEntity<List<Journey>> getJourneysOfAutomobile
            (@PathVariable Long automobileId,
             @PathVariable String date1,
             @PathVariable String date2) throws Exception{
        Date dateFrom = SDF.parse(date1);
        Date dateTo = SDF.parse(date2);
        List journeys =  journeyService.getJourneysOfAutomobile(automobileId,dateFrom, dateTo);
        return new ResponseEntity(journeys, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/summary", method = RequestMethod.GET)
    public ResponseEntity<List<AutomobileSummary>> getAutomobileSummaries(){
        List summary = journeyService.getAutomobileSummaries();
        return new ResponseEntity(summary, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/summary/date1/{date1}/date2/{date2}", method = RequestMethod.GET)
    public ResponseEntity<List<AutomobileSummary>> getAutomobileSummaries(
            @PathVariable String date1,
            @PathVariable String date2) throws Exception{
        Date dateFrom = SDF.parse(date1);
        Date dateTo = SDF.parse(date2);
        List summary =  journeyService.getAutomobileSummaries(dateFrom, dateTo);
        return new ResponseEntity(summary, HttpStatus.OK);
    }
}
