package com.java.jira.utils;

import com.java.jira.models.Task;

import javax.swing.*;
import java.awt.*;
import java.sql.PseudoColumnUsage;
import java.util.Random;

public class Utils {
    final static Random RANDOM = new Random();

    public static JComponent addBorder(JComponent component) {
        component.setBorder(BorderFactory.createLineBorder(new Color(RANDOM.nextInt(255),RANDOM.nextInt(255),
                RANDOM.nextInt(255)), 3));
        return component;
    }

    public static Dimension rescale(Dimension preference, double factor) {
        final int rescaledWidth = (int) (preference.getWidth() * factor);
        final int rescaledHeight = (int) (preference.getHeight() * factor);
        return new Dimension(rescaledWidth, rescaledHeight);
    }

    public static boolean containsIgnoreCase(String reference, String toFind) {
        return reference.toLowerCase().contains(toFind.toLowerCase());
    }

    public static boolean taskContainsIgnoreCase(Task task, String toFind) {
        return containsIgnoreCase(task.getTitle(), toFind) ||
                containsIgnoreCase(task.getDescription(), toFind);
    }
}
