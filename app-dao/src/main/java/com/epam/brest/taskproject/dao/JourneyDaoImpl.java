package com.epam.brest.taskproject.dao;

import com.epam.brest.taskproject.domain.Automobile;
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
//import java.sql.Date;

/**
 * Created by alesya on 18.11.14.
 */

@Component
public class JourneyDaoImpl implements JourneyDao {

    private static final Logger LOGGER = LogManager.getLogger();

    public static final String AUTOMOBILE_ID= "automobileid";
    public static final String MAKE= "make";
    public static final String NUMBER= "number";
    public static final String FUEL_RATE= "fuelrate";
    public static final String JOURNEY_ID = "journeyid";
    public static final String ORIGIN_DESTINATION = "origin_destination";
    public static final String JOURNEY_DATE = "journey_date";
    public static final String DISTANCE = "distance";
    public static final String SUM_DISTANCE = "sumdistance";


    @Value("#{T(org.apache.commons.io.IOUtils).toString((new org.springframework.core.io.ClassPathResource('${add_journey_path}')).inputStream)}")
    public String addJourney;

    @Value("#{T(org.apache.commons.io.IOUtils).toString((new org.springframework.core.io.ClassPathResource('${get_all_journeys_of_automobile_path}')).inputStream)}")
    public String getAllJourneysOfAutomobile;

    @Value("#{T(org.apache.commons.io.IOUtils).toString((new org.springframework.core.io.ClassPathResource('${get_all_journeys_path}')).inputStream)}")
    public String getAllJourneys;

    @Value("#{T(org.apache.commons.io.IOUtils).toString((new org.springframework.core.io.ClassPathResource('${get_automobile_by_id_path}')).inputStream)}")
    public String getAutomobileById;

    @Value("#{T(org.apache.commons.io.IOUtils).toString((new org.springframework.core.io.ClassPathResource('${get_automobile_summaries_between_dates_path}')).inputStream)}")
    public String getAutomobileSummariesBetweenDates;

    @Value("#{T(org.apache.commons.io.IOUtils).toString((new org.springframework.core.io.ClassPathResource('${get_automobile_summaries_path}')).inputStream)}")
    public String getAutomobileSummaries;

    @Value("#{T(org.apache.commons.io.IOUtils).toString((new org.springframework.core.io.ClassPathResource('${get_journey_by_id_path}')).inputStream)}")
    public String getJourneyById;

    @Value("#{T(org.apache.commons.io.IOUtils).toString((new org.springframework.core.io.ClassPathResource('${get_journeys_between_dates_path}')).inputStream)}")
    public String getJourneysBetweenDates;

    @Value("#{T(org.apache.commons.io.IOUtils).toString((new org.springframework.core.io.ClassPathResource('${get_journeys_of_automobile_between_dates_path}')).inputStream)}")
    public String getJourneysOfAutomobileBetweenDates;

    @Value("#{T(org.apache.commons.io.IOUtils).toString((new org.springframework.core.io.ClassPathResource('${remove_journey_path}')).inputStream)}")
    public String removeJourney;

    @Value("#{T(org.apache.commons.io.IOUtils).toString((new org.springframework.core.io.ClassPathResource('${update_journey_path}')).inputStream)}")
    public String updateJourney;

    public static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");

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
        parameters.put(JOURNEY_DATE, SDF.format(journey.getDate()));
        parameters.put(ORIGIN_DESTINATION, journey.getOriginDestination());
        parameters.put(DISTANCE, journey.getDistance());
        LOGGER.debug("addJourney({}) parameters", parameters.toString());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedJdbcTemplate.update(addJourney, new MapSqlParameterSource(parameters), keyHolder);
        return keyHolder.getKey().longValue();
    }

    @Override
    public void removeJourney(Long id) {
        LOGGER.debug("removeJourney({})", id);
        jdbcTemplate.update(removeJourney, id);

    }

    @Override
    public void updateJourney(Journey journey) {
        //TODO: cascade update automobile if necessary

        LOGGER.debug("updateJourney({})", journey);
        Map<String, Object> parameters = new HashMap(5);
        parameters.put(JOURNEY_ID, journey.getId());
        parameters.put(AUTOMOBILE_ID, journey.getAutomobile().getId());
        parameters.put(JOURNEY_DATE, SDF.format(journey.getDate()));
        parameters.put(ORIGIN_DESTINATION, journey.getOriginDestination());
        parameters.put(DISTANCE, journey.getDistance());
        namedJdbcTemplate.update(updateJourney,parameters);
    }

    @Override
    public Journey getJourneyById(Long id) {
        LOGGER.debug(" getJourneyById({})",id);
        return jdbcTemplate.queryForObject(getJourneyById, new JourneyMapper(), id);
    }

    @Override
    public List<Journey> getAllJourneys() {
        LOGGER.debug("getAllJourneys()");
        return jdbcTemplate.query(getAllJourneys, new JourneyMapper());
    }

    @Override
    public List<Journey> getJourneys(Date date1, Date date2) {
        LOGGER.debug("getJourneys ({} {})", SDF.format(date1),SDF.format(date2));
        return jdbcTemplate.query(getJourneysBetweenDates, new JourneyMapper(),SDF.format(date1),SDF.format(date2));
    }

    @Override
    public List<Journey> getAllJourneysOfAutomobile(Long automobileId) {
        LOGGER.debug("getAllJourneysOfAutomobile({})",automobileId);
        return jdbcTemplate.query(getAllJourneysOfAutomobile, new JourneyMapper(),automobileId);
    }

    @Override
    public List<Journey> getJourneysOfAutomobile(Long automobileId, Date date1, Date date2) {
        LOGGER.debug("getJourneysOfAutomobile({} {}) automobile {}",
                SDF.format(date1),SDF.format(date2),automobileId);
        return jdbcTemplate.query(getJourneysOfAutomobileBetweenDates,new JourneyMapper(),
                SDF.format(date1),SDF.format(date2),automobileId);
    }

    @Override
    public List<AutomobileSummary> getAutomobileSummaries() {
        LOGGER.debug("getAutomobileSummaries");
        return jdbcTemplate.query(getAutomobileSummaries, new AutomobileSummaryMapper());
    }

    @Override
    public List<AutomobileSummary> getAutomobileSummaries(Date date1, Date date2) {
        LOGGER.debug("getAutomobileSummaries(){}, {}",SDF.format(date1),SDF.format(date2));
        return jdbcTemplate.query(getAutomobileSummariesBetweenDates, new AutomobileSummaryMapper(),
                SDF.format(date1),SDF.format(date2));
    }

    public class JourneyMapper implements RowMapper<Journey> {
        public Journey mapRow(ResultSet rs, int i) throws SQLException {
            Journey journey = new Journey();
            Automobile automobile = new Automobile();
            automobile.setId(rs.getLong(AUTOMOBILE_ID));
            automobile.setMake(rs.getString(MAKE));
            automobile.setNumber(rs.getString(NUMBER));
            automobile.setFuelRate(rs.getDouble(FUEL_RATE));
            journey.setAutomobile(automobile);
            journey.setId(rs.getLong(JOURNEY_ID));
            journey.setOriginDestination(rs.getString(ORIGIN_DESTINATION));
            journey.setDistance(rs.getDouble(DISTANCE));
            journey.setDate(rs.getDate(JOURNEY_DATE));
            return journey;
        }
    }


    public class AutomobileSummaryMapper implements RowMapper<AutomobileSummary> {
        public AutomobileSummary mapRow(ResultSet rs, int i) throws SQLException {
            Journey journey = new Journey();
            AutomobileSummary automobileSummary = new AutomobileSummary();

            Automobile automobile = new Automobile();
            automobile.setId(rs.getLong(AUTOMOBILE_ID));
            automobile.setMake(rs.getString(MAKE));
            automobile.setNumber(rs.getString(NUMBER));
            automobile.setFuelRate(rs.getDouble(FUEL_RATE));

            automobileSummary.setAutomobile(automobile);
            automobileSummary.setSumDistance(rs.getDouble(SUM_DISTANCE));

            return automobileSummary;
        }
    }
}
