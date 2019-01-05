package com.saneforce.logistics.Enquiry_Me;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.saneforce.logistics.Rate_finder_Fragment.RatefilnderResponse;

public class Enquiry_Me_Success {

    @SerializedName("Incoterms")
    @Expose
    private List<Incoterm> incoterms = null;
    @SerializedName("Priority")
    @Expose
    private List<Priority> priority = null;
    @SerializedName("Commodity")
    @Expose
    private List<Commodity> commodity = null;
    @SerializedName("CountryName")
    @Expose
    private List<CountryName> countryName = null;
    @SerializedName("PortName")
    @Expose
    private List<PortName> portName = null;
    @SerializedName("Package_Types")
    @Expose
    private List<PackageType> packageTypes = null;
    @SerializedName("Container_Types")
    @Expose
    private List<ContainerType> containerTypes = null;
    @SerializedName("RatefilnderResponse")
    @Expose
    private List<RatefilnderResponse> ratefilnderResponse = null;
    public List<Incoterm> getIncoterms() {
        return incoterms;
    }

    public void setIncoterms(List<Incoterm> incoterms) {
        this.incoterms = incoterms;
    }

    public List<Priority> getPriority() {
        return priority;
    }

    public void setPriority(List<Priority> priority) {
        this.priority = priority;
    }

    public List<Commodity> getCommodity() {
        return commodity;
    }

    public void setCommodity(List<Commodity> commodity) {
        this.commodity = commodity;
    }

    public List<CountryName> getCountryName() {
        return countryName;
    }

    public void setCountryName(List<CountryName> countryName) {
        this.countryName = countryName;
    }

    public List<PortName> getPortName() {
        return portName;
    }

    public void setPortName(List<PortName> portName) {
        this.portName = portName;
    }

    public List<PackageType> getPackageTypes() {
        return packageTypes;
    }

    public void setPackageTypes(List<PackageType> packageTypes) {
        this.packageTypes = packageTypes;
    }

    public List<ContainerType> getContainerTypes() {
        return containerTypes;
    }

    public void setContainerTypes(List<ContainerType> containerTypes) {
        this.containerTypes = containerTypes;
    }
    public List<RatefilnderResponse> getRatefilnderResponse() {
        return ratefilnderResponse;
    }

    public void setRatefilnderResponse(List<RatefilnderResponse> ratefilnderResponse) {
        this.ratefilnderResponse = ratefilnderResponse;
    }
}