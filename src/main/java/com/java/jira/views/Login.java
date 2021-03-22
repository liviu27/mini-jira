package com.java.jira.views;

import com.java.jira.exceptions.business.WrongCredentialsException;
import com.java.jira.utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static com.java.jira.service.AccountService.ACCOUNT_SERVICE;
import static com.java.jira.views.AppFrame.*;

public class Login {
    static boolean successLogin;

    public static JPanel createLoginWindow() {
        //Create main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(400, 220));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));

        //Laying Title Pane
        JPanel titlePanel = new JPanel();
        JLabel titleLabel = new JLabel("LOGIN FORM");
        titleLabel.setFont(new Font(UIManager.getLookAndFeelDefaults().toString(), Font.BOLD, 16));
        titlePanel.add(titleLabel);
        titlePanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        // Laying LoginInfo Pane
        JPanel loginInfo = new JPanel(new GridLayout(2, 2, 10, 5));
        JLabel usernameLabel = new JLabel("Username:", SwingConstants.LEADING);
        JLabel passwordLabel = new JLabel("Password:", SwingConstants.LEADING);

        JTextField usernameTextField = new JTextField(12);
        JPasswordField passwordField = new JPasswordField(12);

        usernameTextField.setText("liviu"); //erase
        passwordField.setText("123456"); //erase

        loginInfo.add(usernameLabel);
        loginInfo.add(usernameTextField);
        loginInfo.add(passwordLabel);
        loginInfo.add(passwordField);
        loginInfo.setBorder(BorderFactory.createEmptyBorder(15, 0, 10, 0));

        JPanel intermediaryLoginInfo = new JPanel();
        intermediaryLoginInfo.add(loginInfo, BorderLayout.CENTER);

        //Lay warning pane
        JPanel wrongCredentialsPanel = new JPanel();
        JLabel wrongCredentialsLabel = new JLabel(" ");
        wrongCredentialsLabel.setForeground(Color.RED);
        wrongCredentialsPanel.add(wrongCredentialsLabel, Component.CENTER_ALIGNMENT);

        //Lay the Button Pane
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BorderLayout());

        JButton loginButton = new JButton("LOGIN");
        loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginButton.setPreferredSize(new Dimension(100, 25));
        loginButton.addActionListener(e -> {
            successLogin = login(usernameTextField.getText(), new String(passwordField.getPassword()));
            if (!successLogin) {
                wrongCredentialsLabel.setText("Incorrect credentials. Please try again or sign up!");
            }
        });
        buttonsPanel.add(loginButton);

        JPanel intermediaryButtonPanel = new JPanel();
        intermediaryButtonPanel.add(buttonsPanel);

        // Laying Registration Pane
        JPanel registrationPanel = new JPanel();
        JLabel questionLabel = new JLabel("Don't have an account?");
        JLabel registrationLabel = new JLabel("<html><u>Register here</u></html>");
        registrationLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        final Font font = registrationLabel.getFont();
        registrationPanel.setFont(font.deriveFont(font.getStyle() | Font.ITALIC));
        registrationPanel.add(questionLabel);
        registrationPanel.add(registrationLabel);

        // Pack all panes in the main pane
        mainPanel.add(titlePanel);
        mainPanel.add(intermediaryLoginInfo);
        mainPanel.add(intermediaryButtonPanel);
        mainPanel.add(wrongCredentialsPanel);
        mainPanel.add(registrationPanel);

        passwordField.addKeyListener(new KeyAdapter() {
                                         @Override
                                         public void keyReleased(KeyEvent e) {
                                             if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                                                 final boolean successLogin = login(usernameTextField.getText(), new String(passwordField.getPassword()));
                                                 if (!successLogin) {
                                                     wrongCredentialsLabel.setText(new WrongCredentialsException().getMessage());
                                                 }
                                             }
                                         }
                                     }
        );
        return mainPanel;
    }

    private static boolean login(String username, String password) {
        try {
            ACCOUNT_SERVICE.getVerifiedAccount(username, password);
            appFrame.setContentPane(MainWindow.createMainWindow());
            appFrame.pack();

            final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            appFrame.setSize(Utils.rescale(screenSize, 0.75));
            appFrame.setMinimumSize(Utils.rescale(screenSize, 0.5));

            appFrame.setLocationRelativeTo(null);
            appFrame.setVisible(true);
            return true;
        } catch (WrongCredentialsException ex) {
            return false;
        }
    }

}
