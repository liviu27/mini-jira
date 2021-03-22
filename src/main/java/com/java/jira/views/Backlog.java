package com.java.jira.views;

import com.java.jira.Main;
import com.java.jira.component.FilterItem;
import com.java.jira.component.FilterOption;
import com.java.jira.models.Task;
import com.java.jira.utils.Utils;
import jdk.jshell.execution.Util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class Backlog extends JPanel {

    private List<Task> tasks;
    private SprintTasks sprintTasksView;

    public Backlog(List<Task> tasks) {
        this.tasks = tasks;
        createBacklogView();
    }

    public void createBacklogView() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(0, 25, 0, 25));
        setBackground(Color.WHITE);

        //2.1 Laying Summary Panel
        JPanel summaryPanel = new JPanel();
        summaryPanel.setLayout(new GridLayout(2, 1));
        summaryPanel.setPreferredSize(new Dimension(0, 150));
        summaryPanel.setBackground(Color.WHITE);

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.PAGE_AXIS));
        titlePanel.setBackground(Color.WHITE);
        JLabel title = new JLabel("Backlog");
        title.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));
        title.setFont(title.getFont().deriveFont(Font.BOLD, 40));
        titlePanel.add(title);

        JPanel quickFiltersPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));

        quickFiltersPanel.setBackground(Color.WHITE);
        quickFiltersPanel.setBorder(BorderFactory.createEmptyBorder(20, -5, 0, 0));

        JLabel quickFilterLabel = new JLabel("QUICK FILTERS: ");
        quickFilterLabel.setFont(quickFilterLabel.getFont().deriveFont(Font.BOLD, 14));
        quickFilterLabel.setForeground(Color.gray);

        //Quick filter section - buttons


        quickFiltersPanel.add(quickFilterLabel);

        for (FilterOption option : FilterOption.values()) {
            quickFiltersPanel.add(new FilterItem(option.getFilterText()));

        }

        summaryPanel.add(titlePanel);
        summaryPanel.add(quickFiltersPanel);

        sprintTasksView = new SprintTasks(tasks);

//        JPanel intermediary = new JPanel();
//        intermediary.add(sprintTasksView);


        //Packing Information panel
        add(summaryPanel, BorderLayout.NORTH);
//        add(intermediary, BorderLayout.CENTER);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(sprintTasksView);
        add(panel, BorderLayout.CENTER);
    }


}
