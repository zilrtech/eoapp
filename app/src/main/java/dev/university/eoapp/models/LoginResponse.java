package dev.university.eoapp.models;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    private boolean error;
    private String message;
    @SerializedName("data")
    private data data;

    public LoginResponse(boolean error, String message, data subdata) {
        this.error = error;
        this.message = message;
        this.data = subdata;
    }

    public boolean isError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public data getData() {
        return data;
    }


}
