package com.hfad.catchat.Model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserExtra {

    @SerializedName("count")
    @Expose
    private String count;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("place")
    @Expose
    private String place;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("disease")
    @Expose
    private String disease;
    @SerializedName("age")
    @Expose
    private String age;
    @SerializedName("day")
    @Expose
    private String day;

    public UserExtra(String count, String name, String place, String phone, String disease, String age, String day){

        this.count = count;
        this.name = name;
        this.place = place;
        this.phone = phone;
        this.disease = disease;
        this.age = age;
        this.day = day;

    }

    public String getName() {
        return name;
    }

    public String getDay() {
        return day;
    }

    public String getPlace() {
        return place;
    }

    public String getPhone() {
        return phone;
    }

    public String getDisease() {
        return disease;
    }

    public String getAge() {
        return age;
    }

    public String getCount() {
        return count;
    }


}

