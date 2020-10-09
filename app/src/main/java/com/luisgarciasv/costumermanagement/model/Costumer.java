package com.luisgarciasv.costumermanagement.model;

final public class Costumer {

    private String name;
    private String lastName;
    private String dui;
    private String nit;
    private String address;
    private String phone;
    private String email;

    public Costumer(String name, String lastName, String dui, String nit, String address, String phone, String email) {
        this.name = name;
        this.lastName = lastName;
        this.dui = dui;
        this.nit = nit;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDui() {
        return dui;
    }

    public String getNit() {
        return nit;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

}
