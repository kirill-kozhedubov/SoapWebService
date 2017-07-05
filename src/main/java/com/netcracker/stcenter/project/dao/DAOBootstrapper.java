package com.netcracker.stcenter.project.dao;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.TimeZone;

public class DAOBootstrapper {
    private static final Logger LOGGER = Logger.getLogger(DAOBootstrapper.class);


    public ProjectCreationDAO bootstrapDao() {
        return new ProjectCreationDAOImpl(getSimpleJdbcCall(), getJdbcTemplate());
    }


    private JdbcTemplate getJdbcTemplate() {
        return new JdbcTemplate(getDataSource());
    }

    private DataSource getDataSource() {
        TimeZone timeZone = TimeZone.getTimeZone("Europe/Helsinki");
        TimeZone.setDefault(timeZone);
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        dataSource.setUrl(System.getenv("SQL_JDBC_URL"));
        dataSource.setUsername(System.getenv("SQL_LOGIN"));
        dataSource.setPassword(System.getenv("SQL_PASSWORD"));
        return dataSource;
    }



    private SimpleJdbcCall getSimpleJdbcCall() {
        return new SimpleJdbcCall(getDataSource());
    }
}
