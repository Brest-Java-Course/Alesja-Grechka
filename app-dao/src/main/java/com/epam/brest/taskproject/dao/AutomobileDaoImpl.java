package com.epam.brest.taskproject.dao;

import com.epam.brest.taskproject.domain.Automobile;

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


/**
 * Created by alesya on 18.11.14.
 */

@Component
public class AutomobileDaoImpl implements AutomobileDao {

    private static final Logger LOGGER = LogManager.getLogger();

    public static final String AUTOMOBILE_ID= "automobileid";
    public static final String MAKE= "make";
    public static final String NUMBER= "number";
    public static final String FUEL_RATE= "fuelrate";

    @Value("#{T(org.apache.commons.io.IOUtils).toString((new org.springframework.core.io.ClassPathResource('${add_automobile_path}')).inputStream)}")
    public String addAutomobile;
    @Value("#{T(org.apache.commons.io.IOUtils).toString((new org.springframework.core.io.ClassPathResource('${get_all_automobiles_path}')).inputStream)}")
    public String getAllAutomobiles;


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
    public Long addAutomobile(Automobile automobile) {
        LOGGER.debug("addAutomobile({}) ",automobile);
        Assert.notNull(automobile);
        Assert.isNull(automobile.getId());
        Assert.notNull(automobile.getMake(), "Automobile make should be specified.");
        Assert.notNull(automobile.getNumber(), "Automobile number should be specified.");
        Assert.notNull(automobile.getFuelRate(), "Automobile fuel rate should be specified.");


        Map<String, Object> parameters = new HashMap(4);
        parameters.put(AUTOMOBILE_ID, automobile.getId());
        parameters.put(MAKE, automobile.getMake());
        parameters.put(NUMBER, automobile.getNumber());
        parameters.put(FUEL_RATE, automobile.getFuelRate());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedJdbcTemplate.update(addAutomobile, new MapSqlParameterSource(parameters), keyHolder);
        LOGGER.debug("addAutomobile: id{} ",keyHolder.getKey().longValue());
        return keyHolder.getKey().longValue();
    }

    @Override
    public void removeAutomobile(Long id) {
        //TODO

    }

    @Override
    public void updateAutomobile(Automobile automobile) {
        //TODO
    }

    @Override
    public Automobile getAutomobileById(Long id) {
        //TODO
        return null;
    }

    @Override
    public List<Automobile> getAllAutomobiles() {
        LOGGER.debug("getAllAutomobiles");
        return jdbcTemplate.query(getAllAutomobiles, new AutomobileMapper());
    }


    public class AutomobileMapper implements RowMapper<Automobile> {
        public Automobile mapRow(ResultSet rs, int i) throws SQLException {
            Automobile automobile = new Automobile();
            automobile.setId(rs.getLong(AUTOMOBILE_ID));
            automobile.setMake(rs.getString(MAKE));
            automobile.setNumber(rs.getString(NUMBER));
            automobile.setFuelRate(rs.getDouble(FUEL_RATE));
            return automobile;
        }
    }
}
