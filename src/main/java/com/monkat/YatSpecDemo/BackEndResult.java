package com.monkat.YatSpecDemo;

public class BackEndResult {

    private String message;
    private String id;

    public BackEndResult(final String id, final String message) {
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }


}
