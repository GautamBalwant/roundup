package com.roundup.roundup.model.client.transaction;

import java.io.Serializable;

/**
 * This class contains the round up details
 */
public class RoundUp implements Serializable {
    private String goalCategoryUid;
    private Amount amount;

    public String getGoalCategoryUid() {
        return goalCategoryUid;
    }

    public void setGoalCategoryUid(String goalCategoryUid) {
        this.goalCategoryUid = goalCategoryUid;
    }

    public Amount getAmount() {
        return amount;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
    }
}
