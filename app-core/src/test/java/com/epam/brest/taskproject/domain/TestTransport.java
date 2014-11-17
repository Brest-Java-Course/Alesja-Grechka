package com.epam.brest.taskproject.domain;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Created by alesya on 17.11.14.
 */
public class TestTransport {

    Transport transport;

    @Test
    public void TestTransportFields(){

        Long id = 1L;
        String make= "audi80";
        String number="0013ih1";
        Double fuelRate= 6.2;

        transport = new Transport(id,make,number,fuelRate);
        assertEquals(id,transport.getId());
        assertEquals(make,transport.getMake());
        assertEquals(number,transport.getNumber());
        assertEquals(fuelRate,transport.getFuelRate());
    }

    @Test
    public void TetsTransportEquals(){

        transport = new Transport();

        Transport transport1 = new Transport(1L,"audi","0013ih1",6.2);
        Transport transport2 = new Transport(1L,"audi","0013ih1",6.2);
        Transport transport3 = new Transport(1L,"audi","0014ih1",6.2);


        assertEquals(transport, transport);
        assertEquals(transport1,transport2);

        assertFalse(transport.equals(transport1));
        assertFalse(transport3.equals(transport2));
        assertFalse(transport1.equals(transport3));
    }
}
