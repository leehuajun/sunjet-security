package com.sunjet.sunjetsecuritydemo.exception;

public class UserNotExistException extends RuntimeException {

    private static final long serialVersionUID = 2614710697946965279L;

    private String id;

    public UserNotExistException(String id) {
        super("user not exists.");
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
