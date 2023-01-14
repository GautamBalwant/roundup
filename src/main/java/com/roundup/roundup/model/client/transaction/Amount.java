package com.roundup.roundup.model.client.transaction;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * This class used to hold the amount details (i.e currency and value)
 */
public class Amount implements Serializable {
    private String currency;
    private BigDecimal minorUnits;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getMinorUnits() {
        return minorUnits;
    }

    public void setMinorUnits(BigDecimal minorUnits) {
        this.minorUnits = minorUnits;
    }
}
