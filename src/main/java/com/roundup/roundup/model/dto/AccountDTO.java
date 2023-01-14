package com.roundup.roundup.model.dto;

import java.io.Serializable;

/**
 * Contains the account id and category id details
 */
public class AccountDTO implements Serializable {
    private String accountUid;
    private String categoryId;

    public String getAccountUid() {
        return accountUid;
    }

    public void setAccountUid(String accountUid) {
        this.accountUid = accountUid;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
}
