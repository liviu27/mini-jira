package com.java.jira.component;

public enum MenuOption {

    BACKLOG("BACKLOG"),
    BOARD_VIEW("BOARD VIEW"),
    LOG_OUT("LOG OUT");

    private String menuText;

    MenuOption(String menuText) {
        this.menuText = menuText;
    }
}
