package com.epam.brest.taskproject.web;

import com.epam.brest.taskproject.domain.Automobile;
import com.epam.brest.taskproject.domain.Journey;
import com.epam.brest.taskproject.service.AutomobileService;
import com.epam.brest.taskproject.service.JourneyService;
import com.epam.brest.taskproject.service.JourneyService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
/**
 * Created by alesya on 25.11.14.
 */
@Controller
public class JourneyAutomobileController {

    private static final Logger LOGGER = LogManager.getLogger();

    public static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    private AutomobileService automobileService;

    @Autowired
    private JourneyService journeyService;

    @RequestMapping("/")
    public String init() {
        return "redirect:/dataList";
    }

    @RequestMapping("/inputFormAutomobile")
    public ModelAndView launchAutomobileInputForm() {
        LOGGER.debug("launchInputForm()");
        return new ModelAndView("inputFormAutomobile", "automobile", new Automobile());
    }

    @RequestMapping("/submitAutomobileData")
    public String getAutomobileInputForm(@RequestParam("make")String make,
                               @RequestParam("number")String number,
                               @RequestParam("fuelRate")String fuelRateStr) {
        Double fuelRate = Double.parseDouble(fuelRateStr);

        Automobile automobile = new Automobile();
        automobile.setMake(make);
        automobile.setNumber(number);
        automobile.setFuelRate(fuelRate);
        Long id = automobileService.addAutomobile(automobile);
        LOGGER.debug("new automobile {}", id);
        return "redirect:/dataList";

    }

    @RequestMapping("/submitJourneyData")
    public String getJourneyInputForm(@RequestParam("date")String dateStr,
                               @RequestParam("automobileId")String automobileIdStr,
                               @RequestParam("originDestination")String originDestination,
                               @RequestParam("distance")String distanceStr )throws Exception{

        //TODO: input full data of automobile from listbox;
        Double distance = Double.parseDouble(distanceStr);
        Long automobileId = Long.parseLong(automobileIdStr);
        Date date = SDF.parse(dateStr);

        Automobile automobile = new Automobile();
        automobile.setId(automobileId);

        Journey journey = new Journey();
        journey.setAutomobile(automobile);
        journey.setDate(date);
        journey.setOriginDestination(originDestination);
        journey.setDistance(distance);

        Long id = journeyService.addJourney(journey);
        LOGGER.debug("new journey: {}", id);

        return "redirect:/dataList";
    }

    @RequestMapping("/inputFormJourney")
    public ModelAndView launchJourneyInputForm() {
        LOGGER.debug("launchInputForm(): /inputFormJourney");
        return new ModelAndView("inputFormJourney", "journey", new Journey());
    }

    @RequestMapping("/dataList")
    public ModelAndView getListUsersView() {
        List<Automobile> automobiles = automobileService.getAllAutomobiles();
        List<Journey> journeys = journeyService.getAllJourneys();
        LOGGER.debug("automobiles.size = " + automobiles.size());
        ModelAndView view = new ModelAndView("dataList", "automobiles", automobiles);
        view.addObject("journeys", journeys);

        return view;
    }
}
