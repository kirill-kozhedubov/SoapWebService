package com.netcracker.stcenter.project.dao;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.TimeZone;

public class DAOBootstrapper {
    private static final Logger LOGGER = Logger.getLogger(DAOBootstrapper.class);


    public static ProjectCreationDAO bootstrapDao() {
        TimeZone timeZone = TimeZone.getTimeZone("Europe/Helsinki");
        TimeZone.setDefault(timeZone);
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        dataSource.setUrl(System.getenv("TEST_SQL_JDBC_URL"));
        dataSource.setUsername(System.getenv("TEST_SQL_LOGIN"));
        dataSource.setPassword(System.getenv("TEST_SQL_PASSWORD"));

        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(dataSource);
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        return new ProjectCreationDAOImpl(simpleJdbcCall, jdbcTemplate);
    }


}
