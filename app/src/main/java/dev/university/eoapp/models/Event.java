package dev.university.eoapp.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by mujahid on 3/15/2019.
 */

public class Event {

    @SerializedName("id")

    private String id;
    @SerializedName("client_id")

    private String clientId;
    @SerializedName("gender")

    private String gender;
    @SerializedName("party_type")

    private String partyType;
    @SerializedName("name")

    private String name;
    @SerializedName("number_of_guest")

    private String numberOfGuest;
    @SerializedName("date")

    private String date;
    @SerializedName("party_location")

    private String partyLocation;
    @SerializedName("budget")

    private String budget;
    @SerializedName("idea")

    private String idea;
    @SerializedName("created_at")

    private String createdAt;
    @SerializedName("accepted_offer")

    private Object acceptedOffer;
    @SerializedName("isAccept")

    private String isAccept;


    @SerializedName("offer_count")

    private String offer_count;

    public String getOffer_count() {
        return offer_count;
    }

    public void setOffer_count(String offer_count) {
        this.offer_count = offer_count;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPartyType() {
        return partyType;
    }

    public void setPartyType(String partyType) {
        this.partyType = partyType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumberOfGuest() {
        return numberOfGuest;
    }

    public void setNumberOfGuest(String numberOfGuest) {
        this.numberOfGuest = numberOfGuest;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPartyLocation() {
        return partyLocation;
    }

    public void setPartyLocation(String partyLocation) {
        this.partyLocation = partyLocation;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public String getIdea() {
        return idea;
    }

    public void setIdea(String idea) {
        this.idea = idea;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Object getAcceptedOffer() {
        return acceptedOffer;
    }

    public void setAcceptedOffer(Object acceptedOffer) {
        this.acceptedOffer = acceptedOffer;
    }

    public String getIsAccept() {
        return isAccept;
    }

    public void setIsAccept(String isAccept) {
        this.isAccept = isAccept;
    }

}
