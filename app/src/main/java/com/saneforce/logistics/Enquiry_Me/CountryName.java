package com.saneforce.logistics.Enquiry_Me;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CountryName {

    @SerializedName("Country_Code")
    @Expose
    private Integer countryCode;
    @SerializedName("Country_Name")
    @Expose
    private String countryName;
    @SerializedName("ShortName")
    @Expose
    private String shortName;
    @SerializedName("Created_Date")
    @Expose
    private Object createdDate;
    @SerializedName("LastUpdt_Date")
    @Expose
    private Object lastUpdtDate;
    @SerializedName("Division_ID")
    @Expose
    private Integer divisionID;
    @SerializedName("WeekOff")
    @Expose
    private String weekOff;
    @SerializedName("ZIPCode")
    @Expose
    private String zIPCode;
    @SerializedName("Country_Active_Flag")
    @Expose
    private String countryActiveFlag;
    @SerializedName("country_flag")
    @Expose
    private String country_flag;

    public Integer getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(Integer countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public Object getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Object createdDate) {
        this.createdDate = createdDate;
    }

    public Object getLastUpdtDate() {
        return lastUpdtDate;
    }

    public void setLastUpdtDate(Object lastUpdtDate) {
        this.lastUpdtDate = lastUpdtDate;
    }

    public Integer getDivisionID() {
        return divisionID;
    }

    public void setDivisionID(Integer divisionID) {
        this.divisionID = divisionID;
    }

    public String getWeekOff() {
        return weekOff;
    }

    public void setWeekOff(String weekOff) {
        this.weekOff = weekOff;
    }

    public String getZIPCode() {
        return zIPCode;
    }

    public void setZIPCode(String zIPCode) {
        this.zIPCode = zIPCode;
    }

    public String getCountryActiveFlag() {
        return countryActiveFlag;
    }

    public void setCountryActiveFlag(String countryActiveFlag) {
        this.countryActiveFlag = countryActiveFlag;
    }

    public String getCountry_flag() {
        return country_flag;
    }

    public void setCountry_flag(String country_flag) {
        this.country_flag = country_flag;
    }
}
