package dev.university.eoapp.models;

import com.google.gson.annotations.SerializedName;

public class addnewoferrmodel {

    private boolean response;
    private String message;
//    @SerializedName("data")
//    private data data;

    public addnewoferrmodel(boolean response, String message) {
        this.response = response;
        this.message = message;
//        this.data = subdata;
    }

    public boolean getResponse() {
        return response;
    }

    public String getMessage() {
        return message;
    }

  //  public data getData() {
//        return data;
//    }


}
