package com.epam.brest.taskproject.web;

import com.epam.brest.taskproject.domain.Automobile;
import com.epam.brest.taskproject.service.AutomobileService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;
/**
 * Created by alesya on 25.11.14.
 */
@Controller
public class AutomobileController {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private AutomobileService automobileService;

    @RequestMapping("/")
    public String init() {
        return "redirect:/automobileList";
    }

    @RequestMapping("/automobileInputForm")
    public ModelAndView launchInputForm() {
        LOGGER.debug("launchInputForm()");
        return new ModelAndView("automobileInputForm", "automobile", new Automobile());
    }

    @RequestMapping("/submitAutomobileData")
    public String getInputForm(@RequestParam("make")String make,
                               @RequestParam("number")String number,
                               @RequestParam("fuelRate")String fuelRateStr) {
        Double fuelRate = Double.parseDouble(fuelRateStr);

        Automobile automobile = new Automobile();
        automobile.setMake(make);
        automobile.setNumber(number);
        automobile.setFuelRate(fuelRate);
        Long id = automobileService.addAutomobile(automobile);
        LOGGER.debug("new automobile {}", id);
        return "redirect:/automobileList";
    }

    @RequestMapping("/automobileList")
    public ModelAndView getListUsersView() {
        List<Automobile> automobiles = automobileService.getAllAutomobiles();
        LOGGER.debug("automobiles.size = " + automobiles.size());
        ModelAndView view = new ModelAndView("automobileList", "automobiles", automobiles);
        return view;
    }
}
