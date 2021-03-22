package com.java.jira.component;

public enum FilterOption {

    ALL("All"),
    HIGH_PRIORITY("High priority"),
    IN_REVIEW("In review"),
    DONE("Done");


    private String filterText;

    FilterOption(String filterText) {
        this.filterText = filterText;
    }

    public String getFilterText() {
        return filterText;
    }
}
