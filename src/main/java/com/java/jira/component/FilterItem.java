package com.java.jira.component;

import lombok.SneakyThrows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FilterItem extends JPanel {

    private static final List<FilterItem> FILTER_ITEMS = new ArrayList<>();

    private static Font defaultFont;

    private boolean isClicked;
    private String name;
    private JLabel filterItemLabel;

    public FilterItem(String name) {
        super(new FlowLayout());
        this.name = name;
        filterItemLabel = new JLabel(name);
        setPreferredSize(new Dimension((name.length() + 1) * 8, 25));
        setBorder(BorderFactory.createEmptyBorder(-2, 0, 0, 0));
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        setBackground(Color.WHITE);

        defaultFont = this.getFont().deriveFont(Font.BOLD, 14);
        filterItemLabel.setFont(defaultFont);
        filterItemLabel.setForeground(Color.BLUE);
        add(filterItemLabel);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                FILTER_ITEMS.stream().parallel().forEach(option -> option.setSelected(option == FilterItem.this));
            }
        });
        FILTER_ITEMS.add(this);
    }


    @SneakyThrows
    private void setSelected(boolean isClicked) {
        this.isClicked = isClicked;
        if (isClicked) {
            Map attributes = defaultFont.getAttributes();
            attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
            filterItemLabel.setFont(defaultFont.deriveFont(attributes));
        } else {
            filterItemLabel.setFont(defaultFont);
        }
    }

}
