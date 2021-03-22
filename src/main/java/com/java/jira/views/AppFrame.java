package com.java.jira.views;

import javax.swing.*;

public class AppFrame {
    public static JFrame appFrame;

    public static JFrame createAppFrame() {
        appFrame = new JFrame();
        var loginView = Login.createLoginWindow();

        appFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        appFrame.setContentPane(loginView);
        appFrame.pack();

        appFrame.setLocationRelativeTo(null);
        appFrame.setVisible(true);

        return appFrame;
    }
}
