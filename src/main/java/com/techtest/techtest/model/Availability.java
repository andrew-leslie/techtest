package com.techtest.techtest.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Availability {

    @JsonProperty("has_available_tickets")
    private boolean isAvailable;

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
