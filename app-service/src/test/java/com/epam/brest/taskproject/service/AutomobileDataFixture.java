package com.epam.brest.taskproject.service;

import com.epam.brest.taskproject.domain.Automobile;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alesya on 21.11.14.
 */
public class AutomobileDataFixture {

    public static Automobile getNewAutomobile(){
        Automobile automobile = new Automobile();
        automobile.setMake("fiat");
        automobile.setNumber("0101ii1");
        automobile.setFuelRate(8.0);
        return automobile;
    }

    public static Automobile getExistingAutomobile(Long id){
        Automobile automobile = new Automobile();
        automobile.setId(id);
        automobile.setMake("fiat");
        automobile.setNumber("0101ii1");
        automobile.setFuelRate(8.0);
        return automobile;
    }

    public static Automobile getExistingAutomobile(String number){
        Automobile automobile = new Automobile();
        automobile.setId(1L);
        automobile.setMake("fiat");
        automobile.setNumber(number);
        automobile.setFuelRate(8.0);
        return automobile;
    }

    public static Automobile getAutomobileWithNullMake(){
        Automobile automobile = new Automobile();
        automobile.setId(1L);
       // automobile.setMake(null);
        automobile.setNumber("0101ii1");
        automobile.setFuelRate(8.0);
        return automobile;
    }

    public static Automobile getAutomobileWithNullNumber(){
        Automobile automobile = new Automobile();
        automobile.setId(1L);
        automobile.setMake("fiat");
        automobile.setNumber(null);
        automobile.setFuelRate(8.0);
        return automobile;
    }

    public static Automobile getAutomobileWithNullFuelRate(){
        Automobile automobile = new Automobile();
        automobile.setId(1L);
        automobile.setMake("fiat");
        automobile.setNumber("0101ii1");
        automobile.setFuelRate(null);
        return automobile;
    }

    public static List<Automobile> getAllAutomobiles(){
        ArrayList<Automobile> automobilies = new ArrayList<Automobile>();
        automobilies.add(new Automobile(1L,"audi","0013ih1", 6.2));
        automobilies.add(new Automobile(2L,"alfaromeo","4707ek1", 5.1));
        automobilies.add(new Automobile(3L,"ford","2101it1", 8.1));
        return automobilies;
    }

    public static List<Automobile> getEmptyListAutomobiles(){
        ArrayList<Automobile> automobilies = new ArrayList<Automobile>();
        return automobilies;
    }
}
