package com.java.jira.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public enum MySqlConnection {

    DATA_SOURCE;

    private static final String DB_RL = "jdbc:mysql://localhost:3306/mini_jira";
    private static final String DB_ADMIN = "root";
    private static final String DB_PASSWORD = "root";

    private final HikariDataSource hikariDataSource;


    MySqlConnection() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(DB_RL);
        config.setUsername(DB_ADMIN);
        config.setPassword(DB_PASSWORD);
        hikariDataSource = new HikariDataSource(config);
    }

    public Connection getConnection() throws SQLException {
        return hikariDataSource.getConnection();
    }


}
