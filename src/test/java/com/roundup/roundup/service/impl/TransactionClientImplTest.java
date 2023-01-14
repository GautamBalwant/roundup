package com.roundup.roundup.service.impl;

import com.roundup.roundup.model.client.transaction.Amount;
import com.roundup.roundup.model.client.transaction.FeedItem;
import com.roundup.roundup.model.client.transaction.TransactionResponse;
import com.roundup.roundup.service.StarlingRestClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TransactionClientImplTest {
    private TransactionClientImpl transactionClientImpl;

    @Mock
    private StarlingRestClient starlingRestClient;
    @Mock
    private ResponseEntity<Object> response;

    @Test
    public void getTransactionTest(){
        transactionClientImpl= new TransactionClientImpl(starlingRestClient);
        when(starlingRestClient.getCall(any(),any(),any(),any())).thenReturn(response);
        when(response.getBody()).thenReturn(getTransactionResponse());

        TransactionResponse result= transactionClientImpl.getTransaction("1","1","Bearer ");
        assertNotNull(result);
        assertNotNull(result.getFeedItems());
        assertEquals(1,result.getFeedItems().size());
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