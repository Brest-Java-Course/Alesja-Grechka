package com.epam.brest.taskproject.dao;

import com.epam.brest.taskproject.dao.JourneyDao;
import com.epam.brest.taskproject.domain.Automobile;
import com.epam.brest.taskproject.domain.AutomobileSummary;
import com.epam.brest.taskproject.domain.Journey;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import static org.junit.Assert.*;

/**
 * Created by alesya on 19.11.14.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring-dao-test.xml"})
public class JourneyDaoImplTest {

    @Autowired
    private JourneyDao journeyDao;

    public static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");

    @Test
    public void getAllJourneysTest(){
        List<Journey> journeys = journeyDao.getAllJourneys();
        assertNotNull(journeys);
        assertFalse(journeys.isEmpty());
        assertNotNull(journeys.get(0).getAutomobile());
    }

    @Test
    public void getJourneyByIdTest(){
        Long id = 1L;
        Journey journey = journeyDao.getJourneyById(id);
        assertNotNull(journey);
        assertEquals(journey.getId(), id);
    }

    @Test
    public void addJourneyTest() throws ParseException {
        List<Journey> journeys = journeyDao.getAllJourneys();
        int size = journeys.size();
        Automobile automobile = new Automobile();
        automobile.setId(1L);
        Journey journey = new Journey(null,automobile,SDF.parse("2014-11-18"),"brest-brest fortress", 18.0);
        journeyDao.addJourney(journey);
        journeys = journeyDao.getAllJourneys();
        assertEquals(size, journeys.size()-1);
    }

    @Test
    public void removeJourneyTest(){
        List<Journey> journeys = journeyDao.getAllJourneys();
        int size = journeys.size();
        Long id = 1L;
        journeyDao.removeJourney(id);
        journeys = journeyDao.getAllJourneys();
        assertEquals(size, journeys.size()+1);
    }

    @Test
    public void updateJourneyTest()throws ParseException{
        Long journeyId =1L;
        Automobile modifiedAutomobile = new Automobile();
        modifiedAutomobile.setId(3L);
        Date modifiedDate = SDF.parse("2014-10-07");
        String modifiedOriginDestination = "brest-grodno";
        Double modifiedDistance = 266.0;

        Journey journey = journeyDao.getJourneyById(journeyId);
        journey.setAutomobile(modifiedAutomobile);
        journey.setDate(modifiedDate);
        journey.setOriginDestination(modifiedOriginDestination);
        journey.setDistance(modifiedDistance);

        journeyDao.updateJourney(journey);
        Journey modifiedJourney = journeyDao.getJourneyById(journeyId);

        assertNotNull(modifiedJourney);
        assertEquals(modifiedAutomobile.getId(), modifiedJourney.getAutomobile().getId());
        assertEquals(modifiedDate, modifiedJourney.getDate());
        assertEquals(modifiedOriginDestination, modifiedJourney.getOriginDestination());
        assertEquals(modifiedDistance, modifiedJourney.getDistance());
    }

    @Test
    public void getAllJourneysOfAutomobileTets(){
        Long id= 1L;
        List<Journey> automobileJourneys = journeyDao.getAllJourneysOfAutomobile(id);
        assertNotNull(automobileJourneys);
        assertFalse(automobileJourneys.isEmpty());
        for(int i=0; i<automobileJourneys.size(); i++){
            assertEquals(automobileJourneys.get(i).getAutomobile().getId(), id);
        }
    }

    @Test
    public void  getJourneysBetweenDatesTets() throws ParseException {

        Date date1 = SDF.parse("2014-01-01");
        Date date2 = SDF.parse("2014-10-01");

        List<Journey> journeys = journeyDao.getJourneys(date1,date2);

        assertNotNull(journeys);
        assertFalse(journeys.isEmpty());

        for(int i=0; i<journeys.size();i++){
            assertFalse(journeys.get(i).getDate().before(date1));
            assertFalse(journeys.get(i).getDate().after(date2));
        }
    }

    @Test
    public void getJourneysOfAutomobileBetweenDatesTets() throws ParseException {
        Date date1 = SDF.parse("2014-01-01");
        Date date2 = SDF.parse("2014-10-01");
        Long automobileId = 1L;

        List<Journey> journeys = journeyDao.getJourneysOfAutomobile(automobileId, date1, date2);

        assertNotNull(journeys);
        assertFalse(journeys.isEmpty());
        for(int i=0; i<journeys.size();i++){
            assertEquals(journeys.get(i).getAutomobile().getId(), automobileId);
            assertFalse(journeys.get(i).getDate().before(date1));
            assertFalse(journeys.get(i).getDate().after(date2));
        }
    }

    @Test
    public void getAutomobileSummariesTest(){
        List<AutomobileSummary> automobileSummaries = journeyDao.getAutomobileSummaries();
        assertNotNull(automobileSummaries);
    }

    @Test
    public void getAutomobileSummariesBetweenDatesTest() throws ParseException {
        Date date1 = SDF.parse("2014-01-01");
        Date date2 = SDF.parse("2014-10-01");
        List<AutomobileSummary> automobileSummaries = journeyDao.getAutomobileSummaries(date1,date2);
        assertNotNull(automobileSummaries);
    }
}
