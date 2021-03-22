package com.java.jira.service;

import com.java.jira.exceptions.business.WrongCredentialsException;
import com.java.jira.exceptions.technical.DatabaseConnectionException;
import com.java.jira.models.Account;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.NoSuchElementException;
import java.util.Optional;

import static com.java.jira.database.MySqlConnection.DATA_SOURCE;
import static com.java.jira.repository.AccountRepository.ACCOUNT_REPOSITORY;

public enum AccountService {

    ACCOUNT_SERVICE;

    private Account currentLoggedAccount;

    public void getVerifiedAccount(String username, String password) throws WrongCredentialsException {

        try (Connection connection = DATA_SOURCE.getConnection()) {
            final Optional<Account> optionalAccount = ACCOUNT_REPOSITORY.getVerifiedAccount(connection, username, password);
            currentLoggedAccount = optionalAccount.orElseThrow();
        } catch (SQLException exception) {
            throw new DatabaseConnectionException();
        } catch (NoSuchElementException exception) {
            throw new WrongCredentialsException();
        }
    }

    public Account getCurrentLoggedAccount() {
        return currentLoggedAccount;
    }
}
