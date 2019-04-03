package dev.university.eoapp.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by mujahid on 3/15/2019.
 */

public class event_details {

    @SerializedName("response")

    private Boolean response;
    @SerializedName("event")

    private Event event;

    public Boolean getResponse() {
        return response;
    }

    public void setResponse(Boolean response) {
        this.response = response;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

}