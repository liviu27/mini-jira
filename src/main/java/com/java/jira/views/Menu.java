package com.java.jira.views;

import com.java.jira.component.MenuItem;
import com.java.jira.component.MenuOption;
import com.java.jira.models.Task;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import static com.java.jira.service.AccountService.ACCOUNT_SERVICE;

public class Menu extends JPanel {

    private List<Task> allTasks;
    private List<Task> userTasks;


    public Menu(List<Task> allTasks, List<Task> userTasks) {
        this.allTasks = allTasks;
        this.userTasks = userTasks;
        createMenuView(allTasks, userTasks);
    }

    public void createMenuView(List<Task> allTasks, List<Task> userTasks) {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(300, 0));

        //1. Account + sprint info
        JPanel accountInfo = new JPanel();
        accountInfo.setPreferredSize(new Dimension(270, 150));
        JLabel accountName = new JLabel(ACCOUNT_SERVICE.getCurrentLoggedAccount().getName());
//        JLabel sprintName = new JLabel(ACCOUNT_SERVICE.getEnrolledProject().getName()); // TODO

        accountInfo.add(accountName);
//        accountInfo.add(sprintName);

        //2. Menu options
        JPanel menuOptions = new JPanel();
        menuOptions.setPreferredSize(new Dimension(270, 0));
        menuOptions.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));

        //2.1 Backlog option
        JPanel backlogPanel = new MenuItem("BACKLOG");
        backlogPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MainWindow.mainPanel.remove(MainWindow.informationPanel);
                MainWindow.informationPanel = new Backlog(allTasks);
                MainWindow.mainPanel.add(MainWindow.informationPanel);
                MainWindow.mainPanel.revalidate();
            }
        });


        //2.2 Board option
        JPanel boardPanel = new MenuItem("BOARD");
        boardPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MainWindow.mainPanel.remove(MainWindow.informationPanel);
                MainWindow.informationPanel = new Board(userTasks);
                MainWindow.mainPanel.add(MainWindow.informationPanel);
                MainWindow.mainPanel.revalidate();
            }
        });

        //2.3 Log out option
        JPanel logoutPanel = new MenuItem("LOG OUT");
        logoutPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                AppFrame.appFrame.setVisible(false);
                AppFrame.appFrame.setMaximumSize(new Dimension(400, 220));
                AppFrame.appFrame.setContentPane(Login.createLoginWindow());

                AppFrame.appFrame.setLocationRelativeTo(null);
                AppFrame.appFrame.setExtendedState(Frame.NORMAL);
                AppFrame.appFrame.setVisible(true);

            }
        });

        //2.4 Pack menuOption Panel
        menuOptions.add(backlogPanel);
        menuOptions.add(boardPanel);
        menuOptions.add(logoutPanel);


        add(accountInfo, BorderLayout.NORTH);
        add(menuOptions, BorderLayout.CENTER);
    }

}
