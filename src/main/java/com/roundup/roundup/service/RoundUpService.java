package com.roundup.roundup.service;

import com.roundup.roundup.model.client.saving.SavingsGoalResponse;

public interface RoundUpService {

    SavingsGoalResponse roundUp(final String authorization);
}
