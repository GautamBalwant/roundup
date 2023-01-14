package com.roundup.roundup.service;

import com.roundup.roundup.model.client.saving.SavingsGoalResponse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public interface SavingsGoalClient {

    SavingsGoalResponse createAndUpdateSavingGoal(final String accountId, final BigDecimal amount, final String authorization);
}
