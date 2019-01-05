package com.saneforce.logistics.Enquiry_Me;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Commodity {

    @SerializedName("Division_ID")
    @Expose
    private Integer divisionID;
    @SerializedName("Commodity_Name")
    @Expose
    private String commodityName;
    @SerializedName("Commodity_SName")
    @Expose
    private String commoditySName;

    public Integer getDivisionID() {
        return divisionID;
    }

    public void setDivisionID(Integer divisionID) {
        this.divisionID = divisionID;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public String getCommoditySName() {
        return commoditySName;
    }

    public void setCommoditySName(String commoditySName) {
        this.commoditySName = commoditySName;
    }

}
