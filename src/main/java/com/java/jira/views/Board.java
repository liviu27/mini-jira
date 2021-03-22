package com.java.jira.views;

import com.java.jira.models.Task;
import com.java.jira.utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

public class Board extends JPanel {
    private List<Task> tasks;
    private UserTasks taskCategoryView;

    public Board(List<Task> tasks) {
        this.tasks = tasks;
        createBoardView();
    }


    private void createBoardView() {
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
        JLabel title = new JLabel("Board");
        title.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));
        title.setFont(new Font(UIManager.getLookAndFeelDefaults().toString(), Font.BOLD, 40));
        titlePanel.add(title);

        JPanel searchAndFilterPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
        searchAndFilterPanel.setBackground(Color.WHITE);
        searchAndFilterPanel.setBorder(BorderFactory.createEmptyBorder(20, -5, 0, 0));

        JPanel intermediarySearchPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
        JTextField searchBar = new JTextField(10);
        searchBar.setSize(50, 25);
        searchBar.setBackground(new Color(240, 240, 240));
        searchBar.setBorder(BorderFactory.createEmptyBorder());
        final InputStream resourceAsStream = Board.class.getClassLoader().getResourceAsStream("magnifier.jpg");
        // add icon TODO

        intermediarySearchPanel.add(searchBar);

        //Add filter section
        String[] filterOptions = {"", "Low priority", "High priority", "No priority"}; // modify type & implement methods TODO
        JComboBox filter = new JComboBox(filterOptions);
        filter.setSelectedIndex(0);
        //Filter implementation TODO

        searchAndFilterPanel.add(intermediarySearchPanel);
        searchAndFilterPanel.add(filter);

        summaryPanel.add(titlePanel);
        summaryPanel.add(searchAndFilterPanel);

        searchBar.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                final String searchText = searchBar.getText().toLowerCase();
                if (searchText.length() > 2) {
                    long time = System.currentTimeMillis();
                    var filteredTaskList = filter(tasks, searchText);
                    System.out.println(System.currentTimeMillis() - time);
                    taskCategoryView.setTasks(filteredTaskList);

                } else {
                    taskCategoryView.setTasks(tasks);
                }
            }
        });

        taskCategoryView = new UserTasks(tasks);

        //Packing Information panel
        add(summaryPanel, BorderLayout.NORTH);
        add(taskCategoryView, BorderLayout.CENTER);

    }

    static List<Task> filter(List<Task> referenceList, String search) {
        return referenceList.stream()
                .filter(task -> Utils.taskContainsIgnoreCase(task, search))
                .collect(Collectors.toList());
    }
}
