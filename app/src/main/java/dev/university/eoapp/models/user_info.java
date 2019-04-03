package dev.university.eoapp.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by mujahid on 3/26/2019.
 */

public class user_info {



    @SerializedName("id")

    private String id;
    @SerializedName("email")

    private String email;
    @SerializedName("password")

    private String password;
    @SerializedName("phone_number")

    private String phoneNumber;
    @SerializedName("city")

    private String city;
    @SerializedName("type")

    private String type;
    @SerializedName("created_at")

    private String createdAt;
    @SerializedName("work")

    private String work;
    @SerializedName("name")

    private String name;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLasttname() {
        return lasttname;
    }

    public void setLasttname(String lasttname) {
        this.lasttname = lasttname;
    }

    @SerializedName("firstname")


    private String firstname;

    @SerializedName("lasttname")

    private String lasttname;

    @SerializedName("license")

    private String license;

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

    public void setWork(String work) {
        this.work = work;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }
}