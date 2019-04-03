package dev.university.eoapp.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by mujahid on 3/14/2019.
 */

public class Provider {

    @SerializedName("id")

    private String id;
    @SerializedName("email")

    private String email;
    @SerializedName("password")
    private String password;



    @SerializedName("phone_number")
    private String phoneNumber;

    @SerializedName("work")
    private String work;

    @SerializedName("license")
    private String license;


    @SerializedName("name")
    private String name;




    @SerializedName("city")
    private String city;


    @SerializedName("type")
    private String type;
    @SerializedName("created_at")

    private String createdAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getWork() {
        return work;
    }

    public String getLicense() {
        return license;
    }

    public String getName() {
        return name;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public void setName(String name) {
        this.name = name;
    }



}