package com.roundup.roundup.model.client.saving;

import com.roundup.roundup.model.client.ClientResponse;

/**
 * The class is used to hold the response from the savings goal endpoint.
 */
public class SavingsGoalResponse extends ClientResponse {

    private String savingsGoalUid;

    public String getSavingsGoalUid() {
        return savingsGoalUid;
    }

    public void setSavingsGoalUid(String savingsGoalUid) {
        this.savingsGoalUid = savingsGoalUid;
    }

}
