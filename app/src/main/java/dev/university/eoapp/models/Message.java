package dev.university.eoapp.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by mujahid on 3/15/2019.
 */

public class Message {

    @SerializedName("id")

    private String id;
    @SerializedName("offer_id")

    private String offerId;
    @SerializedName("isClient")

    private String isClient;
    @SerializedName("message")

    private String message;
    @SerializedName("created_at")

    private String createdAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOfferId() {
        return offerId;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }

    public String getIsClient() {
        return isClient;
    }

    public void setIsClient(String isClient) {
        this.isClient = isClient;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

}