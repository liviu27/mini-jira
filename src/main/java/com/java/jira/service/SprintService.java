package com.java.jira.service;

import com.java.jira.exceptions.technical.DatabaseConnectionException;

import java.sql.Connection;
import java.sql.SQLException;

import static com.java.jira.database.MySqlConnection.DATA_SOURCE;

public enum SprintService {

    SPRINT_SERVICE;

    public String getSprintAcronym(int sprintId) {
        try (Connection connection = DATA_SOURCE.getConnection()) {
            return SPRINT_SERVICE.getSprintAcronym(sprintId);
        } catch (SQLException exception) {
            throw new DatabaseConnectionException();
        }
    }

}
