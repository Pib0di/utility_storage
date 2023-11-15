package com.thewhite.utilitystorage.exception;

public class BadInputDataForRating extends IllegalArgumentException {

    public BadInputDataForRating(String error) {
        super(error);
    }

    public String getError() {
        return super.getMessage();
    }
}
