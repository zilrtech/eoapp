package dev.university.eoapp.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by mujahid on 3/14/2019.
 */

public class category_response {

    @SerializedName("response")

    private Boolean response;
    @SerializedName("categories")

    private List<Category> categories = null;

    public Boolean getResponse() {
        return response;
    }

    public void setResponse(Boolean response) {
        this.response = response;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

}