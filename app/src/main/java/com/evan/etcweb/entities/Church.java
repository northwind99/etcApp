package com.evan.etcweb.entities;

import java.util.ArrayList;


public class Church {

    int id;
    String name;
    String description;
    String address1;
    String address2;
    String city;
    String postalCode;
    String province;
    double latitude;
    double longitude;
    String phone;
    String fax;
    String website;
    ArrayList<String> serviceTime = new ArrayList<>();
    ArrayList<Contact> contacts = new ArrayList<>();

    public Church(int id, String name, String description, String address1, String address2, String city, String postalCode, String province, double latitude, double longitude, String phone, String fax, String website,ArrayList<String> serviceTime, ArrayList<Contact> contacts) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
        this.postalCode = postalCode;
        this.province = province;
        this.latitude = latitude;
        this.longitude = longitude;
        this.phone = phone;
        this.fax = fax;
        this.website = website;
        this.serviceTime = serviceTime;
        this.contacts = contacts;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public ArrayList<String> getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(ArrayList<String> serviceTime) {
        this.serviceTime = serviceTime;
    }

    public ArrayList<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(ArrayList<Contact> contacts) {
        this.contacts = contacts;
    }
}
