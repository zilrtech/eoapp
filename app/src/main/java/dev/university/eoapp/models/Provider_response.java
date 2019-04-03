package dev.university.eoapp.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by mujahid on 3/14/2019.
 */

public class Provider_response {

    @SerializedName("response")

    private Boolean response;
    @SerializedName("providers")

    private List<Provider> providers = null;

    public Boolean getResponse() {
        return response;
    }

    public void setResponse(Boolean response) {
        this.response = response;
    }

    public List<Provider> getProviders() {
        return providers;
    }

    public void setProviders(List<Provider> providers) {
        this.providers = providers;
    }

}