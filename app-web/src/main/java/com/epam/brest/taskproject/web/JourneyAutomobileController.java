package com.epam.brest.taskproject.web;

import com.epam.brest.taskproject.domain.Automobile;
import com.epam.brest.taskproject.domain.Journey;
import com.epam.brest.taskproject.domain.AutomobileSummary;
import com.epam.brest.taskproject.service.AutomobileService;
import com.epam.brest.taskproject.service.JourneyService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by alesya on 25.11.14.
 */
@Controller
public class JourneyAutomobileController {

    private static final Logger LOGGER = LogManager.getLogger();

    public static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");

    private List<AutomobileSummary> summaries = new ArrayList();
    private String summaryDateRangeMessage = new String();
    private Automobile managedAutomobile = new Automobile();
    private Journey managedJourney = new Journey();
    private void clearSummary(){
        summaries.clear();
        summaryDateRangeMessage ="";
    }

    private String relativeAddress;
    {
        try {
            relativeAddress = new String(readRelativeAddressFromPropertyFile());
        } catch (IOException e) {
            LOGGER.error("init relativeAddress err:{}",e.getMessage());
            relativeAddress = new String("");
        }
    }

    private static String readRelativeAddressFromPropertyFile() throws IOException {
        Properties properties = new Properties();
        properties.load(JourneyAutomobileController.class.getClassLoader()
                .getResourceAsStream("url.properties"));
        return  properties.getProperty("relativeAddress");
    }

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
        LOGGER.debug("launchAutomobileInputForm() ");
        ModelAndView view = new ModelAndView("inputFormAutomobile", "automobile", new Automobile());
        view.addObject("relativeAddress", relativeAddress);
        return view;
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
        clearSummary();
        return "redirect:/dataList";

    }

    @RequestMapping("/submitJourneyData")
    public String getJourneyInputForm(@RequestParam("date")String dateStr,
                                      @RequestParam("automobile")String automobileStr,
                                      @RequestParam("originDestination")String originDestination,
                                      @RequestParam("distance")String distanceStr )throws Exception{
        Double distance = Double.parseDouble(distanceStr);
        Long automobileId = Long.parseLong(automobileStr);
        Date date = SDF.parse(dateStr);
        Automobile automobile = new Automobile();
        automobile.setId(automobileId);
        Journey journey = new Journey();
        journey.setAutomobile(automobile);
        journey.setDate(date);
        journey.setOriginDestination(originDestination);
        journey.setDistance(distance);
        Long id = journeyService.addJourney(journey);
        LOGGER.debug("new journey: {} ", id);
        clearSummary();
        return "redirect:/dataList";
    }

    @RequestMapping("/inputFormJourney")
    public ModelAndView launchJourneyInputForm() {
        LOGGER.debug("launchInputForm(): /inputFormJourney");
        List<Automobile> automobiles = automobileService.getAllAutomobiles();
        ModelAndView view = new ModelAndView("inputFormJourney", "journey", new Journey());
        view.addObject("automobiles", automobiles);
        view.addObject("relativeAddress", relativeAddress);
        return view;
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

    @RequestMapping("/summary")
    public ModelAndView launchSummary(){
        ModelAndView view =new ModelAndView();
        view.addObject("summaries", summaries);
        view.addObject("summaryDateRangeMessage",summaryDateRangeMessage);
        view.addObject("relativeAddress", relativeAddress);
        return view;
    }

    @RequestMapping("/filterSummary")
    public String getSummaryBetweenDates(@RequestParam("dateFrom")String dateFrom,
                                         @RequestParam("dateTo")String dateTo)throws Exception{
        Date date1 = SDF.parse(dateFrom);
        Date date2 = SDF.parse(dateTo);
        summaries = journeyService.getAutomobileSummaries(date1, date2);
        summaryDateRangeMessage = " from "+dateFrom+" to "+dateTo;
        LOGGER.debug(" getSummaryBetweenDates: summary.size: {}", summaries.size());
        return  "redirect:/summary";
    }

    @RequestMapping("/AllSummaries")
    public String getSummary(){
        summaries = journeyService.getAutomobileSummaries();
        summaryDateRangeMessage = " all summaries";
        LOGGER.debug(" getSummary: summary.size: {}", summaries.size());
        return  "redirect:/summary";
    }

    @RequestMapping("/managerAutomobile")
    public ModelAndView launchAutomobileManager(){
        LOGGER.debug("launchAutomobileManager");
        List<Automobile> automobiles = automobileService.getAllAutomobiles();
        ModelAndView view = new ModelAndView();
        view.addObject("automobiles", automobiles);
        view.addObject("managedAutomobile", managedAutomobile);
        view.addObject("relativeAddress", relativeAddress);
        return  view;
    }

    @RequestMapping("/submitFindAuto")
    public String findAutomobileByNumber(@RequestParam("number")String number){
        LOGGER.debug("findAutomobileByNumber: {}", number);
        managedAutomobile =  automobileService.getAutomobileByNumber(number);
        return  "redirect:/managerAutomobile";
    }

    @RequestMapping("/submitSelectAuto")
    public String selectAutomobile(@RequestParam("automobile")String automobile){
        LOGGER.debug("selectAutomobile: {}", automobile);
        Long automobileId = Long.parseLong(automobile);
        managedAutomobile =  automobileService.getAutomobileById(automobileId);
        return  "redirect:/managerAutomobile";
    }

    @RequestMapping("/submitManageAuto")
    public String manageAutomobile(@RequestParam("automobileId")String automobileIdStr,
                                   @RequestParam("make") String make,
                                   @RequestParam("number") String number,
                                   @RequestParam("fuelRate") String fuelRateStr,
                                   @RequestParam("manage") String manage){
        LOGGER.debug("manageAutomobile: {}", manage);
        Long automobileId = Long.parseLong(automobileIdStr);
        Double fuelRate = Double.parseDouble(fuelRateStr);
        if(manage.equals("update")){
            Automobile automobile = new Automobile(automobileId, make, number,fuelRate);
            automobileService.updateAutomobile(automobile);
            managedAutomobile =  automobileService.getAutomobileById(automobileId);
        }
        if(manage.equals("delete")){
            automobileService.removeAutomobile(automobileId);
            managedAutomobile =  new Automobile();
        }
        clearSummary();
        return  "redirect:/managerAutomobile";
    }

    @RequestMapping("/managerJourney")
    public ModelAndView launchJourneyManager(){
        LOGGER.debug("launchJourneyManager");
        List<Automobile> automobiles = automobileService.getAllAutomobiles();
        List<Journey> journeys = journeyService.getAllJourneys();
        ModelAndView view = new ModelAndView();
        view.addObject("automobiles", automobiles);
        view.addObject("journeys", journeys);
        view.addObject("managedJourney", managedJourney);
        view.addObject("relativeAddress", relativeAddress);
        return  view;
    }

    @RequestMapping("/submitSelectJourney")
    public String selectJourney (@RequestParam("journey")String journey)
    {
        LOGGER.debug("selectJourney: {}", journey);
        Long journeyId = Long.parseLong(journey);
        managedJourney =  journeyService.getJourneyById(journeyId);
        return  "redirect:/managerJourney";
    }

    @RequestMapping("/submitManageJourney")
    public String manageJourney(@RequestParam("journey")String journeyIdStr,
                                @RequestParam("automobile")String automobileIdStr,
                                @RequestParam("date")String dateStr,
                                @RequestParam("originDestination")String originDestination,
                                @RequestParam("distance")String distanceStr,
                                @RequestParam("manage") String manage) throws Exception{
        LOGGER.debug("manageJourney");
        Long journeyId = Long.parseLong(journeyIdStr);
        Long automobileId = Long.parseLong(automobileIdStr);
        Date date = SDF.parse(dateStr);
        Double distance = Double.parseDouble(distanceStr);
        if(manage.equals("update")){
            Automobile automobile = new Automobile();
            automobile.setId(automobileId);
            Journey journey = new Journey(journeyId, automobile, date, originDestination, distance);
            journeyService.updateJourney(journey);
            managedJourney = journeyService.getJourneyById(journeyId);
        }
        if(manage.equals("delete")){
            journeyService.removeJourney(journeyId);
            managedJourney = new Journey();
        }
        clearSummary();
        return  "redirect:/managerJourney";
    }
}
