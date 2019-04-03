package dev.university.eoapp.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by mujahid on 3/14/2019.
 */

public class Category {

    @SerializedName("id")

    private String id;
    @SerializedName("name")

    private String name;
    @SerializedName("created_at")

    private String image;
    @SerializedName("image")

    private String createdAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

}