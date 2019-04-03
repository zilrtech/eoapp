package dev.university.eoapp.models;

import java.util.List;

public class RequetsResponse {

    private boolean error;
    private List<request> requests;

    public RequetsResponse(boolean error, List<request> users) {
        this.error = error;
        this.requests = users;
    }

    public boolean isError() {
        return error;
    }

    public List<request> getRequests() {
        return requests;
    }
}
