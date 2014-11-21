package com.epam.brest.taskproject.service;

import com.epam.brest.taskproject.domain.Automobile;
import com.epam.brest.taskproject.dao.AutomobileDao;
import junit.framework.Assert;
import org.easymock.IExpectationSetters;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.IllegalFormatCodePointException;
import java.util.List;

import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertNotNull;
import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

/**
 * Created by alesya on 20.11.14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:/spring-services-mock-test.xml"})
public class AutomobileServiceImplMockTest {

    @Autowired
    AutomobileDao automobileDao;

    @Autowired
    AutomobileService automobileService;

    @After
    public void clean(){
        reset(automobileDao);
    }

    @Test
    public void addAutomobileTest(){
        Automobile automobile = AutomobileDataFixture.getNewAutomobile();
        expect(automobileDao.addAutomobile(automobile)).andReturn(Long.valueOf(1L));
        expect(automobileDao.getAutomobileByNumber(automobile.getNumber())).andReturn(null);
        replay(automobileDao);

        Long id = automobileService.addAutomobile(automobile);
        assertEquals(id,Long.valueOf(1L));
        verify(automobileDao);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addEAutomobileWithExistingNumberTest(){

        Automobile automobile = AutomobileDataFixture.getNewAutomobile();
        Automobile existingAutomobile = AutomobileDataFixture.getExistingAutomobile(1L);

        expect(automobileDao.addAutomobile(automobile)).andReturn(Long.valueOf(1L));
        expect(automobileDao.getAutomobileByNumber(automobile.getNumber())).andReturn(existingAutomobile);
        replay(automobileDao);

        automobileService.addAutomobile(automobile);
        verify(automobileDao);
    }

    @Test
    public void getAutomobileByIdTest(){
        Long id = 1L;
        Automobile automobile = AutomobileDataFixture.getExistingAutomobile(id);
        expect(automobileDao.getAutomobileById(id)).andReturn(automobile);
        replay(automobileDao);

        Automobile automobile1 = automobileService.getAutomobileById(id);
        verify(automobileDao);
        assertEquals(automobile, automobile1);
    }

    @Test
    public void getNotExistingAutomobileByIdTest() {
        Long id = 1L;
        expect(automobileDao.getAutomobileById(id)).andThrow(new EmptyResultDataAccessException(1));
        replay(automobileDao);

        Automobile automobile = automobileService.getAutomobileById(id);
        verify(automobileDao);
        assertNull(automobile);
    }

    @Test
    public void getAutomobileByNumberTest(){
        String number ="0011ih1";
        Automobile automobile = AutomobileDataFixture.getExistingAutomobile(number);

        expect(automobileDao.getAutomobileByNumber(number)).andReturn(automobile);
        replay(automobileDao);

        Automobile automobile1 = automobileService.getAutomobileByNumber(number);
        verify(automobileDao);
        assertNotNull(automobile1);
        assertEquals(automobile, automobile1);
    }

    @Test
    public void getNotExistingAutomobileByNumberTest(){
        String number ="0011ih1";
        expect(automobileDao.getAutomobileByNumber(number)).andThrow(new EmptyResultDataAccessException(1));

        replay(automobileDao);
        Automobile automobile = automobileService.getAutomobileByNumber(number);
        verify(automobileDao);
        assertNull(automobile);
    }

    @Test
    public void getAllAutomobilesTest(){
        List<Automobile> automobiles = AutomobileDataFixture.getAllAutomobiles();
        expect(automobileDao.getAllAutomobiles()).andReturn(AutomobileDataFixture.getAllAutomobiles());
        replay(automobileDao);

        List<Automobile> allAutomobiles= automobileService.getAllAutomobiles();
        verify(automobileDao);
        assertEquals(automobiles, allAutomobiles);
    }

    @Test
    public void getEmptyListAllAutomobilesTets(){
        expect(automobileDao.getAllAutomobiles()).andReturn(AutomobileDataFixture.getEmptyListAutomobiles());
        replay(automobileDao);

        List<Automobile> allAutomobiles = automobileService.getAllAutomobiles();
        verify(automobileDao);
        assertNotNull(allAutomobiles);
        assertTrue(allAutomobiles.isEmpty());
    }

    @Test
    public void removeAutomobileTest(){
        Long id = 1L;
        automobileDao.removeAutomobile(id);
        expectLastCall();
        replay(automobileDao);
        automobileService.removeAutomobile(1L);
        verify(automobileDao);
    }


    @Test
    public void updateAutomobileTest(){
        Automobile automobile= AutomobileDataFixture.getExistingAutomobile(1L);
        automobileDao.updateAutomobile(automobile);
        expectLastCall();
        replay(automobileDao);
        automobileService.updateAutomobile(automobile);
        verify(automobileDao);
    }

    @Test
    public void updateAutomobileWithNullMakeTest(){
        Automobile automobile = AutomobileDataFixture.getAutomobileWithNullMake();
        automobileDao.updateAutomobile(automobile);

        expectLastCall().andThrow(new IllegalArgumentException());
        replay(automobileDao);

        automobileService.updateAutomobile(automobile);
        verify(automobileDao);
    }

    @Test
    public void updateAutomobileWithNullNumberTest(){
        Automobile automobile = AutomobileDataFixture.getAutomobileWithNullNumber();
        automobileDao.updateAutomobile(automobile);

        expectLastCall().andThrow(new IllegalArgumentException());
        replay(automobileDao);

        automobileService.updateAutomobile(automobile);
        verify(automobileDao);
    }

    @Test
    public void updateAutomobileWithNullFuelRateTest(){
        Automobile automobile = AutomobileDataFixture.getAutomobileWithNullFuelRate();
        automobileDao.updateAutomobile(automobile);

        expectLastCall().andThrow(new IllegalArgumentException());
        replay(automobileDao);

        automobileService.updateAutomobile(automobile);
        verify(automobileDao);
    }
    @Test
    public void updateAutomobileWithNullIdTest(){
        Automobile automobile = AutomobileDataFixture.getNewAutomobile();
        automobileDao.updateAutomobile(automobile);

        expectLastCall().andThrow(new IllegalArgumentException());
        replay(automobileDao);

        automobileService.updateAutomobile(automobile);
        verify(automobileDao);
    }
}
