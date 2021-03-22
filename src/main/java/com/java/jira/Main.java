package com.java.jira;

import com.java.jira.views.AppFrame;
import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());
        SwingUtilities.invokeLater(() -> {
            AppFrame.createAppFrame();
        });
    }
}
