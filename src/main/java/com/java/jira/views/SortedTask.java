package com.java.jira.views;

import com.java.jira.component.TaskItem;
import com.java.jira.models.Task;
import com.java.jira.models.TaskStatus;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SortedTask extends JPanel {

    private TaskStatus status;
    private List<Task> tasks;

    public SortedTask(TaskStatus status, List<Task> tasks) {
        this.status = status;
        this.tasks = tasks;
        createStatusTaskView();
    }

    private void createStatusTaskView() {
        //2.2.1 To Do tasks
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));


        //2.2.1.1 Summary
        JPanel summaryPanel = new JPanel();
        summaryPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
        JLabel summaryLabel = new JLabel(status.getLabel());

        JLabel noOfTasks = new JLabel();
        noOfTasks.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
        if (tasks == null) {
            noOfTasks.setText("0");
        } else {
            noOfTasks.setText(String.valueOf(tasks.size()));
        }

        summaryPanel.add(summaryLabel);
        summaryPanel.add(noOfTasks);

        //2.2.1.2 Content
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.PAGE_AXIS));
        if (tasks != null) {
            tasks.forEach(task -> contentPanel.add(new TaskItem(task)));
        }

        //2.2.1.3 Pack
        add(summaryPanel);
        add(contentPanel);
    }


}
