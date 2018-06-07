package com.hfad.catchat.Model;

import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("success")
    private Boolean success;
    @SerializedName("message")
    private String message;
    //@SerializedName("user")
    //private User user;


    public Result(Boolean error, String message) {
        this.success = error;
        this.message = message;
        //this.user = user;
    }


    public Boolean getError() {
        return success;
    }

    public String getMessage() {
        return message;
    }

}
