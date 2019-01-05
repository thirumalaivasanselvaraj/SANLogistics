package com.saneforce.logistics.API_interface;

import com.google.gson.annotations.SerializedName;

public class ServerResponse {
    // variable name should be same as in the json response from php    @SerializedName("success")
   public boolean success;
    @SerializedName("message")
    String message;

    public String getMessage() {
        return message;
    }

    public boolean getSuccess() {
        return success;
    }
}