package com.java.jira.repository;

import com.java.jira.models.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public enum AccountRepository {
    ACCOUNT_REPOSITORY;


    private static final String GET_VERIFIED_ACCOUNT = "SELECT * FROM accounts WHERE username= ? AND password = ?";

    public Optional<Account> getVerifiedAccount(Connection connection, String username, String password) throws SQLException {
        Account verifiedAccount = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_VERIFIED_ACCOUNT)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            final ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                verifiedAccount = mapRowToAccount(resultSet);
            }
        }
        return Optional.ofNullable(verifiedAccount);
    }

    private Account mapRowToAccount(ResultSet resultSet) throws SQLException {
        final int accountID = resultSet.getInt(1);
        final String accountUsername = resultSet.getString(2);
        final String accountName = resultSet.getString(4);
        final String accountEmail = resultSet.getString(5);
        final String accountType = resultSet.getString(6);
        return Account.builder()
                .id(accountID)
                .username(accountUsername)
                .password("************")
                .name(accountName)
                .email(accountEmail)
                .type(accountType)
                .build();
    }

}
