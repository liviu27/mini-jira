package com.java.jira.repository;

import com.java.jira.models.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public enum TaskRepository {
    TASK_REPOSITORY;

    private static final String GET_ALL_TASKS = "SELECT * FROM tasks";
    private static final String GET_TASKS_BY_USER_ID = "SELECT * FROM tasks WHERE accountID = ?";

    public List<Task> getAllTasks(Connection connection) throws SQLException {
        List<Task> allTasks = new ArrayList<>();
        try (Statement stm = connection.createStatement()) {
            final ResultSet resultSet = stm.executeQuery(GET_ALL_TASKS);
            while (resultSet.next()) {
                allTasks.add(convert(resultSet));
            }
        }
        return allTasks;
    }

    public List<Task> getAllTasks(Connection connection, int accountId) throws SQLException {
        List<Task> allTasks = new ArrayList<>();
        try (PreparedStatement pstm = connection.prepareStatement(GET_TASKS_BY_USER_ID)) {
            pstm.setInt(1, accountId);
            final ResultSet resultSet = pstm.executeQuery();
            while (resultSet.next()) {
                allTasks.add(convert(resultSet));
            }
        }
        return allTasks;
    }

    private Task convert(ResultSet resultSet) throws SQLException {
        final int taskId = resultSet.getInt(1);
        final String taskTitle = resultSet.getString(2);
        final String taskDescription = resultSet.getString(3);
        final String taskCategory = resultSet.getString(4);
        final String taskType = resultSet.getString(5);
        final String taskStatus = resultSet.getString(6);
        final String taskPriority = resultSet.getString(7);
        final int taskAccountId = resultSet.getInt(8);
        final int taskSprintId = resultSet.getInt(9);
        final int taskWorkedHours = resultSet.getInt(10);
        final int taskEstimation = resultSet.getInt(11);

        return Task.builder()
                .id(taskId)
                .title(taskTitle)
                .description(taskDescription)
                .category(TaskCategory.valueOf(taskCategory))
                .type(TaskType.valueOf(taskType))
                .status(TaskStatus.valueOf(taskStatus))
                .priority(TaskPriority.valueOf(taskPriority))
                .accountId(taskAccountId)
                .sprintId(taskSprintId)
                .workedHours(taskWorkedHours)
                .estimation(taskEstimation)
                .build();
    }

}
