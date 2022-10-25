package com.fit5136.entities;

import java.util.List;

// customer entity class
public class Customer {

    // customer attribute    
    private String monashEmail;
    private String password;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String gender;
    private String phoneNumber;
    private String address;


    private List<AdForItem> postAd;
    private List<AdForItem> recentlyViewedAd;
    private List<AdForItem> watchlistAd;

    // constructor 
    public Customer(String monashEmail, String password) {
        this.monashEmail = monashEmail;
        this.password = password;
    }

    // get or Set methods
    public String getMonashEmail() {
        return monashEmail;
    }

    public void setMonashEmail(String monashEmail) {
        this.monashEmail = monashEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<AdForItem> getPostAd() {
        return postAd;
    }

    public void setPostAd(List<AdForItem> postAd) {
        this.postAd = postAd;
    }

    public List<AdForItem> getRecentlyViewedAd() {
        return recentlyViewedAd;
    }

    public void setRecentlyViewedAd(List<AdForItem> recentlyViewedAd) {
        this.recentlyViewedAd = recentlyViewedAd;
    }

    public List<AdForItem> getWatchlistAd() {
        return watchlistAd;
    }

    public void setWatchlistAd(List<AdForItem> watchlistAd) {
        this.watchlistAd = watchlistAd;
    }
}
