package com.saneforce.logistics.Customer_Profile_Pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MonthlyVolumeName {
    @SerializedName("Vol_ID")
    @Expose
    private Integer volID;
    @SerializedName("Vol_Name")
    @Expose
    private String volName;
    @SerializedName("Vol_SName")
    @Expose
    private String volSName;
    @SerializedName("Division_ID")
    @Expose
    private Integer divisionID;
    @SerializedName("Vol_Active_Flag")
    @Expose
    private String volActiveFlag;
    @SerializedName("Created_Date")
    @Expose
    private Object createdDate;
    @SerializedName("LastUpdt_Date")
    @Expose
    private Object lastUpdtDate;

    public Integer getVolID() {
        return volID;
    }

    public void setVolID(Integer volID) {
        this.volID = volID;
    }

    public String getVolName() {
        return volName;
    }

    public void setVolName(String volName) {
        this.volName = volName;
    }

    public String getVolSName() {
        return volSName;
    }

    public void setVolSName(String volSName) {
        this.volSName = volSName;
    }

    public Integer getDivisionID() {
        return divisionID;
    }

    public void setDivisionID(Integer divisionID) {
        this.divisionID = divisionID;
    }

    public String getVolActiveFlag() {
        return volActiveFlag;
    }

    public void setVolActiveFlag(String volActiveFlag) {
        this.volActiveFlag = volActiveFlag;
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

}
