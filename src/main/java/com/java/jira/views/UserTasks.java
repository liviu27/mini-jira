package com.java.jira.views;

import com.java.jira.models.Task;
import com.java.jira.models.TaskStatus;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class UserTasks extends JPanel {

    private List<Task> tasks;
    private Map<TaskStatus, SortedTask> sortedTaskViewMap;

    public UserTasks(List<Task> tasks) {
        this.tasks = tasks;
        this.sortedTaskViewMap = new HashMap<>();
        createTaskView();
    }


    private void createTaskView() {
        setLayout(new GridLayout(0, 4, 15, 0));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(25, 0, 25, 0));

        addUserTasks();
    }

    private void addUserTasks() {
        final Map<TaskStatus, List<Task>> sortedTasksMap = sortByTaskStatus();
        Arrays.stream(TaskStatus.values()).forEach(taskStatus -> {
            final SortedTask sortedTask = new SortedTask(taskStatus, sortedTasksMap.get(taskStatus));
            sortedTaskViewMap.put(taskStatus, sortedTask);
            add(sortedTask);

        });
    }

    private Map<TaskStatus, List<Task>> sortByTaskStatus() {
        Map<TaskStatus, List<Task>> map = new HashMap<>();
        tasks.forEach(task -> {
            List<Task> tasks = map.get(task.getStatus());
            if (tasks == null) {
                tasks = new ArrayList<>();
                map.put(task.getStatus(), tasks);
            }
            tasks.add(task);
        });
        return map;
    }

    public void setTasks(List<Task> filteredTaskList) {
        this.tasks = filteredTaskList;
        removeAll();
        addUserTasks();
        revalidate();
    }
}
