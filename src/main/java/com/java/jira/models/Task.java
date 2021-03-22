package com.java.jira.models;

import lombok.Builder;
import lombok.Value;

@Builder(toBuilder = true)
@Value

public class Task {

    int id;
    String title;
    String description;
    TaskCategory category;
    TaskType type;
    TaskPriority priority;
    TaskStatus status;
    int accountId;
    int sprintId;
    int workedHours;
    int estimation;

}
