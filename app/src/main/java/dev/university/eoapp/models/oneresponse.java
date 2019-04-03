package dev.university.eoapp.models;

public class oneresponse {


    private String  details, response_date;

    public oneresponse(String details, String response_date) {
        this.details = details;
        this.response_date = response_date;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getResponse_date() {
        return response_date;
    }

    public void setResponse_date(String response_date) {
        this.response_date = response_date;
    }
}
