package com.epam.brest.taskproject.domain;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by alesya on 22.11.14.
 */
public class AutomobileSummaryTest {


    @Test
    public void AutomobileSummaryConstructorTest(){
        Automobile automobile= new Automobile(1L, "ford","1111ih1", 8.0);
        Double sumDistance = 3000.0;
        AutomobileSummary automobileSummary = new AutomobileSummary(automobile,sumDistance);

        assertEquals(automobile, automobileSummary.getAutomobile());
        assertEquals(sumDistance, automobileSummary.getSumDistance());
    }

    @Test
    public void AutomobileSummaryDefaultConstructorTest(){
        AutomobileSummary automobileSummary = new AutomobileSummary();
        assertNull(automobileSummary.getAutomobile());
        assertNull(automobileSummary.getSumDistance());
        assertNotEquals(automobileSummary.getSumDistance(), Double.valueOf(0));
    }

    @Test
    public void getSumFuelTest(){

        Automobile automobile= new Automobile(1L, "ford","1111ih1", 8.0);
        Double sumDistance = 3000.0;
        AutomobileSummary automobileSummary = new AutomobileSummary(automobile,sumDistance);

        Double sumFuel = 3000.0*8.0;

        assertEquals(automobileSummary.getSumFuel(), sumFuel);

    }

    @Test
    public void getSumFuelWithNullValueTest(){

        AutomobileSummary automobileSummary = new AutomobileSummary();

        assertEquals(automobileSummary.getSumFuel(), Double.valueOf(0));
    }
}
