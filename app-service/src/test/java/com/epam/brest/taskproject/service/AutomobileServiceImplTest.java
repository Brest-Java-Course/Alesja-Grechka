package com.epam.brest.taskproject.service;

import com.epam.brest.taskproject.dao.AutomobileDao;
import com.epam.brest.taskproject.domain.Automobile;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by alesya on 20.11.14.
 */
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = { "classpath*:/spring-services-test.xml" })
//@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=false)
//@Transactional
public class AutomobileServiceImplTest {

    @Autowired
    AutomobileService automobileService;

    @Test
    public void  getAllAutomobilesTest(){
//        List<Automobile> automobiles = automobileService.getAllAutomobiles();
//        assertNotNull(automobiles);
//        assertFalse(automobiles.isEmpty());
    }
}
