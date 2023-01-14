package com.roundup.roundup.service.impl;

import com.roundup.roundup.model.client.saving.SavingsGoalResponse;
import com.roundup.roundup.service.StarlingRestClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SavingsGoalClientImplTest {

    private SavingsGoalClientImpl savingsGoalClientImpl;

    @Mock
    private StarlingRestClient starlingRestClient;

    @Mock
    ResponseEntity<Object> response;

    @Test
    public void createAndUpdateSavingGoalTest(){
        SavingsGoalResponse savingsGoalResponse =new SavingsGoalResponse();
        savingsGoalResponse.setSavingsGoalUid("1");
        savingsGoalClientImpl = new SavingsGoalClientImpl(starlingRestClient);
        when(starlingRestClient.putCall(any(),any(),any(),any(),any())).thenReturn(response);
        when(response.getBody()).thenReturn(savingsGoalResponse);

        SavingsGoalResponse response = savingsGoalClientImpl.createAndUpdateSavingGoal("1", BigDecimal.ONE,"Bearer ");
        assertEquals(response.getSavingsGoalUid(),"1");
    }



}