package dev.university.eoapp.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by mujahid on 3/14/2019.
 */

public class offers_response {

    @SerializedName("response")

    private Boolean response;
    @SerializedName("offers")

    private List<Offer> offers = null;

    public Boolean getResponse() {
        return response;
    }

    public void setResponse(Boolean response) {
        this.response = response;
    }

    public List<Offer> getOffers() {
        return offers;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }

}