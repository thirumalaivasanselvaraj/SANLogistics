package com.saneforce.logistics.Enquiry_Me;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PortName {

    @SerializedName("Port_ID")
    @Expose
    private Integer portID;
    @SerializedName("Port_Name")
    @Expose
    private String portName;
    @SerializedName("Port_SName")
    @Expose
    private String portSName;
    @SerializedName("Division_ID")
    @Expose
    private Integer divisionID;
    @SerializedName("Port_Active_Flag")
    @Expose
    private String portActiveFlag;


    @SerializedName("Country_Code")
    @Expose
    private Integer countryCode;
    @SerializedName("Country_Name")
    @Expose
    private String countryName;

    public Integer getPortID() {
        return portID;
    }

    public void setPortID(Integer portID) {
        this.portID = portID;
    }

    public String getPortName() {
        return portName;
    }

    public void setPortName(String portName) {
        this.portName = portName;
    }

    public String getPortSName() {
        return portSName;
    }

    public void setPortSName(String portSName) {
        this.portSName = portSName;
    }

    public Integer getDivisionID() {
        return divisionID;
    }

    public void setDivisionID(Integer divisionID) {
        this.divisionID = divisionID;
    }

    public String getPortActiveFlag() {
        return portActiveFlag;
    }

    public void setPortActiveFlag(String portActiveFlag) {
        this.portActiveFlag = portActiveFlag;
    }



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

}
