package com.saneforce.logistics.Add_New_Customer_Spinner_Pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CustomerCategory {

    @SerializedName("Cus_Cat_Code")
    @Expose
    private Integer cusCatCode;
    @SerializedName("Cus_Cat_SName")
    @Expose
    private String cusCatSName;
    @SerializedName("Cus_Cat_Name")
    @Expose
    private String cusCatName;
    @SerializedName("Division_ID")
    @Expose
    private Integer divisionID;
    @SerializedName("Cus_Cat_Active_Flag")
    @Expose
    private String cusCatActiveFlag;
    @SerializedName("Created_Date")
    @Expose
    private Object createdDate;
    @SerializedName("LastUpdt_Date")
    @Expose
    private Object lastUpdtDate;

    public Integer getCusCatCode() {
        return cusCatCode;
    }

    public void setCusCatCode(Integer cusCatCode) {
        this.cusCatCode = cusCatCode;
    }

    public String getCusCatSName() {
        return cusCatSName;
    }

    public void setCusCatSName(String cusCatSName) {
        this.cusCatSName = cusCatSName;
    }

    public String getCusCatName() {
        return cusCatName;
    }

    public void setCusCatName(String cusCatName) {
        this.cusCatName = cusCatName;
    }

    public Integer getDivisionID() {
        return divisionID;
    }

    public void setDivisionID(Integer divisionID) {
        this.divisionID = divisionID;
    }

    public String getCusCatActiveFlag() {
        return cusCatActiveFlag;
    }

    public void setCusCatActiveFlag(String cusCatActiveFlag) {
        this.cusCatActiveFlag = cusCatActiveFlag;
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