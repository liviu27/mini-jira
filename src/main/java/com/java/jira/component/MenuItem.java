package com.java.jira.component;

import lombok.SneakyThrows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class MenuItem extends JPanel {

    private static final Dimension DIMENSION = new Dimension(270, 50);
    private static final Color HOVER_BACKGROUND_COLOR = new Color(211, 211, 211);
    private static final List<MenuItem> MENU_ITEMS = new ArrayList<>();

    private static Color defaultForegroundColor;
    private static Color defaultBackgroundColor;

    private boolean isClicked;
    private String name;
    private JLabel menuItemLabel;

    public MenuItem(String name) {
        super(new FlowLayout(FlowLayout.LEADING));
        this.name = name;
        setPreferredSize(DIMENSION);
        setBorder(BorderFactory.createEmptyBorder(9, 0, 0, 0));
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        menuItemLabel = new JLabel(name);

        defaultBackgroundColor = getBackground();
        defaultForegroundColor = getForeground();

        menuItemLabel.setFont(menuItemLabel.getFont().deriveFont(Font.BOLD, 16));
        add(menuItemLabel);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MENU_ITEMS.stream().parallel().forEach(b -> b.setSelected(b == MenuItem.this));
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (!isClicked) {
                    setBackground(HOVER_BACKGROUND_COLOR);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!isClicked) {
                    setBackground(defaultBackgroundColor);
                }
            }
        });
        MENU_ITEMS.add(this);
    }

    @SneakyThrows
    private void setSelected(boolean isClicked) {
        this.isClicked = isClicked;
        if (isClicked) {
            setBackground(HOVER_BACKGROUND_COLOR);
            menuItemLabel.setForeground(Color.BLUE);
        } else {
            setBackground(defaultBackgroundColor);
            menuItemLabel.setForeground(defaultForegroundColor);
        }
    }
}
