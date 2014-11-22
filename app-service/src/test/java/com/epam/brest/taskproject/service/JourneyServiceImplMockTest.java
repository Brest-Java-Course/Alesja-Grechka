package com.epam.brest.taskproject.service;

import com.epam.brest.taskproject.dao.JourneyDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.Assert.*;

/**
 * Created by alesya on 22.11.14.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:/spring-services-mock-test.xml"})
public class JourneyServiceImplMockTest {

    @Autowired
    JourneyDao journeyDao;

    @Autowired
    JourneyService journeyService;

    @Test
    public void test1(){
        assertTrue(true);
    }

    @Test
    public void addJourneyTest(){
        //TODO:
    }
    @Test
    public void addExistingJourneyTest(){
        //TODO:
    }

    @Test
    public void removeJourneyTest(){
        //TODO:
    }

    @Test
    public void updateJourneyTest(){
        //TODO:
    }

    @Test
    public void updateNullJourneyTest(){
        //TODO:
    }

    @Test
    public void updateJourneyWithNullIdTest(){
        //TODO:
    }

    @Test
    public void updateJourneyWithNullAutomobileIdTest(){
        //TODO:
    }

    @Test
    public void updateJourneyWithNullDateTest(){
        //TODO:
    }

    @Test
    public void updateJourneyWithNullDistanceTest(){
        //TODO:
    }

    @Test
    public void updateJourneyWithNullOriginDestinationTest(){
        //TODO:
    }

    @Test
    public void getJourneyByIdTest(){
        //TODO:
    }

    @Test
    public void getJourneyByIdIfNotExistTest(){
        //TODO:
    }

    @Test
    public void getAllJourneysTest(){
        //TODO:
    }

    @Test
    public void getJourneysTest(){
        //TODO:
    }

    @Test
    public void getAllJourneysOfAutomobileTest(){
        //TODO:
    }

    @Test
    public void getJourneysOfAutomobileTest(){
        //TODO:
    }

    @Test
    public void getAutomobileSummariesTest(){
        //TODO:
    }

}
