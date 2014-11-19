package com.epam.brest.taskproject.dao;

import com.epam.brest.taskproject.dao.AutomobileDao;
import com.epam.brest.taskproject.domain.Automobile;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Test
    public void removeAutomobileTest(){
        List<Automobile> automobiles = automobileDao.getAllAutomobiles();
        int sizeBefore = automobiles.size();
        automobileDao.removeAutomobile(1L);
        automobiles = automobileDao.getAllAutomobiles();
        assertEquals(sizeBefore, automobiles.size()+1);
    }

    @Test
    public void updateAutomobileTest(){
        Long id =1L;
        String makeModified= "audi80";
        String numberModified ="0014ii1";
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
}
