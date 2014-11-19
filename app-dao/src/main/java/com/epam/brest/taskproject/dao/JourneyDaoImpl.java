package com.epam.brest.taskproject.dao;

import com.epam.brest.taskproject.domain.AutomobileSummary;
import com.epam.brest.taskproject.domain.Journey;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * Created by alesya on 18.11.14.
 */

@Component
public class JourneyDaoImpl implements JourneyDao {

    private static final Logger LOGGER = LogManager.getLogger();

    public static final String JOURNEY_ID = "journeyid";
    public static final String AUTOMOBILE_ID= "automobileid";
    public static final String ORIGIN_DESTINATION = "origin_destination";
    public static final String JOURNEY_DATE = "journey_date";
    public static final String DISTANCE = "distance";
    public static final String DATE_FROM = "date1";
    public static final String DATE_TO = "date2";

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Value("#{T(org.apache.commons.io.IOUtils).toString((new org.springframework.core.io.ClassPathResource('${add_journey_path}')).inputStream)}")
    public String addJourney;

    @Autowired
    private DataSource dataSource;

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedJdbcTemplate;

    @PostConstruct
    public void init() {
        jdbcTemplate = new JdbcTemplate(dataSource);
        namedJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }


    @Override
    public Long addJourney(Journey journey) {

        LOGGER.debug("addJourney({}) ", journey);
        Assert.notNull(journey);
        Assert.isNull(journey.getId());

        Assert.notNull(journey.getDate(), "journey date should be specified.");
        Assert.notNull(journey.getDistance(), "journey distance should be specified.");
        Assert.notNull(journey.getOriginDestination() , "journey origin-destination should be specified.");
        Assert.notNull(journey.getAutomobile().getId() , "Automobile should be specified.");

        Map<String, Object> parameters = new HashMap(5);
        parameters.put(JOURNEY_ID, journey.getId());
        parameters.put(AUTOMOBILE_ID, journey.getAutomobile().getId());
        parameters.put(JOURNEY_DATE,  sdf.format(journey.getDate()));
        parameters.put(ORIGIN_DESTINATION, journey.getOriginDestination());
        parameters.put(DISTANCE, journey.getDate());

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedJdbcTemplate.update(addJourney, new MapSqlParameterSource(parameters), keyHolder);
        return keyHolder.getKey().longValue();

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
