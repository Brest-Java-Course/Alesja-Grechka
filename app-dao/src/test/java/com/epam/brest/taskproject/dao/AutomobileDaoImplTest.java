package com.epam.brest.taskproject.dao;

import com.epam.brest.taskproject.dao.AutomobileDao;
import com.epam.brest.taskproject.domain.Automobile;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.util.List;


import static org.junit.Assert.*;

/**
 * Created by alesya on 19.11.14.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring-dao-test.xml"})
public class AutomobileDaoImplTest {

    @Autowired
    private AutomobileDao automobileDao;

    @Test
    public void addAutomobileTest(){
        List<Automobile> automobiles = automobileDao.getAllAutomobiles();
        int sizeBefore =  automobiles.size();
        Automobile automobile = new Automobile(null,"fiat", "8789ek7", 9.0);
        automobileDao.addAutomobile(automobile);
        automobiles = automobileDao.getAllAutomobiles();
        assertEquals(sizeBefore, automobiles.size()-1 );
    }

    @Test(expected = IllegalArgumentException.class)
    public void addAutomobileWithNullNumberTest(){
        Automobile automobile = new Automobile(null,"fiat", null, 9.0);
        automobileDao.addAutomobile(automobile);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addAutomobileWithNullMakeTest(){
        Automobile automobile = new Automobile(null,null, "8789ek7", 9.0);
        automobileDao.addAutomobile(automobile);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addAutomobileWithNotNullIdTest(){
        Automobile automobile = new Automobile(13L,"fiat", "8789ek7", 9.0);
        automobileDao.addAutomobile(automobile);
    }

    @Test
    public void getAllAutomobilesTest(){
        List<Automobile> automobiles = automobileDao.getAllAutomobiles();
        assertNotNull(automobiles);
        assertFalse(automobiles.isEmpty());
    }

    @Test
    public void getAutomobileByIdTest(){
        Long id =1L;
        Automobile automobile = automobileDao.getAutomobileById(id);
        assertNotNull(automobile);
        assertEquals(id, automobile.getId() );
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void getAutomobileByIdIfNotExistTest(){
        Long id =10L;
        Automobile automobile = automobileDao.getAutomobileById(id);
    }

    @Test
    public void getAutomobileByNumberTest(){
        String number ="2101-it7";
        Automobile automobile = automobileDao.getAutomobileByNumber(number);
        assertNotNull(automobile);
        assertEquals(number, automobile.getNumber() );
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void getAutomobileByNumberIfNotExistTest(){
        String number ="0000-ii1";
        Automobile automobile = automobileDao.getAutomobileByNumber(number);
    }

    @Test
    public void removeAutomobileTest(){
        List<Automobile> automobiles = automobileDao.getAllAutomobiles();
        int sizeBefore = automobiles.size();
        automobileDao.removeAutomobile(2L);
        automobiles = automobileDao.getAllAutomobiles();

        assertEquals(sizeBefore, automobiles.size()+1);
    }

    @Test
    public void removeAutomobileIfNotExistTest(){
        List<Automobile> automobiles = automobileDao.getAllAutomobiles();
        int sizeBefore = automobiles.size();
        automobileDao.removeAutomobile(12L);
        automobiles = automobileDao.getAllAutomobiles();
        assertEquals(sizeBefore, automobiles.size());
    }

    @Test
    public void updateAutomobileTest(){
        Long id =1L;
        String makeModified= "audi80";
        String numberModified ="0014-ii1";
        Double fuelRateModified=5.8;

        Automobile automobile = automobileDao.getAutomobileById(id);

        automobile.setFuelRate(fuelRateModified);
        automobile.setMake(makeModified);
        automobile.setNumber(numberModified);

        automobileDao.updateAutomobile(automobile);
        Automobile automobileModified = automobileDao.getAutomobileById(id);

        assertNotNull(automobileModified);
        assertEquals(makeModified, automobileModified.getMake());
        assertEquals(numberModified, automobileModified.getNumber());
        assertEquals(numberModified, automobileModified.getNumber());
        assertEquals(fuelRateModified, automobileModified.getFuelRate());
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateAutomobileWithEmptyNumberTest(){
        Long id =1L;
        String makeModified= "audi80";
        String numberModified = null;
        Double fuelRateModified=5.8;

        Automobile automobile = automobileDao.getAutomobileById(id);

        automobile.setFuelRate(fuelRateModified);
        automobile.setMake(makeModified);
        automobile.setNumber(numberModified);

        automobileDao.updateAutomobile(automobile);
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateAutomobileWithEmptyMakeTest(){
        Long id =1L;
        String makeModified= null;
        String numberModified = "0014-ii1";
        Double fuelRateModified=5.8;

        Automobile automobile = automobileDao.getAutomobileById(id);

        automobile.setFuelRate(fuelRateModified);
        automobile.setMake(makeModified);
        automobile.setNumber(numberModified);

        automobileDao.updateAutomobile(automobile);
    }
}
