package com.hfad.catchat.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Contact {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("place")
        @Expose
        private String place;
        @SerializedName("phone")
        @Expose
        private String phone;
        @SerializedName("id")
        private String id;

        public Contact(String name, String place, String phone, String id) {
            this.name = name;
            this.place = place;
            this.phone = phone;
            this.id = id;
        }


        public String getName() {
            return name;
        }
        public String getId() {return id; }
        public String getPlace() {
            return place;
        }
        public String getPhone() {
            return phone;
        }
    }

