package com.java.jira.views;

import com.java.jira.component.FilterOption;
import com.java.jira.component.TaskItemBacklog;
import com.java.jira.models.Task;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SprintTasks extends JPanel {

    private List<Task> allTasks;

    public SprintTasks(List<Task> allTasks) {
        this.allTasks = allTasks;
        createSprintView();
    }

    private void createSprintView() {
        setLayout(new GridLayout(0, 1, 0, 3));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(25, 0, 25, 0));

        setTasks(allTasks);
    }

    public void setTasks(List<Task> filteredTaskList) {
        this.allTasks = filteredTaskList;
        removeAll();
        if (allTasks != null) {
            allTasks.forEach(task -> add(new TaskItemBacklog(task)));
        }
        revalidate();
    }
}
