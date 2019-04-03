package dev.university.eoapp.models;

import java.util.List;

public class myResponse {

    private boolean error;
    private List<oneresponse> myresponse;

    public myResponse(boolean error, List<oneresponse> myresponse) {
        this.error = error;
        this.myresponse = myresponse;
    }

    public boolean isError() {
        return error;
    }

    public List<oneresponse> getmyResponse() {
        return myresponse;
    }
}
