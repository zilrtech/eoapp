package dev.university.eoapp.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by mujahid on 3/15/2019.
 */

public class chat_mesage_response {

    @SerializedName("response")

    private Boolean response;
    @SerializedName("messages")

    private List<Message> messages = null;

    public Boolean getResponse() {
        return response;
    }

    public void setResponse(Boolean response) {
        this.response = response;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

}