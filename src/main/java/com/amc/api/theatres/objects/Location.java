
package com.amc.api.theatres.objects;

public class Location {

    private String addressLine1;
    private String city;
    private String postalCode;
    private String state;
    private String stateName;
    private String country;
    private Integer latitude;
    private Integer longitude;
    private String directionsUrl;
    private String marketName;
    private String addressLine2;
    private String cityUrlSuffixText;
    private String stateUrlSuffixText;
    private String marketUrlSuffixText;

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getLatitude() {
        return latitude;
    }

    public void setLatitude(Integer latitude) {
        this.latitude = latitude;
    }

    public Integer getLongitude() {
        return longitude;
    }

    public void setLongitude(Integer longitude) {
        this.longitude = longitude;
    }

    public String getDirectionsUrl() {
        return directionsUrl;
    }

    public void setDirectionsUrl(String directionsUrl) {
        this.directionsUrl = directionsUrl;
    }

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getCityUrlSuffixText() {
        return cityUrlSuffixText;
    }

    public void setCityUrlSuffixText(String cityUrlSuffixText) {
        this.cityUrlSuffixText = cityUrlSuffixText;
    }

    public String getStateUrlSuffixText() {
        return stateUrlSuffixText;
    }

    public void setStateUrlSuffixText(String stateUrlSuffixText) {
        this.stateUrlSuffixText = stateUrlSuffixText;
    }

    public String getMarketUrlSuffixText() {
        return marketUrlSuffixText;
    }

    public void setMarketUrlSuffixText(String marketUrlSuffixText) {
        this.marketUrlSuffixText = marketUrlSuffixText;
    }

}
