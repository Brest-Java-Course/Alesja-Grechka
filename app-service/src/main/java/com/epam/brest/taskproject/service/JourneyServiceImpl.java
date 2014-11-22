package com.epam.brest.taskproject.service;

import com.epam.brest.taskproject.dao.JourneyDao;
import com.epam.brest.taskproject.domain.AutomobileSummary;
import com.epam.brest.taskproject.domain.Journey;
import org.springframework.stereotype.Service;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by alesya on 20.11.14.
 */
@Service
public class JourneyServiceImpl implements JourneyService {

    private static final Logger LOGGER = LogManager.getLogger();

    public static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    JourneyDao journeyDao;

    @Override
    public Long addJourney(Journey journey) {
        //TODO: process situation if there is no such automobile,or different automobile parameters
        LOGGER.debug("addJourney({})", journey);
        Assert.isNull(journey.getId(), "journey id should not be specified");
        Assert.notNull(journey, "journey should be specified");
        Assert.notNull(journey.getAutomobile(), "journey automobile should not be specified");
        Assert.notNull(journey.getAutomobile().getId(), "journey automobileId should be specified");
        Assert.notNull(journey.getDistance(), "journey distance should be specified");
        Assert.notNull(journey.getDate(), "journey date should be specified");
        Assert.notNull(journey.getOriginDestination(),"journey origin-destination should be specified");
        return journeyDao.addJourney(journey);
    }

    @Override
    public void removeJourney(Long id) {
        LOGGER.debug("removeJourney({})",id);
        journeyDao.removeJourney(id);
    }

    @Override
    public void updateJourney(Journey journey) {
        //TODO: process situation if there is no such automobile,or different automobile parameters
        LOGGER.debug("updateJourney({})",journey);

        try {
            journeyDao.addJourney(journey);
        } catch (IllegalArgumentException e) {
            LOGGER.error("updateJourney({})",journey);
        }
    }

    @Override
    public Journey getJourneyById(Long id) {
        LOGGER.debug("getJourneyById({})", id);
        Journey journey = null;
        try{
            journey = journeyDao.getJourneyById(id);
        } catch (EmptyResultDataAccessException e){
            LOGGER.error("getJourneyById({}): {}", id, e.toString());
        }
        return journey;
    }

    @Override
    public List<Journey> getAllJourneys() {
        LOGGER.debug("getAllJourneys()");
        return journeyDao.getAllJourneys();
    }

    @Override
    public List<Journey> getJourneys(Date date1, Date date2) {
        LOGGER.debug("getJourneys({}, {})", SDF.format(date1), SDF.format(date2));
        return journeyDao.getJourneys(date1, date2);
    }

    @Override
    public List<Journey> getAllJourneysOfAutomobile(Long automobileId) {
        LOGGER.debug("getAllJourneysOfAutomobile({})",automobileId);
        return journeyDao.getAllJourneysOfAutomobile(automobileId);
    }

    @Override
    public List<Journey> getJourneysOfAutomobile(Long automobileId, Date date1, Date date2) {
        LOGGER.debug("getJourneysOfAutomobile({}, {}, {})", automobileId,
                SDF.format(date1), SDF.format(date2));
        return journeyDao.getJourneysOfAutomobile(automobileId, date1, date2);
    }

    @Override
    public List<AutomobileSummary> getAutomobileSummaries() {
        LOGGER.debug("getAutomobileSummaries()");
        return journeyDao.getAutomobileSummaries();
    }

    @Override
    public List<AutomobileSummary> getAutomobileSummaries(Date date1, Date date2) {
        LOGGER.debug("getAutomobileSummaries({}, {})", SDF.format(date1), SDF.format(date2));
        return journeyDao.getAutomobileSummaries(date1, date2);
    }
}
