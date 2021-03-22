package com.java.jira.exceptions.business;

public class WrongCredentialsException extends Throwable{
    public WrongCredentialsException() {
        super("Incorrect credentials. Please try again or sign up!");
    }
}
