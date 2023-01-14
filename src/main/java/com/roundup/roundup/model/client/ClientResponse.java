package com.roundup.roundup.model.client;

import java.io.Serializable;
import java.util.List;

/**
 * Base model class used to hold the error details
 */
public class ClientResponse implements Serializable {
    private List<Errors> errors;
    private boolean success;

    public List<Errors> getErrors() {
        return errors;
    }

    public void setErrors(List<Errors> errors) {
        this.errors = errors;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
