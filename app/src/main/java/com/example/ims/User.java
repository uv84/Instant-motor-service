package com.example.ims;

class User {
    String displayname;


    String email,contact;
    long createdAt;

    public User (){};
    public User(String displayname,String email,String contact,long createdAt){
        this.displayname=displayname;
        this.email=email;
        this.contact= contact;
        this.createdAt=createdAt;
    }



    public String getdisplayname() {
        return displayname;
    }

    public String getemail() {
        return email;
    }
    public String getcontact() {
        return contact;
    }

    public long getCreatedAt() {
        return createdAt;
    }

}
