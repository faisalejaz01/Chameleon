
package com.amc.api.theatres.objects;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Theatre {

    private Integer id;
    private String longName;
    private String name;
    private String showtimesPhoneNumber;
    private String guestServicesPhoneNumber;
    private String timezone;
    private String slug;
    private String facebookUrl;
    private String websiteUrl;
    private String ticketable;
    private List<Attribute> attributes = null;
    private Location location;
    private Media media;
    @JsonProperty("_links")
    private Links links;
    private List<String> redemptionMethods = null;
    private List<String> concessionsDeliveryOptions = null;
    private Double convenienceFeeTaxPercent;
    private Integer convenienceFeeTaxFlatAmount;
    private String brand;
    private String westWorldMediaNumber;
    private String loyaltyVersionId;
    private String onlineConcessions;
    private String deliveryToSeat;
    private String utcOffset;

    public Integer getId() {
        return id;
    }

    public String getUtcOffset() {
        return utcOffset;
    }

    public void setUtcOffset(String utcOffset) {
        this.utcOffset = utcOffset;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLongName() {
        return longName;
    }

    public void setLongName(String longName) {
        this.longName = longName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShowtimesPhoneNumber() {
        return showtimesPhoneNumber;
    }

    public void setShowtimesPhoneNumber(String showtimesPhoneNumber) {
        this.showtimesPhoneNumber = showtimesPhoneNumber;
    }

    public String getGuestServicesPhoneNumber() {
        return guestServicesPhoneNumber;
    }

    public void setGuestServicesPhoneNumber(String guestServicesPhoneNumber) {
        this.guestServicesPhoneNumber = guestServicesPhoneNumber;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getFacebookUrl() {
        return facebookUrl;
    }

    public void setFacebookUrl(String facebookUrl) {
        this.facebookUrl = facebookUrl;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    public String getTicketable() {
        return ticketable;
    }

    public void setTicketable(String ticketable) {
        this.ticketable = ticketable;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        this.media = media;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    public List<String> getRedemptionMethods() {
        return redemptionMethods;
    }

    public void setRedemptionMethods(List<String> redemptionMethods) {
        this.redemptionMethods = redemptionMethods;
    }

    public List<String> getConcessionsDeliveryOptions() {
        return concessionsDeliveryOptions;
    }

    public void setConcessionsDeliveryOptions(List<String> concessionsDeliveryOptions) {
        this.concessionsDeliveryOptions = concessionsDeliveryOptions;
    }

    public Double getConvenienceFeeTaxPercent() {
        return convenienceFeeTaxPercent;
    }

    public void setConvenienceFeeTaxPercent(Double convenienceFeeTaxPercent) {
        this.convenienceFeeTaxPercent = convenienceFeeTaxPercent;
    }

    public Integer getConvenienceFeeTaxFlatAmount() {
        return convenienceFeeTaxFlatAmount;
    }

    public void setConvenienceFeeTaxFlatAmount(Integer convenienceFeeTaxFlatAmount) {
        this.convenienceFeeTaxFlatAmount = convenienceFeeTaxFlatAmount;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getWestWorldMediaNumber() {
        return westWorldMediaNumber;
    }

    public void setWestWorldMediaNumber(String westWorldMediaNumber) {
        this.westWorldMediaNumber = westWorldMediaNumber;
    }

    public String getLoyaltyVersionId() {
        return loyaltyVersionId;
    }

    public void setLoyaltyVersionId(String loyaltyVersionId) {
        this.loyaltyVersionId = loyaltyVersionId;
    }

    public String getOnlineConcessions() {
        return onlineConcessions;
    }

    public void setOnlineConcessions(String onlineConcessions) {
        this.onlineConcessions = onlineConcessions;
    }

    public String getDeliveryToSeat() {
        return deliveryToSeat;
    }

    public void setDeliveryToSeat(String deliveryToSeat) {
        this.deliveryToSeat = deliveryToSeat;
    }

}
