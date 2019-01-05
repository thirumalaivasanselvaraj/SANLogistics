package com.saneforce.logistics.Add_New_Customer_Spinner_Pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Customertype {

    @SerializedName("Cus_Type_ID")
    @Expose
    private Integer cusTypeID;
    @SerializedName("Cus_Type_sname")
    @Expose
    private String cusTypeSname;
    @SerializedName("Cus_Type_name")
    @Expose
    private String cusTypeName;
    @SerializedName("Division_ID")
    @Expose
    private Integer divisionID;
    @SerializedName("Cus_Type_Active_Flag")
    @Expose
    private String cusTypeActiveFlag;
    @SerializedName("Created_Date")
    @Expose
    private Object createdDate;
    @SerializedName("LastUpdt_Date")
    @Expose
    private Object lastUpdtDate;

    public Integer getCusTypeID() {
        return cusTypeID;
    }

    public void setCusTypeID(Integer cusTypeID) {
        this.cusTypeID = cusTypeID;
    }

    public String getCusTypeSname() {
        return cusTypeSname;
    }

    public void setCusTypeSname(String cusTypeSname) {
        this.cusTypeSname = cusTypeSname;
    }

    public String getCusTypeName() {
        return cusTypeName;
    }

    public void setCusTypeName(String cusTypeName) {
        this.cusTypeName = cusTypeName;
    }

    public Integer getDivisionID() {
        return divisionID;
    }

    public void setDivisionID(Integer divisionID) {
        this.divisionID = divisionID;
    }

    public String getCusTypeActiveFlag() {
        return cusTypeActiveFlag;
    }

    public void setCusTypeActiveFlag(String cusTypeActiveFlag) {
        this.cusTypeActiveFlag = cusTypeActiveFlag;
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