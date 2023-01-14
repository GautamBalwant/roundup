package com.roundup.roundup.model.client;

import java.io.Serializable;

/**
 * Class to hold the error message
 */
public class Errors implements Serializable {

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
