package com.java.jira.views;

import com.java.jira.models.Task;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import static com.java.jira.service.AccountService.ACCOUNT_SERVICE;
import static com.java.jira.service.TaskService.TASK_SERVICE;

public class MainWindow {

    static JPanel informationPanel;
    static JPanel mainPanel;

    static List<Task> userTasks;

    public static JPanel createMainWindow() {
        final List<Task> allTasks = TASK_SERVICE.getAllTasks();
        userTasks = TASK_SERVICE.getAllTask(ACCOUNT_SERVICE.getCurrentLoggedAccount().getId());

        //Main Panel
        mainPanel = new JPanel();

        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 25, 25, 30));

        //1. Showing Menu Panel
        Menu menu = new Menu(allTasks, userTasks);
//        menu.setSelectedMenuItem(MenuOption.BOARD_VIEW);

        //2. Laying information panel

        informationPanel = new JPanel();
        informationPanel.setBorder(BorderFactory.createEmptyBorder(0, 25, 0, 25));
        informationPanel.setBackground(Color.WHITE);

        JLabel test = new JLabel("WELCOME");
        informationPanel.add(test);

        //Packing main panel
        mainPanel.add(menu, BorderLayout.WEST);
        mainPanel.add(informationPanel, BorderLayout.CENTER);

        return mainPanel;
    }
}
