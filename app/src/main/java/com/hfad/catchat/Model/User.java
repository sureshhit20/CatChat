package com.hfad.catchat.Model;

public class User {

    private String name;
    private String phone;
    private String place;
    private String age;
    private String disease;
    private String day;

    private String uid;
    private String dr;
    private String count;
    private String buffer;


    private String joiningDate;

    public User(String name, String age,String place,String phone, String disease, String day,String dr){

        this.name = name;
        this.place = place;
        this.phone = phone;
        this.disease = disease;
        this.age = age;
        this.day = day;
        this.dr = dr;


    }

    public User(String name, String phone, String place, String age, String disease, String day, String dr,String uid){

        this.dr = dr;
        this.name = name;
        this.place = place;
        this.phone = phone;
        this.disease = disease;
        this.age = age;
        this.day = day;
        this.uid = uid;

    }

    public User(String count, String name, String place, String phone, String disease, String age, String day,String joiningDate, String buffer){

        this.count = count;
        this.name = name;
        this.place = place;
        this.phone = phone;
        this.disease = disease;
        this.age = age;
        this.day = day;
        this.joiningDate = joiningDate;
        this.buffer = buffer;

    }


    public String getUid() {
        return uid;
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

    public String getDr() {
        return dr;
    }

    public String getCount() {
        return count;
    }

    public String getJoiningDate() {
        return joiningDate;
    }

    public String getBuffer() {
        return buffer;
    }


}
