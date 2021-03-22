package com.java.jira.component;

import com.java.jira.models.Task;
import com.java.jira.models.TaskCategory;
import com.java.jira.utils.Utils;

import javax.swing.*;
import java.awt.*;

public class TaskItem extends JPanel {

    private final Task task;

    public TaskItem(Task task) {
        super(new BorderLayout());
        this.task = task;

        setPreferredSize(new Dimension(320, 150));
        setBorder(BorderFactory.createLineBorder(Color.blue));
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        setBackground(Color.WHITE);

        // 1. Task Description/Title
        JLabel taskDescriptionLabel = new JLabel();
        taskDescriptionLabel.setBackground(Color.WHITE);
        taskDescriptionLabel.setText(task.getDescription());
        taskDescriptionLabel.setFont(taskDescriptionLabel.getFont().deriveFont(Font.BOLD, 12));

        // 2. Category
        JPanel categoryPanel = new JPanel();
        JPanel intermediary = new JPanel();
        JLabel taskCategoryLabel = new JLabel();
        taskCategoryLabel.setText(task.getCategory().toString());
        taskCategoryLabel.setForeground(Color.WHITE);

        TaskCategory taskCategory = task.getCategory();
        Color taskCategoryColor = getTaskCategoryColor(taskCategory);
        intermediary.add(taskCategoryLabel);
        intermediary.setBackground(taskCategoryColor);
        categoryPanel.add(intermediary);

        // 3. Task additional info
        JPanel informationPanel = new JPanel(new GridLayout(1, 2));

        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
        JLabel taskType = new JLabel(task.getType().toString());
        JLabel taskPriority = new JLabel(task.getPriority().toString());
        JLabel taskEstimation = new JLabel("Time: " + task.getEstimation());
        leftPanel.add(taskType);
        leftPanel.add(taskPriority);
        leftPanel.add(taskEstimation);

        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
//        JLabel taskId = new JLabel(SPRINT_SERVICE.getSprintAcronym(task.getSprintId()) + "-" + task.getId()); TODO
        JLabel taskId = new JLabel("TTT");
        rightPanel.add(taskId);

        // to add co-account label
        informationPanel.add(leftPanel);
        informationPanel.add(rightPanel);

        // 4. Pack intermediary panels
        add(taskDescriptionLabel, BorderLayout.NORTH);
        add(categoryPanel, BorderLayout.CENTER);
        add(informationPanel, BorderLayout.SOUTH);


    }

    private Color getTaskCategoryColor(TaskCategory taskCategory) {
        return switch (taskCategory) {
            case ADMINISTRATIVE -> Color.DARK_GRAY;
            case TECHNICAL -> Color.BLUE;
            case FINANCIAL -> Color.ORANGE;
            case BUSINESS -> Color.GREEN;
            case OTHER -> Color.RED;
        };
    }

}
