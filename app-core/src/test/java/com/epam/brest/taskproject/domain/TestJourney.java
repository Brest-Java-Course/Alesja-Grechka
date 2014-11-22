package com.epam.brest.taskproject.domain;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by alesya on 22.11.14.
 */
public class TestJourney {


    public static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");

    @Test
    public void journeyConstructorTest()throws ParseException {
        Long automobileId = 1L;
        String make= "audi80";
        String number="0013ih1";
        Double fuelRate= 6.2;
        Automobile automobile =  new Automobile( automobileId,make,number,fuelRate);
        Long journeyId = 2L;
        Date date = SDF.parse("2014-01-01");
        String originDestination = "minsk-brest";
        Double distance =350.0;

        Journey journey = new Journey(journeyId, automobile, date, originDestination, distance);

        assertEquals(journeyId, journey.getId());
        assertEquals(automobile, journey.getAutomobile());
        assertEquals(date, journey.getDate());
        assertEquals(originDestination, journey.getOriginDestination());
        assertEquals(distance, journey.getDistance());
    }

    @Test
    public void journeyCompareTest()throws ParseException{

        Long automobileId = 1L;
        String make= "audi80";
        String number="0013ih1";
        Double fuelRate= 6.2;
        Automobile automobile =  new Automobile( automobileId,make,number,fuelRate);

        Long journeyId1 = 2L;
        Date date1 = SDF.parse("2014-01-01");
        String originDestination1 = "minsk-brest";
        Double distance1 =350.0;

        Long journeyId2 = 3L;
        Date date2 = SDF.parse("2014-11-14");
        String originDestination2 = "minsk-kobrin";
        Double distance2 =300.0;

        Journey journey = new Journey();
        Journey journey1 = new Journey(journeyId1,automobile,date1, originDestination1, distance1);
        Journey journey2 = new Journey(journeyId2,automobile,date2, originDestination2, distance2);

        assertEquals(journey, journey);
        assertEquals(journey1, journey1);
        assertEquals(journey2, journey2);
        assertNotEquals(journey, journey1);
        assertNotEquals(journey, journey2);
        assertNotEquals(journey1, journey2);
    }
}
