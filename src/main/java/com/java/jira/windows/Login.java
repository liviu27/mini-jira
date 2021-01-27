package com.java.jira.windows;

import com.java.jira.exceptions.business.WrongCredentialsException;

import javax.swing.*;
import java.awt.*;

import static com.java.jira.service.AccountService.ACCOUNT_SERVICE;
import static com.java.jira.utils.Utils.addBorder;

public class Login {


    public static JFrame showLoginFrame() {
        JFrame loginFrame = new JFrame("Jira Login");
        var loginWindow = createLoginWindow();

        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setContentPane(loginWindow);
        loginFrame.pack();

        loginFrame.setSize(400, 250);
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setVisible(true);

        return loginFrame;
    }

    private static JPanel createLoginWindow() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));

        //Laying Title Pane
        JPanel titlePanel = new JPanel();
        JLabel titleLabel = new JLabel("LOGIN FORM");
        titleLabel.setFont(new Font(UIManager.getLookAndFeelDefaults().toString(), Font.BOLD, 16));
        titlePanel.add(titleLabel);
        titlePanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        // Laying LoginInfo Pane
        JPanel loginInfo = new JPanel(new GridLayout(2, 2, 10, 5));
        JLabel usernameLabel = new JLabel("Username:", SwingConstants.TRAILING);
        JLabel passwordLabel = new JLabel("Password:", SwingConstants.TRAILING);

        JTextField usernameTextField = new JTextField(12);
        JPasswordField passwordField = new JPasswordField(12);

        loginInfo.add(usernameLabel);
        loginInfo.add(usernameTextField);
        loginInfo.add(passwordLabel);
        loginInfo.add(passwordField);
        loginInfo.setBorder(BorderFactory.createEmptyBorder(15, 0, 10, 0));

        JPanel intermediaryLoginInfo = new JPanel();
        intermediaryLoginInfo.add(loginInfo, BorderLayout.CENTER);

        //Lay the Button Pane
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BorderLayout());

        JButton loginButton = new JButton("LOGIN");
        loginButton.setPreferredSize(new Dimension(100, 25));
        loginButton.addActionListener(e -> {
            final String username = usernameTextField.getText();
            final String password = new String(passwordField.getPassword());
            try {
                ACCOUNT_SERVICE.getVerifiedAccount(username, password);
                System.out.println("login successfully");
            } catch (WrongCredentialsException ex) {
                System.out.println(ex.getMessage());
            }
        });
        buttonsPanel.add(loginButton);

        JPanel intermediaryButtonPanel = new JPanel();
        intermediaryButtonPanel.add(buttonsPanel);

        // Laying Registration Pane
        JPanel registrationPanel = new JPanel();
        JLabel registrationLabel = new JLabel("Don't have an account? Register here.");
        registrationPanel.add(registrationLabel);

        // Pack all panes in the main pane
        mainPanel.add(titlePanel);
        mainPanel.add(intermediaryLoginInfo);
        mainPanel.add(intermediaryButtonPanel);
        mainPanel.add(registrationPanel);


        return mainPanel;
    }

}
