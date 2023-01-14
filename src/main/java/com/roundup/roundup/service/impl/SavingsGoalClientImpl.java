package com.roundup.roundup.service.impl;

import com.roundup.roundup.model.client.saving.SavingsGoal;
import com.roundup.roundup.model.client.saving.SavingsGoalResponse;
import com.roundup.roundup.model.client.transaction.Amount;
import com.roundup.roundup.service.SavingsGoalClient;
import com.roundup.roundup.service.StarlingRestClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Class is used to create and update the saving goal
 */
@Service
public class SavingsGoalClientImpl implements SavingsGoalClient {

    @Value("${saving.goal.uri.create}")
    private String createUri;

    @Value("${saving.goal.uri.update}")
    private String updateUri;

    @Value("${saving.goal.name}")
    private String savingGoalName;

    @Value("${currency}")
    private String currency;

    private StarlingRestClient starlingRestClient;

    public SavingsGoalClientImpl(StarlingRestClient starlingRestClient) {
        this.starlingRestClient = starlingRestClient;
    }

    /**
     * This method is used to create and update the saving goal.
     * Saving goal is first created and then updated as two different api call to Sandbox
     * <p>
     * NOTE: the round up amount can be saved using the create call but using two Sandbox api calls here
     *
     * @param accountId
     * @param amount
     * @param authorization
     * @return
     */
    @Override
    public SavingsGoalResponse createAndUpdateSavingGoal(final String accountId, final BigDecimal amount, final String authorization) {
        SavingsGoalResponse createResponse = createSavingGoal(accountId, authorization);
        return updateSavingGoal(accountId, createResponse.getSavingsGoalUid(), amount, authorization);
    }

    /**
     * The method is used to create the Saving Goal
     *
     * @param accountId
     * @param authorization
     * @return
     */
    private SavingsGoalResponse createSavingGoal(final String accountId, final String authorization) {
        SavingsGoalResponse response = null;
        response = starlingRestClient.putCall(createUri, getCreateRequestBody(), new ParameterizedTypeReference<SavingsGoalResponse>() {
        }, getUrlParams(accountId), authorization).getBody();
        return response;
    }

    /**
     * The method is used to update the saving goal with the round up amount
     *
     * @param accountId
     * @param savingGoalId
     * @param amount
     * @param authorization
     * @return
     */
    private SavingsGoalResponse updateSavingGoal(final String accountId, final String savingGoalId, final BigDecimal amount, final String authorization) {
        SavingsGoalResponse response = null;
        response = starlingRestClient.putCall(updateUri, getUpdateRequestBody(amount), new ParameterizedTypeReference<SavingsGoalResponse>() {
        }, getUrlParams(accountId, savingGoalId), authorization).getBody();
        return response;
    }

    /**
     * Return Request Body for create Saving Goal call
     *
     * @return
     */
    private SavingsGoal getCreateRequestBody() {
        SavingsGoal savingsGoal = new SavingsGoal();
        savingsGoal.setName(savingGoalName);
        savingsGoal.setCurrency(currency);
        return savingsGoal;
    }

    /**
     * Return Request Body for update Saving Goal call
     *
     * @return
     */
    private SavingsGoal getUpdateRequestBody(final BigDecimal amount) {
        SavingsGoal savingsGoal = getCreateRequestBody();
        if (amount != null) {
            Amount roundUpAmount = new Amount();
            roundUpAmount.setCurrency(currency);
            roundUpAmount.setMinorUnits(amount);
            savingsGoal.setTarget(roundUpAmount);
        }
        return savingsGoal;
    }

    /**
     * Returns request Param for Create api call
     *
     * @param accountId
     * @return
     */
    private Map<String, String> getUrlParams(final String accountId) {
        Map<String, String> urlParams = new HashMap<String, String>();
        urlParams.put("accountUid", accountId);
        return urlParams;
    }

    /**
     * Return request param for update api call
     *
     * @param accountId
     * @param savingGoalId
     * @return
     */
    private Map<String, String> getUrlParams(final String accountId, final String savingGoalId) {
        Map<String, String> urlParams = getUrlParams(accountId);
        urlParams.put("savingsGoalUid", savingGoalId);
        return urlParams;
    }
}
