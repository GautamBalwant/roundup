package com.roundup.roundup.service.impl;

import com.roundup.roundup.model.client.account.Account;
import com.roundup.roundup.model.client.account.AccountResponse;
import com.roundup.roundup.model.dto.AccountDTO;
import com.roundup.roundup.service.AccountClient;
import com.roundup.roundup.service.StarlingRestClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * This class is used for the Account details related calls.
 */
@Service
public class AccountClientImpl implements AccountClient {
    private StarlingRestClient starlingRestClient;

    @Value("${accounts.uri}")
    private String accountUri;

    public AccountClientImpl(StarlingRestClient starlingRestClient) {
        this.starlingRestClient = starlingRestClient;
    }


    /**
     * The method is used to fetch the account details of a customer
     *
     * @param authorization of type {@link String}
     * @return response list of type {@link com.roundup.roundup.model.dto.AccountDTO}
     */
    @Override
    public List<AccountDTO> getAccounts(final String authorization) {
        ResponseEntity<AccountResponse> res = starlingRestClient.getCall(accountUri,
                new ParameterizedTypeReference<AccountResponse>() {}, authorization, new HashMap<>());
        AccountResponse accountResponse = res.getBody();
        final List<Account> accounts;
        List<AccountDTO> response = new ArrayList<>();
        if (accountResponse != null) {
            accounts = accountResponse.getAccounts();

            for (Account account : accounts) {
                AccountDTO accountDTO = new AccountDTO();
                accountDTO.setCategoryId(account.getDefaultCategory());
                accountDTO.setAccountUid(account.getAccountUid());
                response.add(accountDTO);
            }
        }

        return response;
    }
}
