package com.example.ims;

public class model
{
    String Work,Email,name,pimage,Uid,contact;
    Double latitude,longitude;
    float rating1;

    public model() {
    }

    public model(String Uid,String Work, String Email, String name,String contact, String pimage,Double latitude,Double longitude,float rating1) {
        this.Uid = Uid;
        this.Work = Work;
        this.Email = Email;
        this.name = name;
        this.contact = contact;
        this.pimage = pimage;
        this.latitude= latitude;
        this.longitude = longitude;
    }

    public float getRating1() {
        return rating1;
    }

    public void setRating1(float rating1) {
        this.rating1 = rating1;
    }

    public String getUid() {
        return Uid;
    }

    public void setUid(String Uid) {
        this.Uid = Uid;
    }


    public String getWork() {
        return Work;
    }

    public void setWork(String Work) {
        this.Work = Work;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getName() {
        return name;
    }

    public void setcontact(String contact) {
        this.contact = contact;
    }

    public String getcontact() {
        return contact;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getpimage() {
        return pimage;
    }

    public void setpimage(String pimage) {
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
