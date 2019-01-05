package com.saneforce.logistics.Customer_Profile_Pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SegmentName {
    @SerializedName("Seg_ID")
    @Expose
    private Integer segID;
    @SerializedName("Seg_Name")
    @Expose
    private String segName;
    @SerializedName("Seg_SName")
    @Expose
    private String segSName;
    @SerializedName("Division_ID")
    @Expose
    private Integer divisionID;
    @SerializedName("Seg_Active_Flag")
    @Expose
    private String segActiveFlag;
    @SerializedName("Created_Date")
    @Expose
    private Object createdDate;
    @SerializedName("LastUpdt_Date")
    @Expose
    private Object lastUpdtDate;

    public Integer getSegID() {
        return segID;
    }

    public void setSegID(Integer segID) {
        this.segID = segID;
    }

    public String getSegName() {
        return segName;
    }

    public void setSegName(String segName) {
        this.segName = segName;
    }

    public String getSegSName() {
        return segSName;
    }

    public void setSegSName(String segSName) {
        this.segSName = segSName;
    }

    public Integer getDivisionID() {
        return divisionID;
    }

    public void setDivisionID(Integer divisionID) {
        this.divisionID = divisionID;
    }

    public String getSegActiveFlag() {
        return segActiveFlag;
    }

    public void setSegActiveFlag(String segActiveFlag) {
        this.segActiveFlag = segActiveFlag;
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
