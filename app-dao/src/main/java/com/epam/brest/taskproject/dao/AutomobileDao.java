package com.epam.brest.taskproject.dao;


import com.epam.brest.taskproject.domain.Automobile;

import java.util.List;

/**
 * Created by alesya on 18.11.14.
 */
public interface AutomobileDao {

    public Long addAutomobile(Automobile automobile);

    public void removeAutomobile(Long id);

    public void updateAutomobile(Automobile automobile);

    public Automobile getAutomobileById(Long id);

    public  List<Automobile> getAllAutomobiles();

}
