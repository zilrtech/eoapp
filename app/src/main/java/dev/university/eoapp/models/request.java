package dev.university.eoapp.models;

public class request {

    private int id;
    private String title, details, request_date,seen;

    public request(int id, String title, String details, String request_date, String seen) {
        this.id = id;
        this.title = title;
        this.details = details;
        this.request_date = request_date;
        this.seen = seen;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDetails() {
        return details;
    }

    public String getRequest_date() {
        return request_date;
    }

    public String getseen() {
        return seen;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setRequest_date(String request_date) {
        this.request_date = request_date;
    }


}
