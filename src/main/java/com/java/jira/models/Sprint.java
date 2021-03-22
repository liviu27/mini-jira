package com.java.jira.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class Sprint {

    int id;
    String name;
    String acronym;

}
