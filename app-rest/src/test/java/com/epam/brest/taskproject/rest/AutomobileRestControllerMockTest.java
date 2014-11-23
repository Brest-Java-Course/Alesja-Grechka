package com.epam.brest.taskproject.rest;

import com.epam.brest.taskproject.domain.Automobile;
import com.epam.brest.taskproject.service.AutomobileService;
import com.epam.brest.taskproject.rest.AutomobileRestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static org.easymock.EasyMock.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * Created by alesya on 23.11.14.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:/spring-rest-mock-test.xml"})
public class AutomobileRestControllerMockTest {

    private MockMvc mockMvc;

    @Resource
    private AutomobileRestController automobileRestController;

    @Autowired
    private AutomobileService automobileService;

    @Before
    public void setUp() {
        this.mockMvc = standaloneSetup(automobileRestController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter()).build();
    }

    @After
    public void tearDown() throws Exception {
        reset(automobileService);
    }

    @Test
    public void getAutomobileByIdTest() throws Exception {
        Long automobileId = 1L;
        expect(automobileService.getAutomobileById(automobileId))
                .andReturn(AutomobileDataFixture.getExistAutomobile(automobileId));
        replay(automobileService);
        this.mockMvc.perform(get("/automobiles/1").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("{\"id\":1,\"make\":\"make1\",\"number\":\"0011ih1\",\"fuelRate\":6.0}"));
        verify(automobileService);
    }

    @Test
    public void getNotFoundAutomobileByIdTest() throws Exception{
        expect(automobileService.getAutomobileById(4L)).andReturn(null);
        replay(automobileService);

        this.mockMvc.perform(get("/automobiles/4")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
        verify(automobileService);
    }

    @Test
    public void getAutomobileByNumberTest() throws  Exception{
        Automobile automobile = AutomobileDataFixture.getExistAutomobile(2L);
        String number = automobile.getNumber();

        expect(automobileService.getAutomobileByNumber(number))
                .andReturn(automobile);
        replay(automobileService);

        this.mockMvc.perform(get("/automobiles/number/"+number)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("{\"id\":2,\"make\":\"make2\",\"number\":\"0011ih2\",\"fuelRate\":6.0}"));

        verify(automobileService);
    }

    @Test
    public void getNotFoundAutomobileByNumberTest() throws Exception {
        expect(automobileService.getAutomobileByNumber(anyString()))
                .andReturn(null);
        replay(automobileService);

        this.mockMvc.perform(get("/automobiles/number/1111ii1")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
        verify(automobileService);
    }

    @Test
    public void getAllAutomobilesTest() throws Exception {
        expect(automobileService.getAllAutomobiles())
                .andReturn(AutomobileDataFixture.getAutomobiles());
        replay(automobileService);
        this.mockMvc.perform(get("/automobiles").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("[{\"id\":1,\"make\":\"make1\",\"number\":\"0011ih1\",\"fuelRate\":6.0}," +
                        "{\"id\":2,\"make\":\"make2\",\"number\":\"0011ih2\",\"fuelRate\":6.0}," +
                        "{\"id\":3,\"make\":\"make3\",\"number\":\"0011ih3\",\"fuelRate\":6.0}]"));
    }

    @Test
    public void addAutomobileTest() throws Exception {

        expect(automobileService.addAutomobile(anyObject(Automobile.class)))
                .andReturn(1L);
        replay(automobileService);

        ObjectMapper objectMapper = new ObjectMapper();
        String AutomobileJson = objectMapper.writeValueAsString(AutomobileDataFixture.getNewAutomobile());

        this.mockMvc.perform(
                post("/automobiles")
                        .content(AutomobileJson)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().string("1"));

        verify(automobileService);
    }

    @Test
    public void addExistAutomobileTest() throws Exception {

        expect(automobileService.addAutomobile(anyObject(Automobile.class)))
                .andThrow(new IllegalArgumentException());
        replay(automobileService);

        ObjectMapper objectMapper = new ObjectMapper();
        String AutomobileJson = objectMapper.writeValueAsString(AutomobileDataFixture.getNewAutomobile());

        this.mockMvc.perform(
                post("/automobiles")
                        .content(AutomobileJson)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(""));

        verify(automobileService);
    }

    @Test
    public void removeAutomobileTest() throws Exception {
        automobileService.removeAutomobile(1L);
        expectLastCall();
        replay(automobileService);
        ResultActions result = this.mockMvc.perform(
                delete("/automobiles/1"))
                .andDo(print());
        result.andExpect(status().isOk());
        verify(automobileService);
    }

    @Test
    public void updateAutomobileTest() throws Exception{
        automobileService.updateAutomobile(anyObject(Automobile.class));
        expectLastCall();
        replay(automobileService);

        ObjectMapper objectMapper = new ObjectMapper();
        Automobile automobile = AutomobileDataFixture.getExistAutomobile(1L);
        automobile.setMake("modified");
        String automobileJson = objectMapper.writeValueAsString(automobile);

        ResultActions result = this.mockMvc.perform(put("/automobiles")
                .content(automobileJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(print());
        result.andExpect(status().isOk());
        verify(automobileService);
    }

}
