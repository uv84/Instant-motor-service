package com.example.ims;

public class Mechanic {


    public String name, contact,email;
    public Double longitude, latitude;

    public Mechanic(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Mechanic(String fname, String email, Double latitude, Double longitude, String phone) {
        this.name = fname;
        this.email = email;
        this.contact = phone;
        this.latitude = latitude;
        this.longitude = longitude;
    }


}
