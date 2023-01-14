package com.roundup.roundup.model.client.saving;

import com.roundup.roundup.model.client.transaction.Amount;

import java.io.Serializable;

/**
 * This class is used to hold the saving goal details.
 */
public class SavingsGoal implements Serializable {
    private String name;
    private String currency;
    private Amount target;

    public Amount getTarget() {
        return target;
    }

    public void setTarget(Amount target) {
        this.target = target;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
