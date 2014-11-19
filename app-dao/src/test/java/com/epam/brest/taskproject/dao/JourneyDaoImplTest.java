package com.epam.brest.taskproject.dao;

import com.epam.brest.taskproject.dao.JourneyDao;
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

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath:/spring-dao-test.xml"})
public class JourneyDaoImplTest {

    @Autowired
    private JourneyDao journeyDao;

    @Test
    public void tets1() {
       assertTrue(1==1);
    }


}
