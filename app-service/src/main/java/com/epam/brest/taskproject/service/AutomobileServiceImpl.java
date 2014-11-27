package com.epam.brest.taskproject.service;

import com.epam.brest.taskproject.dao.AutomobileDao;
import com.epam.brest.taskproject.domain.Automobile;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import java.util.List;

/**
 * Created by alesya on 20.11.14.
 */

@Service
public class AutomobileServiceImpl implements AutomobileService {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private AutomobileDao automobileDao;

    @Override
    @Transactional
    public Long addAutomobile(Automobile automobile) {
        LOGGER.debug("addAutomobile{}", automobile);

        Assert.notNull(automobile);
        Assert.isNull(automobile.getId());
        Assert.notNull(automobile.getMake());
        Assert.notNull(automobile.getNumber());
        Assert.notNull(automobile.getFuelRate());

        Automobile existingAutomobile = getAutomobileByNumber(automobile.getNumber());
        if(existingAutomobile != null){
            throw new IllegalArgumentException("automobile with number ["+ automobile.getNumber()
                    +"] is present in db");
        }
        return automobileDao.addAutomobile(automobile);
    }

    @Override
    public void removeAutomobile(Long id) {
        LOGGER.debug("removeAutomobile({})",id);
        automobileDao.removeAutomobile(id);
    }

    @Override
    public void updateAutomobile(Automobile automobileModified) {
        LOGGER.debug("updateAutomobile  modified:({})",automobileModified);

        Automobile automobile = getAutomobileById(automobileModified.getId());
        LOGGER.debug("updateAutomobile  previous:({})",automobile);

        if( ! automobile.getNumber().equals(automobileModified.getNumber()) ){
            Automobile automobileWithTheSameNumber =
                    getAutomobileByNumber( automobileModified.getNumber() );
            if( automobileWithTheSameNumber != null){
                LOGGER.debug("automobileWithTheSameNumber:{}", automobileWithTheSameNumber);
                throw new IllegalArgumentException("modified number:["+automobileModified.getNumber()+
                        "] is present in db");
            }
        }

        try {
            automobileDao.updateAutomobile(automobileModified);
        } catch (IllegalArgumentException e){
            LOGGER.error("updateAutomobile({}): {}",automobileModified, e.toString());
        }
    }

    @Override
    public Automobile getAutomobileById(Long id) {
        LOGGER.debug("getAutomobileById({})",id);

        Automobile automobile =null;
        try{
            automobile = automobileDao.getAutomobileById(id);
        } catch (EmptyResultDataAccessException e){
            LOGGER.error("getAutomobileById({})",id);
        }
        return automobile;
    }

    @Override
    public Automobile getAutomobileByNumber(String number) {
        LOGGER.debug("getAutomobileByNumber({})", number);
        Automobile automobile =null;
        try {
            automobile = automobileDao.getAutomobileByNumber(number);
        } catch (EmptyResultDataAccessException e) {
            LOGGER.error("getAutomobileByNumber({})",number);
        }
        return automobile;
    }

    @Override
    public List<Automobile> getAllAutomobiles() {
        LOGGER.debug("getAllAutomobiles");
        return automobileDao.getAllAutomobiles();
    }
}
