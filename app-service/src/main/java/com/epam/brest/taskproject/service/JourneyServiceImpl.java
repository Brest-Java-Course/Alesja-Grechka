package com.epam.brest.taskproject.service;

import com.epam.brest.taskproject.domain.AutomobileSummary;
import com.epam.brest.taskproject.domain.Journey;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by alesya on 20.11.14.
 */
@Service
public class JourneyServiceImpl implements JourneyService {
    @Override
    public Long addJourney(Journey journey) {
        // TODO:
        return null;
    }

    @Override
    public void removeJourney(Long id) {
        // TODO:

    }

    @Override
    public void updateJourney(Journey journey) {
        // TODO:

    }

    @Override
    public Journey getJourneyById(Long id) {
        // TODO:
        return null;
    }

    @Override
    public List<Journey> getAllJourneys() {
        // TODO:
        return null;
    }

    @Override
    public List<Journey> getJourneys(Date date1, Date date2) {
        // TODO:
        return null;
    }

    @Override
    public List<Journey> getAllJourneysOfAutomobile(Long automobileId) {
        // TODO:
        return null;
    }

    @Override
    public List<Journey> getJourneysOfAutomobile(Long automobileId, Date date1, Date date2) {
        // TODO:
        return null;
    }

    @Override
    public List<AutomobileSummary> getAutomobileSummaries() {
        // TODO:
        return null;
    }

    @Override
    public List<AutomobileSummary> getAutomobileSummaries(Date date1, Date date2) {
        // TODO:
        return null;
    }
}
