package com.roundup.roundup.service.impl;

import com.roundup.roundup.model.client.account.Account;
import com.roundup.roundup.model.client.account.AccountResponse;
import com.roundup.roundup.model.client.saving.SavingsGoalResponse;
import com.roundup.roundup.model.client.transaction.Amount;
import com.roundup.roundup.model.client.transaction.FeedItem;
import com.roundup.roundup.model.client.transaction.TransactionResponse;
import com.roundup.roundup.model.dto.AccountDTO;
import com.roundup.roundup.service.SavingsGoalClient;
import com.roundup.roundup.service.TransactionClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RoundUpServiceImplTest {

    private RoundUpServiceImpl roundUpServiceImpl;
    @Mock
    private AccountClientImpl accountClientImpl;
    @Mock
    private TransactionClient transactionClient;
    @Mock
    private SavingsGoalClient savingsGoalClient;

    @Test
    public void roundUpTest(){
        roundUpServiceImpl= new RoundUpServiceImpl(accountClientImpl,transactionClient,savingsGoalClient);

        when(accountClientImpl.getAccounts("Bearer ")).thenReturn(getAccountResponse());
        when(transactionClient.getTransaction("1","1","Bearer ")).thenReturn(getTransactionResponse());
        when(savingsGoalClient.createAndUpdateSavingGoal(anyString(),any(),anyString())).thenReturn(new SavingsGoalResponse());

        SavingsGoalResponse  savingsGoalResponse = roundUpServiceImpl.roundUp("Bearer ");
        assertNotNull(savingsGoalResponse);

    }

    private  List<AccountDTO> getAccountResponse(){

        List<AccountDTO> accounts = new ArrayList<>();
        AccountDTO account = new AccountDTO();
        account.setAccountUid("1");
        account.setCategoryId("1");
        accounts.add(account);

        return accounts;
    }

    private TransactionResponse getTransactionResponse(){
        TransactionResponse response = new TransactionResponse();
        List<FeedItem> feedItems = new ArrayList<>();
        FeedItem feedItem = new FeedItem();
        feedItem.setAmount(getAmount());
        feedItem.setCategoryUid("1");
        feedItem.setFeedItemUid("1");
        feedItems.add(feedItem);
        response.setFeedItems(feedItems);
        return response;
    }

    private Amount getAmount(){
        Amount amount = new Amount();
        BigDecimal minorUnit=   new BigDecimal(1.54);
        amount.setMinorUnits(minorUnit);
        amount.setCurrency("GBP");
        return amount;
    }

}