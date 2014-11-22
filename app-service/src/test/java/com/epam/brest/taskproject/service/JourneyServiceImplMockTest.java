package com.epam.brest.taskproject.service;

import com.epam.brest.taskproject.dao.JourneyDao;
import com.epam.brest.taskproject.domain.Automobile;
import com.epam.brest.taskproject.domain.AutomobileSummary;
import com.epam.brest.taskproject.domain.Journey;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.VoidType;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.easymock.EasyMock.*;

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

    @After
    public void clean(){
        reset(journeyDao);
    }

    public static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");

    @Test
    public void addJourneyTest() throws ParseException {
        Journey journey = JourneyDataFixture.getNewJourney();
        Long journeyIdExpected = 1L;
        expect(journeyDao.addJourney(journey)).andReturn(journeyIdExpected);
        replay(journeyDao);

        Long journeyId = journeyService.addJourney(journey);
        verify(journeyDao);
        assertEquals(journeyIdExpected, journeyId);

    }

    @Test(expected = IllegalArgumentException.class)
    public void addExistingJourneyTest()throws ParseException{
        Journey journey = JourneyDataFixture.getJourney(1L);
        journeyService.addJourney(journey);
    }

    @Test
    public void removeJourneyTest(){
        Long journeyId=1L;

        journeyDao.removeJourney(journeyId);
        expectLastCall();


        replay(journeyDao);
        journeyService.removeJourney(journeyId);
        verify(journeyDao);
    }

    @Test
    public void updateJourneyTest() throws ParseException{
        Journey journey = JourneyDataFixture.getJourney(1L);
        journeyDao.updateJourney(journey);
        expectLastCall();
        replay(journeyDao);
        journeyService.updateJourney(journey);
        verify(journeyDao);
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateNullJourneyTest(){
        Journey journey = new Journey();
        journeyService.updateJourney(journey);
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateJourneyWithNullIdTest() throws ParseException{
        Journey journey= JourneyDataFixture.getJourney(null);
        journeyService.updateJourney(journey);

    }

    @Test(expected = IllegalArgumentException.class)
    public void updateJourneyWithNullAutomobileIdTest()throws ParseException{
        Journey journey = JourneyDataFixture.getJourney(1L);
        journey.setAutomobile(new Automobile(null, "make1", "0000ij1", 7.8));
        journeyService.updateJourney(journey);
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateJourneyWithNullDateTest() throws ParseException{
        Journey journey = JourneyDataFixture.getJourney(1L);
        journey.setDate(null);
        journeyService.updateJourney(journey);
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateJourneyWithNullDistanceTest()throws ParseException{
        Journey journey = JourneyDataFixture.getJourney(1L);
        journey.setDistance(null);
        journeyService.updateJourney(journey);
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateJourneyWithNullOriginDestinationTest()throws ParseException{
        Journey journey = JourneyDataFixture.getJourney(1L);
        journey.setOriginDestination(null);
        journeyService.updateJourney(journey);
    }

    @Test
    public void getJourneyByIdTest() throws ParseException{
        Long journeyId = 1L;
        Journey journeyExpected = JourneyDataFixture.getJourney(1L);
        expect(journeyDao.getJourneyById(1L)).andReturn(journeyExpected);
        replay(journeyDao);

        Journey journey = journeyService.getJourneyById(journeyId);
        verify(journeyDao);
        assertEquals(journeyExpected, journey);
    }

    @Test
    public void getJourneyByIdIfNotExistTest(){
        expect(journeyDao.getJourneyById(1L)).andThrow(new EmptyResultDataAccessException(1));
        replay(journeyDao);
        Journey journey = journeyService.getJourneyById(1L);
        verify(journeyDao);
        assertNull(journey);
    }

    @Test
    public void getAllJourneysTest() throws ParseException{
        List<Journey> journeysExpected = JourneyDataFixture.getJourneys();
        expect(journeyDao.getAllJourneys()).andReturn(journeysExpected);
        replay(journeyDao);
        List<Journey> journeys = journeyService.getAllJourneys();
        verify(journeyDao);
        assertEquals(journeysExpected, journeys);
    }

    @Test
    public void getJourneysTest() throws ParseException{
        Date date1 = SDF.parse("2013-01-01");
        Date date2 = SDF.parse("2015-01-01");

        List<Journey> journeysExpected = JourneyDataFixture.getJourneys();
        expect(journeyDao.getJourneys(date1, date2)).andReturn(journeysExpected);
        replay(journeyDao);
        List<Journey> journeys = journeyService.getJourneys(date1, date2);
        verify(journeyDao);
        assertEquals(journeysExpected, journeys);
    }

    @Test
    public void getAllJourneysOfAutomobileTest() throws  ParseException{
        Long automobileId= 1L;
        List<Journey> journeysExpected = JourneyDataFixture.getJourneys();
        expect(journeyDao.getAllJourneysOfAutomobile(automobileId)).andReturn(journeysExpected);
        replay(journeyDao);
        List<Journey> journeys = journeyService.getAllJourneysOfAutomobile(automobileId);
        verify(journeyDao);
        assertEquals(journeysExpected, journeys);
    }

    @Test
    public void getJourneysOfAutomobileTest() throws  ParseException{
        Date date1 = SDF.parse("2013-01-01");
        Date date2 = SDF.parse("2015-01-01");
        Long automobileId= 1L;
        List<Journey> journeysExpected = JourneyDataFixture.getJourneys();

        expect(journeyDao.getJourneysOfAutomobile(automobileId, date1,date2)).andReturn(journeysExpected);
        replay(journeyDao);
        List<Journey> journeys = journeyService.getJourneysOfAutomobile(automobileId, date1,date2);
        verify(journeyDao);
        assertEquals(journeysExpected, journeys);
    }

    @Test
    public void getAutomobileSummariesTest() throws  ParseException{
        List<AutomobileSummary> automobileSummariesExpected =
                JourneyDataFixture.getAutomobileSummaries();
        expect(journeyDao.getAutomobileSummaries()).andReturn(automobileSummariesExpected);
        replay(journeyDao);

        List<AutomobileSummary> automobileSummaries = journeyService.getAutomobileSummaries();
        verify(journeyDao);
        assertEquals(automobileSummariesExpected, automobileSummaries);
    }

    @Test
    public void getAutomobileSummariesBetweenDatesTest() throws  ParseException{
        Date date1 = SDF.parse("2013-01-01");
        Date date2 = SDF.parse("2015-01-01");
        List<AutomobileSummary> automobileSummariesExpected =
                JourneyDataFixture.getAutomobileSummaries();
        expect(journeyDao.getAutomobileSummaries(date1, date2)).andReturn(automobileSummariesExpected);
        replay(journeyDao);

        List<AutomobileSummary> automobileSummaries = journeyService.getAutomobileSummaries(date1, date2);
        verify(journeyDao);
        assertEquals(automobileSummariesExpected, automobileSummaries);
    }

}
