package com.epam.brest.taskproject.service;

import com.epam.brest.taskproject.domain.Automobile;
import com.epam.brest.taskproject.domain.AutomobileSummary;
import com.epam.brest.taskproject.domain.Journey;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by alesya on 22.11.14.
 */
public class JourneyDataFixture {

    public static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");

    public static Journey getNewJourney() throws ParseException {
        Long automobileId = 1L;
        String make= "audi80";
        String number="0013ih1";
        Double fuelRate= 6.2;
        Automobile automobile =  new Automobile( automobileId,make,number,fuelRate);

        Date date = SDF.parse("2014-01-01");
        String originDestination = "minsk-brest";
        Double distance =350.0;
        Journey journey = new Journey(null, automobile, date, originDestination, distance);
        return journey;
    }

    public static Journey getJourney() throws ParseException {
        Long automobileId = 1L;
        String make= "audi80";
        String number="0013ih1";
        Double fuelRate= 6.2;
        Automobile automobile =  new Automobile( automobileId,make,number,fuelRate);

        Long journeyId = 1L;
        Date date = SDF.parse("2014-01-01");
        String originDestination = "minsk-brest";
        Double distance =350.0;
        Journey journey = new Journey(journeyId, automobile, date, originDestination, distance);
        return journey;
    }

    public static List<Journey> getJourneys() throws ParseException {
        ArrayList<Journey> journeys = new ArrayList<Journey>();
        journeys.add(new Journey(1L,new Automobile(1L, "audi", "0013ih1", 6.2),
                SDF.parse("2014-10-01"),"kobrin-brest", 50.0D));
        journeys.add(new Journey(2L,new Automobile(1L, "audi", "0013ih1", 6.2),
                SDF.parse("2014-10-21"),"brest-warsaw", 200.0D));
        journeys.add(new Journey(3L,new Automobile(2L, "alfaromeo", "4707ek1", 5.1),
                SDF.parse("2014-10-21"),"brest-minsk", 300.0D));
        return journeys;
    }

    public static List<AutomobileSummary> getAutomobileSummaries() throws ParseException {
        ArrayList<AutomobileSummary> automobileSummaries = new ArrayList<AutomobileSummary>();
        automobileSummaries.add(new AutomobileSummary(new Automobile(1L, "audi", "0013ih1", 6.2), 250.0D));
        automobileSummaries.add(new AutomobileSummary(new Automobile(2L, "alfaromeo", "4707ek1", 5.1), 300.0D));
        automobileSummaries.add(new AutomobileSummary(new Automobile(3L, "ford", "2101it1", 8.1), 0.0D));
        return automobileSummaries;
    }
}
