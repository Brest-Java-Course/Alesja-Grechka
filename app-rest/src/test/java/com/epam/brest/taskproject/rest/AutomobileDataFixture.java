package com.epam.brest.taskproject.rest;

import com.epam.brest.taskproject.domain.Automobile;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alesya on 23.11.14.
 */
public class AutomobileDataFixture {
    public static Automobile getNewAutomobile(){
        Automobile automobile = new Automobile();
        automobile.setMake("make1");
        automobile.setNumber("0000ii1");
        automobile.setFuelRate(6.0);
        return automobile;
    }

    public static Automobile getNewAutomobile(Long automobileId){
        Automobile automobile = new Automobile();
        automobile.setId(automobileId);
        automobile.setMake("make1");
        automobile.setNumber("0000ii1");
        automobile.setFuelRate(6.0);
        return automobile;
    }

    public static Automobile getExistAutomobile(Long automobileId){
        Automobile automobile = new Automobile();
        automobile.setId(automobileId);
        automobile.setMake("make"+automobileId);
        automobile.setNumber("0011ih" + automobileId);
        automobile.setFuelRate(6.0);
        return automobile;
    }

    public static List<Automobile> getAutomobiles(){
        List automobiles = new ArrayList(3);
        automobiles.add(getExistAutomobile(1L));
        automobiles.add(getExistAutomobile(2L));
        automobiles.add(getExistAutomobile(3L));
        return automobiles;
    }
}
