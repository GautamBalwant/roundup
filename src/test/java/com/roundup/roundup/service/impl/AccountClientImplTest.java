package com.roundup.roundup.service.impl;

import com.roundup.roundup.model.client.account.Account;
import com.roundup.roundup.model.client.account.AccountResponse;
import com.roundup.roundup.model.dto.AccountDTO;
import com.roundup.roundup.service.StarlingRestClient;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyObject;
import static  org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AccountClientImplTest {

    private AccountClientImpl accountClient;

    @Mock
    private StarlingRestClientImpl starlingRestClient;

    @Mock
    ResponseEntity<Object> response;

    @Test
    public void getAccountsTest(){
        accountClient= new AccountClientImpl(starlingRestClient);
        when(starlingRestClient.getCall(any(),any(),any(),any())).thenReturn(response);
        when(response.getBody()).thenReturn(getAccountResponse());
        List<AccountDTO> responseList=  accountClient.getAccounts("test");
    }

    private AccountResponse getAccountResponse(){
        AccountResponse accountResponse =   new AccountResponse();
        List<Account> accounts = new ArrayList<>();
        Account account = new Account();
        account.setAccountUid("1");
        account.setDefaultCategory("1");
        accounts.add(account);
        accountResponse.setAccounts(accounts);
        return accountResponse;
    }

}