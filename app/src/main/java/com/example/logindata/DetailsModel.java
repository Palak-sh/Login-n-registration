package com.example.logindata;

public class DetailsModel {
    public String name;
    public String password;
    public String number;
    public String email;
    public String zipcode;

    public DetailsModel(String name, String password, String number, String email, String zipcode){
        this.name = name;
        this.password = password;
        this.number = number;
        this.email = email;
        this.zipcode = zipcode;
    }

}
