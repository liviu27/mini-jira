package com.java.jira.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public enum SprintRepository {
    SPRINT_REPOSITORY;

    private static final String GET_SPRINT_ACRONYM = "SELECT ";

    public String getAcronym(Connection connection, int sprintId) throws SQLException {
        String sprintAcronym = null;
        try(PreparedStatement pstm = connection.prepareStatement(GET_SPRINT_ACRONYM)) {
            pstm.setInt(1, sprintId);
            final ResultSet resultSet = pstm.executeQuery();
            if(resultSet.next()) {
                sprintAcronym = resultSet.toString();
            }
        }
        return sprintAcronym;
    }
}
