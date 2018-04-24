package com.salesforce.api.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Account {

    @JsonPropertyOrder({
            "attributes",
            "Id",
            "IsDeleted",
            "MasterRecordId",
            "Name",
            "Type",
            "ParentId",
            "BillingStreet",
            "BillingCity",
            "BillingState",
            "BillingPostalCode",
            "BillingCountry",
            "BillingLatitude",
            "BillingLongitude",
            "BillingGeocodeAccuracy",
            "BillingAddress",
            "ShippingStreet",
            "ShippingCity",
            "ShippingState",
            "ShippingPostalCode",
            "ShippingCountry",
            "ShippingLatitude",
            "ShippingLongitude",
            "ShippingGeocodeAccuracy",
            "ShippingAddress",
            "Phone",
            "Fax",
            "AccountNumber",
            "Website",
            "PhotoUrl",
            "Sic",
            "Industry",
            "AnnualRevenue",
            "NumberOfEmployees",
            "Ownership",
            "TickerSymbol",
            "Description",
            "Rating",
            "Site",
            "OwnerId",
            "CreatedDate",
            "CreatedById",
            "LastModifiedDate",
            "LastModifiedById",
            "SystemModstamp",
            "LastActivityDate",
            "LastViewedDate",
            "LastReferencedDate",
            "Jigsaw",
            "JigsawCompanyId",
            "CleanStatus",
            "AccountSource",
            "DunsNumber",
            "Tradestyle",
            "NaicsCode",
            "NaicsDesc",
            "YearStarted",
            "SicDesc",
            "DandbCompanyId",
            "CustomerPriority__c",
            "SLA__c",
            "Active__c",
            "NumberofLocations__c",
            "UpsellOpportunity__c",
            "SLASerialNumber__c",
            "SLAExpirationDate__c"
    })

    @JsonProperty("attributes")
    private Attributes attributes;

    @JsonProperty("Id")
    private String id;

    @JsonProperty("IsDeleted")
    private String isDeleted;

    @JsonProperty("MasterRecordId")
    private String masterRecordId;

    @JsonProperty("Name")
    private String name;

    @JsonProperty("Type")
    private String type;

    @JsonProperty("ParentId")
    private String parentId;

    @JsonProperty("BillingStreet")
    private String billingStreet;

    @JsonProperty("BillingCity")
    private String billingCity;

    @JsonProperty("BillingState")
    private String billingState;

    @JsonProperty("BillingPostalCode")
    private String billingPostalCode;

    @JsonProperty("BillingCountry")
    private String billingCountry;

    @JsonProperty("BillingLatitude")
    private String billingLatitude;

    @JsonProperty("BillingLongitude")
    private String billingLongitude;

    @JsonProperty("BillingGeocodeAccuracy")
    private String billingGeocodeAccuracy;

    @JsonProperty("BillingAddress")
    private String billingAddress;

    @JsonProperty("ShippingStreet")
    private String shippingStreet;

    @JsonProperty("ShippingCity")
    private String shippingCity;

    @JsonProperty("ShippingState")
    private String shippingState;

    @JsonProperty("ShippingPostalCode")
    private String shippingPostalCode;

    @JsonProperty("ShippingCountry")
    private String shippingCountry;

    @JsonProperty("ShippingLatitude")
    private String shippingLatitude;

    @JsonProperty("ShippingLongitude")
    private String shippingLongitude;

    @JsonProperty("ShippingGeocodeAccuracy")
    private String shippingGeocodeAccuracy;

    @JsonProperty("ShippingAddress")
    private String shippingAddress;

    @JsonProperty("Phone")
    private String phone;

    @JsonProperty("Fax")
    private String fax;

    @JsonProperty("AccountNumber")
    private String accountNumber;

    @JsonProperty("Website")
    private String website;

    @JsonProperty("PhotoUrl")
    private String photoUrl;

    @JsonProperty("Sic")
    private String sic;

    @JsonProperty("Industry")
    private String industry;

    @JsonProperty("AnnualRevenue")
    private String annualRevenue;

    @JsonProperty("NumberOfEmployees")
    private String numberOfEmployees;

    @JsonProperty("Ownership")
    private String ownership;

    @JsonProperty("TickerSymbol")
    private String tickerSymbol;

    @JsonProperty("Description")
    private String description;

    @JsonProperty("Rating")
    private String rating;

    @JsonProperty("Site")
    private String site;

    @JsonProperty("OwnerId")
    private String ownerId;

    @JsonProperty("CreatedDate")
    private String createdDate;

    @JsonProperty("CreatedById")
    private String createdById;

    @JsonProperty("LastModifiedDate")
    private String lastModifiedDate;

    @JsonProperty("LastModifiedById")
    private String lastModifiedById;

    @JsonProperty("SystemModstamp")
    private String systemModstamp;

    @JsonProperty("LastActivityDate")
    private String lastActivityDate;

    @JsonProperty("LastViewedDate")
    private String lastViewedDate;

    @JsonProperty("LastReferencedDate")
    private String lastReferencedDate;

    @JsonProperty("Jigsaw")
    private String jigsaw;

    @JsonProperty("JigsawCompanyId")
    private String jigsawCompanyId;

    @JsonProperty("CleanStatus")
    private String cleanStatus;

    @JsonProperty("AccountSource")
    private String accountSource;

    @JsonProperty("DunsNumber")
    private String dunsNumber;

    @JsonProperty("Tradestyle")
    private String tradestyle;

    @JsonProperty("NaicsCode")
    private String naicsCode;

    @JsonProperty("NaicsDesc")
    private String naicsDesc;

    @JsonProperty("YearStarted")
    private String yearStarted;

    @JsonProperty("SicDesc")
    private String sicDesc;

    @JsonProperty("DandbCompanyId")
    private String dandbCompanyId;

    @JsonProperty("CustomerPriority__c")
    private String customerPriorityC;

    @JsonProperty("SLA__c")
    private String sLAC;

    @JsonProperty("Active__c")
    private String activeC;

    @JsonProperty("NumberofLocations__c")
    private String numberofLocationsC;

    @JsonProperty("UpsellOpportunity__c")
    private String upsellOpportunityC;

    @JsonProperty("SLASerialNumber__c")
    private String sLASerialNumberC;

    @JsonProperty("SLAExpirationDate__c")
    private String sLAExpirationDateC;

    @JsonProperty("attributes")
    public Attributes getAttributes() {
        return attributes;
    }

    @JsonProperty("attributes")
    public void setAttributes(Attributes attributes) {
        this.attributes = attributes;
    }

    @JsonProperty("Id")
    public String getId() {
        return id;
    }

    @JsonProperty("Id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("IsDeleted")
    public String getIsDeleted() {
        return isDeleted;
    }

    @JsonProperty("IsDeleted")
    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    @JsonProperty("MasterRecordId")
    public String getMasterRecordId() {
        return masterRecordId;
    }

    @JsonProperty("MasterRecordId")
    public void setMasterRecordId(String masterRecordId) {
        this.masterRecordId = masterRecordId;
    }

    @JsonProperty("Name")
    public String getName() {
        return name;
    }

    @JsonProperty("Name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("Type")
    public String getType() {
        return type;
    }

    @JsonProperty("Type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("ParentId")
    public String getParentId() {
        return parentId;
    }

    @JsonProperty("ParentId")
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    @JsonProperty("BillingStreet")
    public String getBillingStreet() {
        return billingStreet;
    }

    @JsonProperty("BillingStreet")
    public void setBillingStreet(String billingStreet) {
        this.billingStreet = billingStreet;
    }

    @JsonProperty("BillingCity")
    public String getBillingCity() {
        return billingCity;
    }

    @JsonProperty("BillingCity")
    public void setBillingCity(String billingCity) {
        this.billingCity = billingCity;
    }

    @JsonProperty("BillingState")
    public String getBillingState() {
        return billingState;
    }

    @JsonProperty("BillingState")
    public void setBillingState(String billingState) {
        this.billingState = billingState;
    }

    @JsonProperty("BillingPostalCode")
    public String getBillingPostalCode() {
        return billingPostalCode;
    }

    @JsonProperty("BillingPostalCode")
    public void setBillingPostalCode(String billingPostalCode) {
        this.billingPostalCode = billingPostalCode;
    }

    @JsonProperty("BillingCountry")
    public String getBillingCountry() {
        return billingCountry;
    }

    @JsonProperty("BillingCountry")
    public void setBillingCountry(String billingCountry) {
        this.billingCountry = billingCountry;
    }

    @JsonProperty("BillingLatitude")
    public String getBillingLatitude() {
        return billingLatitude;
    }

    @JsonProperty("BillingLatitude")
    public void setBillingLatitude(String billingLatitude) {
        this.billingLatitude = billingLatitude;
    }

    @JsonProperty("BillingLongitude")
    public String getBillingLongitude() {
        return billingLongitude;
    }

    @JsonProperty("BillingLongitude")
    public void setBillingLongitude(String billingLongitude) {
        this.billingLongitude = billingLongitude;
    }

    @JsonProperty("BillingGeocodeAccuracy")
    public String getBillingGeocodeAccuracy() {
        return billingGeocodeAccuracy;
    }

    @JsonProperty("BillingGeocodeAccuracy")
    public void setBillingGeocodeAccuracy(String billingGeocodeAccuracy) {
        this.billingGeocodeAccuracy = billingGeocodeAccuracy;
    }

    @JsonProperty("BillingAddress")
    public String getBillingAddress() {
        return billingAddress;
    }

    @JsonProperty("BillingAddress")
    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    @JsonProperty("ShippingStreet")
    public String getShippingStreet() {
        return shippingStreet;
    }

    @JsonProperty("ShippingStreet")
    public void setShippingStreet(String shippingStreet) {
        this.shippingStreet = shippingStreet;
    }

    @JsonProperty("ShippingCity")
    public String getShippingCity() {
        return shippingCity;
    }

    @JsonProperty("ShippingCity")
    public void setShippingCity(String shippingCity) {
        this.shippingCity = shippingCity;
    }

    @JsonProperty("ShippingState")
    public String getShippingState() {
        return shippingState;
    }

    @JsonProperty("ShippingState")
    public void setShippingState(String shippingState) {
        this.shippingState = shippingState;
    }

    @JsonProperty("ShippingPostalCode")
    public String getShippingPostalCode() {
        return shippingPostalCode;
    }

    @JsonProperty("ShippingPostalCode")
    public void setShippingPostalCode(String shippingPostalCode) {
        this.shippingPostalCode = shippingPostalCode;
    }

    @JsonProperty("ShippingCountry")
    public String getShippingCountry() {
        return shippingCountry;
    }

    @JsonProperty("ShippingCountry")
    public void setShippingCountry(String shippingCountry) {
        this.shippingCountry = shippingCountry;
    }

    @JsonProperty("ShippingLatitude")
    public String getShippingLatitude() {
        return shippingLatitude;
    }

    @JsonProperty("ShippingLatitude")
    public void setShippingLatitude(String shippingLatitude) {
        this.shippingLatitude = shippingLatitude;
    }

    @JsonProperty("ShippingLongitude")
    public String getShippingLongitude() {
        return shippingLongitude;
    }

    @JsonProperty("ShippingLongitude")
    public void setShippingLongitude(String shippingLongitude) {
        this.shippingLongitude = shippingLongitude;
    }

    @JsonProperty("ShippingGeocodeAccuracy")
    public String getShippingGeocodeAccuracy() {
        return shippingGeocodeAccuracy;
    }

    @JsonProperty("ShippingGeocodeAccuracy")
    public void setShippingGeocodeAccuracy(String shippingGeocodeAccuracy) {
        this.shippingGeocodeAccuracy = shippingGeocodeAccuracy;
    }

    @JsonProperty("ShippingAddress")
    public String getShippingAddress() {
        return shippingAddress;
    }

    @JsonProperty("ShippingAddress")
    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    @JsonProperty("Phone")
    public String getPhone() {
        return phone;
    }

    @JsonProperty("Phone")
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @JsonProperty("Fax")
    public String getFax() {
        return fax;
    }

    @JsonProperty("Fax")
    public void setFax(String fax) {
        this.fax = fax;
    }

    @JsonProperty("AccountNumber")
    public String getAccountNumber() {
        return accountNumber;
    }

    @JsonProperty("AccountNumber")
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @JsonProperty("Website")
    public String getWebsite() {
        return website;
    }

    @JsonProperty("Website")
    public void setWebsite(String website) {
        this.website = website;
    }

    @JsonProperty("PhotoUrl")
    public String getPhotoUrl() {
        return photoUrl;
    }

    @JsonProperty("PhotoUrl")
    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    @JsonProperty("Sic")
    public String getSic() {
        return sic;
    }

    @JsonProperty("Sic")
    public void setSic(String sic) {
        this.sic = sic;
    }

    @JsonProperty("Industry")
    public String getIndustry() {
        return industry;
    }

    @JsonProperty("Industry")
    public void setIndustry(String industry) {
        this.industry = industry;
    }

    @JsonProperty("AnnualRevenue")
    public String getAnnualRevenue() {
        return annualRevenue;
    }

    @JsonProperty("AnnualRevenue")
    public void setAnnualRevenue(String annualRevenue) {
        this.annualRevenue = annualRevenue;
    }

    @JsonProperty("NumberOfEmployees")
    public String getNumberOfEmployees() {
        return numberOfEmployees;
    }

    @JsonProperty("NumberOfEmployees")
    public void setNumberOfEmployees(String numberOfEmployees) {
        this.numberOfEmployees = numberOfEmployees;
    }

    @JsonProperty("Ownership")
    public String getOwnership() {
        return ownership;
    }

    @JsonProperty("Ownership")
    public void setOwnership(String ownership) {
        this.ownership = ownership;
    }

    @JsonProperty("TickerSymbol")
    public String getTickerSymbol() {
        return tickerSymbol;
    }

    @JsonProperty("TickerSymbol")
    public void setTickerSymbol(String tickerSymbol) {
        this.tickerSymbol = tickerSymbol;
    }

    @JsonProperty("Description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("Description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("Rating")
    public String getRating() {
        return rating;
    }

    @JsonProperty("Rating")
    public void setRating(String rating) {
        this.rating = rating;
    }

    @JsonProperty("Site")
    public String getSite() {
        return site;
    }

    @JsonProperty("Site")
    public void setSite(String site) {
        this.site = site;
    }

    @JsonProperty("OwnerId")
    public String getOwnerId() {
        return ownerId;
    }

    @JsonProperty("OwnerId")
    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    @JsonProperty("CreatedDate")
    public String getCreatedDate() {
        return createdDate;
    }

    @JsonProperty("CreatedDate")
    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    @JsonProperty("CreatedById")
    public String getCreatedById() {
        return createdById;
    }

    @JsonProperty("CreatedById")
    public void setCreatedById(String createdById) {
        this.createdById = createdById;
    }

    @JsonProperty("LastModifiedDate")
    public String getLastModifiedDate() {
        return lastModifiedDate;
    }

    @JsonProperty("LastModifiedDate")
    public void setLastModifiedDate(String lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    @JsonProperty("LastModifiedById")
    public String getLastModifiedById() {
        return lastModifiedById;
    }

    @JsonProperty("LastModifiedById")
    public void setLastModifiedById(String lastModifiedById) {
        this.lastModifiedById = lastModifiedById;
    }

    @JsonProperty("SystemModstamp")
    public String getSystemModstamp() {
        return systemModstamp;
    }

    @JsonProperty("SystemModstamp")
    public void setSystemModstamp(String systemModstamp) {
        this.systemModstamp = systemModstamp;
    }

    @JsonProperty("LastActivityDate")
    public String getLastActivityDate() {
        return lastActivityDate;
    }

    @JsonProperty("LastActivityDate")
    public void setLastActivityDate(String lastActivityDate) {
        this.lastActivityDate = lastActivityDate;
    }

    @JsonProperty("LastViewedDate")
    public String getLastViewedDate() {
        return lastViewedDate;
    }

    @JsonProperty("LastViewedDate")
    public void setLastViewedDate(String lastViewedDate) {
        this.lastViewedDate = lastViewedDate;
    }

    @JsonProperty("LastReferencedDate")
    public String getLastReferencedDate() {
        return lastReferencedDate;
    }

    @JsonProperty("LastReferencedDate")
    public void setLastReferencedDate(String lastReferencedDate) {
        this.lastReferencedDate = lastReferencedDate;
    }

    @JsonProperty("Jigsaw")
    public String getJigsaw() {
        return jigsaw;
    }

    @JsonProperty("Jigsaw")
    public void setJigsaw(String jigsaw) {
        this.jigsaw = jigsaw;
    }

    @JsonProperty("JigsawCompanyId")
    public String getJigsawCompanyId() {
        return jigsawCompanyId;
    }

    @JsonProperty("JigsawCompanyId")
    public void setJigsawCompanyId(String jigsawCompanyId) {
        this.jigsawCompanyId = jigsawCompanyId;
    }

    @JsonProperty("CleanStatus")
    public String getCleanStatus() {
        return cleanStatus;
    }

    @JsonProperty("CleanStatus")
    public void setCleanStatus(String cleanStatus) {
        this.cleanStatus = cleanStatus;
    }

    @JsonProperty("AccountSource")
    public String getAccountSource() {
        return accountSource;
    }

    @JsonProperty("AccountSource")
    public void setAccountSource(String accountSource) {
        this.accountSource = accountSource;
    }

    @JsonProperty("DunsNumber")
    public String getDunsNumber() {
        return dunsNumber;
    }

    @JsonProperty("DunsNumber")
    public void setDunsNumber(String dunsNumber) {
        this.dunsNumber = dunsNumber;
    }

    @JsonProperty("Tradestyle")
    public String getTradestyle() {
        return tradestyle;
    }

    @JsonProperty("Tradestyle")
    public void setTradestyle(String tradestyle) {
        this.tradestyle = tradestyle;
    }

    @JsonProperty("NaicsCode")
    public String getNaicsCode() {
        return naicsCode;
    }

    @JsonProperty("NaicsCode")
    public void setNaicsCode(String naicsCode) {
        this.naicsCode = naicsCode;
    }

    @JsonProperty("NaicsDesc")
    public String getNaicsDesc() {
        return naicsDesc;
    }

    @JsonProperty("NaicsDesc")
    public void setNaicsDesc(String naicsDesc) {
        this.naicsDesc = naicsDesc;
    }

    @JsonProperty("YearStarted")
    public String getYearStarted() {
        return yearStarted;
    }

    @JsonProperty("YearStarted")
    public void setYearStarted(String yearStarted) {
        this.yearStarted = yearStarted;
    }

    @JsonProperty("SicDesc")
    public String getSicDesc() {
        return sicDesc;
    }

    @JsonProperty("SicDesc")
    public void setSicDesc(String sicDesc) {
        this.sicDesc = sicDesc;
    }

    @JsonProperty("DandbCompanyId")
    public String getDandbCompanyId() {
        return dandbCompanyId;
    }

    @JsonProperty("DandbCompanyId")
    public void setDandbCompanyId(String dandbCompanyId) {
        this.dandbCompanyId = dandbCompanyId;
    }

    @JsonProperty("CustomerPriority__c")
    public String getCustomerPriorityC() {
        return customerPriorityC;
    }

    @JsonProperty("CustomerPriority__c")
    public void setCustomerPriorityC(String customerPriorityC) {
        this.customerPriorityC = customerPriorityC;
    }

    @JsonProperty("SLA__c")
    public String getSLAC() {
        return sLAC;
    }

    @JsonProperty("SLA__c")
    public void setSLAC(String sLAC) {
        this.sLAC = sLAC;
    }

    @JsonProperty("Active__c")
    public String getActiveC() {
        return activeC;
    }

    @JsonProperty("Active__c")
    public void setActiveC(String activeC) {
        this.activeC = activeC;
    }

    @JsonProperty("NumberofLocations__c")
    public String getNumberofLocationsC() {
        return numberofLocationsC;
    }

    @JsonProperty("NumberofLocations__c")
    public void setNumberofLocationsC(String numberofLocationsC) {
        this.numberofLocationsC = numberofLocationsC;
    }

    @JsonProperty("UpsellOpportunity__c")
    public String getUpsellOpportunityC() {
        return upsellOpportunityC;
    }

    @JsonProperty("UpsellOpportunity__c")
    public void setUpsellOpportunityC(String upsellOpportunityC) {
        this.upsellOpportunityC = upsellOpportunityC;
    }

    @JsonProperty("SLASerialNumber__c")
    public String getSLASerialNumberC() {
        return sLASerialNumberC;
    }

    @JsonProperty("SLASerialNumber__c")
    public void setSLASerialNumberC(String sLASerialNumberC) {
        this.sLASerialNumberC = sLASerialNumberC;
    }

    @JsonProperty("SLAExpirationDate__c")
    public String getSLAExpirationDateC() {
        return sLAExpirationDateC;
    }

    @JsonProperty("SLAExpirationDate__c")
    public void setSLAExpirationDateC(String sLAExpirationDateC) {
        this.sLAExpirationDateC = sLAExpirationDateC;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("attributes", attributes).append("id", id).append("isDeleted", isDeleted).append("masterRecordId", masterRecordId).append("name", name).append("type", type).append("parentId", parentId).append("billingStreet", billingStreet).append("billingCity", billingCity).append("billingState", billingState).append("billingPostalCode", billingPostalCode).append("billingCountry", billingCountry).append("billingLatitude", billingLatitude).append("billingLongitude", billingLongitude).append("billingGeocodeAccuracy", billingGeocodeAccuracy).append("billingAddress", billingAddress).append("shippingStreet", shippingStreet).append("shippingCity", shippingCity).append("shippingState", shippingState).append("shippingPostalCode", shippingPostalCode).append("shippingCountry", shippingCountry).append("shippingLatitude", shippingLatitude).append("shippingLongitude", shippingLongitude).append("shippingGeocodeAccuracy", shippingGeocodeAccuracy)
                .append("shippingAddress", shippingAddress).append("phone", phone).append("fax", fax).append("accountNumber", accountNumber).append("website", website).append("photoUrl", photoUrl).append("sic", sic).append("industry", industry).append("annualRevenue", annualRevenue).append("numberOfEmployees", numberOfEmployees).append("ownership", ownership).append("tickerSymbol", tickerSymbol).append("description", description).append("rating", rating).append("site", site).append("ownerId", ownerId).append("createdDate", createdDate).append("createdById", createdById).append("lastModifiedDate", lastModifiedDate).append("lastModifiedById", lastModifiedById).append("systemModstamp", systemModstamp).append("lastActivityDate", lastActivityDate).append("lastViewedDate", lastViewedDate).append("lastReferencedDate", lastReferencedDate).append("jigsaw", jigsaw).append("jigsawCompanyId", jigsawCompanyId).append("cleanStatus", cleanStatus).append("accountSource", accountSource)
                .append("dunsNumber", dunsNumber).append("tradestyle", tradestyle).append("naicsCode", naicsCode).append("naicsDesc", naicsDesc).append("yearStarted", yearStarted).append("sicDesc", sicDesc).append("dandbCompanyId", dandbCompanyId).append("customerPriorityC", customerPriorityC).append("sLAC", sLAC).append("activeC", activeC).append("numberofLocationsC", numberofLocationsC).append("upsellOpportunityC", upsellOpportunityC).append("sLASerialNumberC", sLASerialNumberC).append("sLAExpirationDateC", sLAExpirationDateC).toString();
    }
}
