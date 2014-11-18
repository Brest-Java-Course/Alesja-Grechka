package com.epam.brest.taskproject.dao;

import com.epam.brest.taskproject.domain.AutomobileSummary;
import com.epam.brest.taskproject.domain.Journey;

import java.util.Date;
import java.util.List;

/**
 * Created by alesya on 18.11.14.
 */
public class JourneyDaoImpl implements JourneyDao {
    //TODO:
    @Override
    public Long addJourney(Journey journey) {
        return null;
    }

    @Override
    public void removeJourney(Long id) {

    }

    @Override
    public void updateJourney(Journey journey) {

    }

    @Override
    public Journey getJourneyById(Long id) {
        return null;
    }

    @Override
    public List<Journey> getAllJourneys() {
        return null;
    }

    @Override
    public List<Journey> getJourneys(Date date1, Date date2) {
        return null;
    }

    @Override
    public List<Journey> getAllJourneysOfAutomobile(Long automobileId) {
        return null;
    }

    @Override
    public List<Journey> getJourneysOfAutomobile(Long automobileId, Date date1, Date date2) {
        return null;
    }

    @Override
    public List<AutomobileSummary> getAutomobileSummaries() {
        return null;
    }

    @Override
    public List<AutomobileSummary> getAutomobileSummaries(Date date1, Date date2) {
        return null;
    }
}
