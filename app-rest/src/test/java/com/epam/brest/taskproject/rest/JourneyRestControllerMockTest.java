package com.epam.brest.taskproject.rest;

import com.epam.brest.taskproject.domain.Journey;
import com.epam.brest.taskproject.service.JourneyService;
import com.epam.brest.taskproject.rest.JourneyRestController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hsqldb.rights.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static junit.framework.Assert.*;
import static org.easymock.EasyMock.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * Created by alesya on 24.11.14.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:/spring-rest-mock-test.xml"})
public class JourneyRestControllerMockTest {

    public static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");

    private MockMvc mockMvc;

    @Resource
    private JourneyRestController journeyRestController;

    @Autowired
    private JourneyService journeyService;

    @Before
    public void setUp() {
        this.mockMvc = standaloneSetup(journeyRestController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter()).build();

    }

    @After
    public void tearDown() throws Exception {
        reset(journeyService);
    }

    @Test
    public void addJourneyTest()throws Exception{
        expect(journeyService.addJourney(anyObject(Journey.class)))
                .andReturn(Long.valueOf(1L));
        replay(journeyService);

        ObjectMapper objectMapper = new ObjectMapper();
        String journeyJSON = objectMapper.writeValueAsString(JourneyDataFixture.getNewJourney());

        this.mockMvc.perform(post("/journeys")
                        .content(journeyJSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().string("1"));
        verify(journeyService);
    }

    @Test
    public void addJourneyIncorrectTest() throws Exception{
        Journey journey = JourneyDataFixture.getJourneyWithNullDate();
        expect(journeyService.addJourney(journey)).andThrow(new IllegalArgumentException());
        replay(journeyService);

        ObjectMapper objectMapper = new ObjectMapper();
        String journeyJSON = objectMapper.writeValueAsString(journey);

        this.mockMvc.perform(post("/journeys")
                .content(journeyJSON)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
        verify(journeyService);

    }

    @Test
    public void  removeJourneyTest() throws Exception{
        journeyService.removeJourney(1L);
        expectLastCall();
        replay(journeyService);

        ResultActions result =  this.mockMvc.perform(delete("/journeys/1"))
                .andDo(print());
        result.andExpect(status().isOk());
        verify(journeyService);
    }

    @Test
    public void updateJourneyTest() throws Exception{
        journeyService.updateJourney(anyObject(Journey.class));
        expectLastCall();
        replay(journeyService);

        Journey journey = JourneyDataFixture.getJourney(1L);
        ObjectMapper objectMapper = new ObjectMapper();
        String journeyJson = objectMapper.writeValueAsString(journey);

        ResultActions result = this.mockMvc.perform(put("/journeys")
                        .content(journeyJson)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print());
        result.andExpect(status().isOk());
        verify(journeyService);
    }

    //TODO: updateJourneyWithNullFieldTest()

    @Test
    public void getJourneyByIdTest()throws Exception{
        Long journeyId = 1L;
        Journey journey = JourneyDataFixture.getJourney(journeyId);
        expect(journeyService.getJourneyById(journeyId)).andReturn(journey);
        replay(journeyService);

        this.mockMvc.perform(get("/journeys/1").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("{\"id\":1,\"" +
                        "automobile\":{\"id\":1,\"make\":\"audi80\",\"number\":\"0013ih1\",\"fuelRate\":6.2},\"" +
                        "date\":1388523600000,\"originDestination\":\"minsk-brest\",\"distance\":350.0}"));
        verify(journeyService);
    }

    @Test
    public void getNotExistJourneyByIdTest()throws Exception{

        expect(journeyService.getJourneyById(1L)).andReturn(null);
        replay(journeyService);

        this.mockMvc.perform(get("/journeys/1").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
        verify(journeyService);
    }

    @Test
    public void getAllJourneysTest() throws Exception{
        List journeys = JourneyDataFixture.getJourneys();
        expect(journeyService.getAllJourneys()).andReturn(journeys);
        replay(journeyService);

        this.mockMvc.perform(get("/journeys").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("[{\"id\":1,\"automobile\":{\"id\":1,\"make\":\"audi\",\"number\":\"0013ih1\",\"fuelRate\":6.2}," +
                        "\"date\":1412110800000,\"originDestination\":\"kobrin-brest\",\"distance\":50.0}," +
                        "{\"id\":2,\"automobile\":{\"id\":1,\"make\":\"audi\",\"number\":\"0013ih1\",\"fuelRate\":6.2}," +
                        "\"date\":1413838800000,\"originDestination\":\"brest-warsaw\",\"distance\":200.0}," +
                        "{\"id\":3,\"automobile\":{\"id\":2,\"make\":\"alfaromeo\",\"number\":\"4707ek1\",\"fuelRate\":5.1}," +
                        "\"date\":1413838800000,\"originDestination\":\"brest-minsk\",\"distance\":300.0}]"));

        verify(journeyService);
    }

    @Test
    public void getJourneysBetweenDatesTest() throws Exception{
        List journeys = JourneyDataFixture.getJourneys();
        Date date1 = SDF.parse("2014-10-10");
        Date date2 = SDF.parse("2014-12-01");
        expect(journeyService.getJourneys(date1, date2)).andReturn(journeys);
        replay(journeyService);

        this.mockMvc.perform(get("/journeys/date1/2014-10-10/date2/2014-12-01").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("[{\"id\":1,\"automobile\":{\"id\":1,\"make\":\"audi\",\"number\":\"0013ih1\",\"fuelRate\":6.2}," +
                        "\"date\":1412110800000,\"originDestination\":\"kobrin-brest\",\"distance\":50.0}," +
                        "{\"id\":2,\"automobile\":{\"id\":1,\"make\":\"audi\",\"number\":\"0013ih1\",\"fuelRate\":6.2}," +
                        "\"date\":1413838800000,\"originDestination\":\"brest-warsaw\",\"distance\":200.0}," +
                        "{\"id\":3,\"automobile\":{\"id\":2,\"make\":\"alfaromeo\",\"number\":\"4707ek1\",\"fuelRate\":5.1}," +
                        "\"date\":1413838800000,\"originDestination\":\"brest-minsk\",\"distance\":300.0}]"));
        verify(journeyService);
    }

    @Test
    public void getAllJourneysOfAutomobileTest() throws Exception{
        List journeys = JourneyDataFixture.getJourneys();

        expect(journeyService.getAllJourneysOfAutomobile(1L)).andReturn(journeys);
        replay(journeyService);

        this.mockMvc.perform(get("/journeys/automobileId/1").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("[{\"id\":1,\"automobile\":{\"id\":1,\"make\":\"audi\",\"number\":\"0013ih1\",\"fuelRate\":6.2}," +
                        "\"date\":1412110800000,\"originDestination\":\"kobrin-brest\",\"distance\":50.0}," +
                        "{\"id\":2,\"automobile\":{\"id\":1,\"make\":\"audi\",\"number\":\"0013ih1\",\"fuelRate\":6.2}," +
                        "\"date\":1413838800000,\"originDestination\":\"brest-warsaw\",\"distance\":200.0}," +
                        "{\"id\":3,\"automobile\":{\"id\":2,\"make\":\"alfaromeo\",\"number\":\"4707ek1\",\"fuelRate\":5.1}," +
                        "\"date\":1413838800000,\"originDestination\":\"brest-minsk\",\"distance\":300.0}]"));
        verify(journeyService);
    }

    @Test
    public void getJourneysOfAutomobileBetweenDatesTest() throws Exception{
        List journeys = JourneyDataFixture.getJourneys();
        Date date1 = SDF.parse("2014-10-10");
        Date date2 = SDF.parse("2014-12-01");

        expect(journeyService.getJourneysOfAutomobile(1L,date1, date2)).andReturn(journeys);
        replay(journeyService);

        this.mockMvc.perform(get("/journeys/automobileId/1/date1/2014-10-10/date2/2014-12-01").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("[{\"id\":1,\"automobile\":{\"id\":1,\"make\":\"audi\",\"number\":\"0013ih1\",\"fuelRate\":6.2}," +
                        "\"date\":1412110800000,\"originDestination\":\"kobrin-brest\",\"distance\":50.0}," +
                        "{\"id\":2,\"automobile\":{\"id\":1,\"make\":\"audi\",\"number\":\"0013ih1\",\"fuelRate\":6.2}," +
                        "\"date\":1413838800000,\"originDestination\":\"brest-warsaw\",\"distance\":200.0}," +
                        "{\"id\":3,\"automobile\":{\"id\":2,\"make\":\"alfaromeo\",\"number\":\"4707ek1\",\"fuelRate\":5.1}," +
                        "\"date\":1413838800000,\"originDestination\":\"brest-minsk\",\"distance\":300.0}]"));
        verify(journeyService);
    }

    @Test
    public void getAutomobileSummariesTest()throws Exception{
        List summary = JourneyDataFixture.getAutomobileSummaries();
        expect(journeyService.getAutomobileSummaries()).andReturn(summary);
        replay(journeyService);
        this.mockMvc.perform(get("/journeys/summary").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("[{\"automobile\":{\"id\":1,\"make\":\"audi\"," +
                        "\"number\":\"0013ih1\",\"fuelRate\":6.2},\"sumDistance\":250.0,\"sumFuel\":1550.0}," +
                        "{\"automobile\":{\"id\":2,\"make\":\"alfaromeo\"," +
                        "\"number\":\"4707ek1\",\"fuelRate\":5.1},\"sumDistance\":300.0,\"sumFuel\":1530.0}," +
                        "{\"automobile\":{\"id\":3,\"make\":\"ford\"," +
                        "\"number\":\"2101it1\",\"fuelRate\":8.1},\"sumDistance\":0.0,\"sumFuel\":0.0}]"));
        verify(journeyService);
    }

    @Test
    public void getAutomobileSummariesBetweenDatesTest()throws Exception{
        Date date1 = SDF.parse("2014-10-10");
        Date date2 = SDF.parse("2014-12-01");
        List summary = JourneyDataFixture.getAutomobileSummaries();
        expect(journeyService.getAutomobileSummaries(date1, date2)).andReturn(summary);
        replay(journeyService);
        this.mockMvc.perform(get("/journeys/summary/date1/2014-10-10/date2/2014-12-01").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("[{\"automobile\":{\"id\":1,\"make\":\"audi\"," +
                        "\"number\":\"0013ih1\",\"fuelRate\":6.2},\"sumDistance\":250.0,\"sumFuel\":1550.0}," +
                        "{\"automobile\":{\"id\":2,\"make\":\"alfaromeo\"," +
                        "\"number\":\"4707ek1\",\"fuelRate\":5.1},\"sumDistance\":300.0,\"sumFuel\":1530.0}," +
                        "{\"automobile\":{\"id\":3,\"make\":\"ford\"," +
                        "\"number\":\"2101it1\",\"fuelRate\":8.1},\"sumDistance\":0.0,\"sumFuel\":0.0}]"));
        verify(journeyService);
    }

}
