package com.java.jira.utils;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Utils {
    final static Random RANDOM = new Random();

    public static JComponent addBorder(JComponent component) {
        component.setBorder(BorderFactory.createLineBorder(new Color(RANDOM.nextInt(255),RANDOM.nextInt(255),
                RANDOM.nextInt(255)), 3));
        return component;
    }

}
