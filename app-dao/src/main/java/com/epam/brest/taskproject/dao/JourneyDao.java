package com.epam.brest.taskproject.dao;

import com.epam.brest.taskproject.domain.*;

import java.util.Date;
import java.util.List;

/**
 * Created by alesya on 18.11.14.
 */
public interface JourneyDao {

    public Long addJourney(Journey journey);

    public void removeJourney(Long id);

    public void updateJourney(Journey journey);

    public Journey getJourneyById(Long id);

    public List<Journey> getAllJourneys();

    public List<Journey> getJourneys(Date date1, Date date2);

    public List<Journey> getAllJourneysOfAutomobile(Long automobileId);

    public List<Journey> getJourneysOfAutomobile(Long automobileId, Date date1, Date date2);

    public List<AutomobileSummary> getAutomobileSummaries();

    public List<AutomobileSummary> getAutomobileSummaries(Date date1, Date date2);

}
