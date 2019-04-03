package dev.university.eoapp.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by mujahid on 3/14/2019.
 */
public class data {

    @SerializedName("id")
    private String userId;

    @SerializedName("firstname")

    private String firstname;
    @SerializedName("lastname")

    private String lastname;

    @SerializedName("type")

    private String type;

    public data(String userId, String firstname, String lastname, String type, String createdAt) {
        this.userId = userId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.type = type;
        this.createdAt = createdAt;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public data(String userId, String type) {
        this.userId = userId;
        this.type = type;
    }

    @SerializedName("created_at")



    private String createdAt;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

}