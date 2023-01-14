package com.roundup.roundup.model.client.account;

import com.roundup.roundup.model.client.ClientResponse;

import java.util.List;

/**
 * The class is used to hold the list of Account details.
 * It is used in the fetch call details calls
 */
public class AccountResponse extends ClientResponse {
    private List<Account> accounts;

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}
