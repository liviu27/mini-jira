package com.java.jira.models;

public enum TaskStatus {

    TO_DO("To Do"),
    IN_PROGRESS("In Progress"),
    IN_REVIEW("In Review"),
    DONE("Done");

    private final String label;

    TaskStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
