package com.example.ims;

public class dataholder
{
    String name,contact,work,pimage,email;
    Double latitude,longitude;

    public dataholder() {
    }

    public dataholder(String name, String contact, String work, String email, String pimage, Double latitude, Double longitude) {
        this.name = name;
        this.contact = contact;
        this.work = work;
        this.email = email;
        this.pimage = pimage;
        this.latitude= latitude;
        this.longitude = longitude;
    }

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

    public String getwork() {
        return work;
    }

    public void setwork(String course) {
        this.work = work;
    }
    public String getemail() {
        return email;
    }

    public void setemail(String email) {
        this.email = email;
    }

    public String getPimage() {
        return pimage;
    }

    public void setPimage(String pimage) {
        this.pimage = pimage;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
