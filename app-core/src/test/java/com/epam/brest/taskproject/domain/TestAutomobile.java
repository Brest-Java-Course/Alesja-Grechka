package com.epam.brest.taskproject.domain;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Created by alesya on 17.11.14.
 */
public class TestAutomobile {

    Automobile automobile;

    @Test
    public void TestAutomobileFields(){

        Long id = 1L;
        String make= "audi80";
        String number="0013ih1";
        Double fuelRate= 6.2;

        automobile = new Automobile(id,make,number,fuelRate);
        assertEquals(id, automobile.getId());
        assertEquals(make, automobile.getMake());
        assertEquals(number, automobile.getNumber());
        assertEquals(fuelRate, automobile.getFuelRate());
    }

    @Test
    public void TetsAutomobileEquals(){

        automobile = new Automobile();

        Automobile atomobile1 = new Automobile(1L,"audi","0013ih1",6.2);
        Automobile atomobile2 = new Automobile(1L,"audi","0013ih1",6.2);
        Automobile atomobile3 = new Automobile(1L,"audi","0014ih1",6.2);

        assertEquals(automobile, automobile);
        assertEquals(atomobile1,atomobile2);

        assertFalse(automobile.equals(atomobile1));
        assertFalse(atomobile3.equals(atomobile2));
        assertFalse(atomobile1.equals(atomobile3));
    }
}
