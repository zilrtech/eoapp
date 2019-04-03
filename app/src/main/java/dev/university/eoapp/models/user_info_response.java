package dev.university.eoapp.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by mujahid on 3/26/2019.
 */

public class user_info_response {

    @SerializedName("response")

    private Boolean response;
    @SerializedName("user")

    private user_info user;




    public Boolean getResponse() {
        return response;
    }

    public void setResponse(Boolean response) {
        this.response = response;
    }

    public user_info getUser() {
        return user;
    }

    public void setUser(user_info user) {
        this.user = user;
    }
}