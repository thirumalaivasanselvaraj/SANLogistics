package com.saneforce.logistics.Customer_Profile_Pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SectorName {
    @SerializedName("Sector_ID")
    @Expose
    private Integer sectorID;
    @SerializedName("Sector_Name")
    @Expose
    private String sectorName;
    @SerializedName("DivisionID")
    @Expose
    private Integer divisionID;
    @SerializedName("Sector_Flag")
    @Expose
    private String sectorFlag;

    public Integer getSectorID() {
        return sectorID;
    }

    public void setSectorID(Integer sectorID) {
        this.sectorID = sectorID;
    }

    public String getSectorName() {
        return sectorName;
    }

    public void setSectorName(String sectorName) {
        this.sectorName = sectorName;
    }

    public Integer getDivisionID() {
        return divisionID;
    }

    public void setDivisionID(Integer divisionID) {
        this.divisionID = divisionID;
    }

    public String getSectorFlag() {
        return sectorFlag;
    }

    public void setSectorFlag(String sectorFlag) {
        this.sectorFlag = sectorFlag;
    }

}
