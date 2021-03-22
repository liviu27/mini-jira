package com.java.jira.service;

import com.java.jira.exceptions.technical.DatabaseConnectionException;
import com.java.jira.models.Task;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static com.java.jira.database.MySqlConnection.DATA_SOURCE;
import static com.java.jira.repository.TaskRepository.TASK_REPOSITORY;

public enum TaskService {
    TASK_SERVICE;

    public List<Task> getAllTasks() {
        try (Connection connection = DATA_SOURCE.getConnection()) {
            return TASK_REPOSITORY.getAllTasks(connection);
        } catch (SQLException ex) {
            throw new DatabaseConnectionException();
        }
    }

    public List<Task> getAllTask(int accountId) {
        try (Connection connection = DATA_SOURCE.getConnection()) {
            return TASK_REPOSITORY.getAllTasks(connection, accountId);
        } catch (SQLException exception) {
            throw new DatabaseConnectionException();
        }
    }

}
