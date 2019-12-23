package com.pani.tictactoe.restclient;

import com.google.gson.annotations.SerializedName;

public class BaseResponseObject {
    @SerializedName("success")
    private boolean success;
    @SerializedName("message")
    private String message;

    public BaseResponseObject() {
    }

    public BaseResponseObject(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
