package com.hfad.catchat.Model;

public class User {

    private String dr;
    private String name;
    private String place;
    private String phone;
    private String disease;
    private int age;
    private String day;

    public User(String dr, String name, String place, String phone, String disease, int age, String day){

        this.dr = dr;
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

    public int getAge() {
        return age;
    }

    public String getDr() {
        return dr;
    }

}
