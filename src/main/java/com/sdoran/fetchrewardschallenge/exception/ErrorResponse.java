package com.sdoran.fetchrewardschallenge.exception;

import java.util.List;

public class ErrorResponse {

    private String message;
    private List<String> details;

    public ErrorResponse(final String message,
                         final List<String> details) {
        super();
        this.message = message;
        this.details = details;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public List<String> getDetails() {
        return details;
    }

    public void setDetails(final List<String> details) {
        this.details = details;
    }
}