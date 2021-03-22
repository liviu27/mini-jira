package com.java.jira.component;

import com.java.jira.models.Task;

import javax.swing.*;
import java.awt.*;

public class TaskItemBacklog extends JPanel {

    private final Task task;

    public TaskItemBacklog(Task task) {
        super(new GridLayout(1, 2));
        this.task = task;

        setPreferredSize(new Dimension(0, 30));

        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(Color.black));

        JPanel leftSide = new JPanel(new FlowLayout(FlowLayout.LEADING));
        JLabel taskType = new JLabel(task.getType().toString());
        JLabel taskPriority = new JLabel(task.getPriority().toString());
        JLabel taskDescription = new JLabel(task.getDescription());
        taskDescription.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        leftSide.add(taskType);
        leftSide.add(taskPriority);
        leftSide.add(taskDescription);

        JPanel rightSide = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        JLabel category = new JLabel(task.getCategory().toString());
        JLabel userName = new JLabel(String.valueOf(task.getAccountId()));
        JLabel estimation = new JLabel(String.valueOf(task.getEstimation()));
        rightSide.add(category);
        rightSide.add(userName);
        rightSide.add(estimation);

        add(leftSide);
        add(rightSide);


    }
}
