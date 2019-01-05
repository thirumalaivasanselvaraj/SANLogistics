package com.saneforce.logistics.Customer_Profile_Pojo;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class Name_Ofthe_Company_Success {
    @SerializedName("CustomerProfileNameOftheCompany")
    @Expose
    private List<CustomerProfileNameOftheCompany> customerProfileNameOftheCompany = null;
    @SerializedName("SectorName")
    @Expose
    private List<SectorName> sectorName = null;
    @SerializedName("SegmentName")
    @Expose
    private List<SegmentName> segmentName = null;
    @SerializedName("MonthlyVolumeName")
    @Expose
    private List<MonthlyVolumeName> monthlyVolumeName = null;

    public List<CustomerProfileNameOftheCompany> getCustomerProfileNameOftheCompany() {
        return customerProfileNameOftheCompany;
    }

    public void setCustomerProfileNameOftheCompany(List<CustomerProfileNameOftheCompany> customerProfileNameOftheCompany) {
        this.customerProfileNameOftheCompany = customerProfileNameOftheCompany;
    }

    public List<SectorName> getSectorName() {
        return sectorName;
    }

    public void setSectorName(List<SectorName> sectorName) {
        this.sectorName = sectorName;
    }

    public List<SegmentName> getSegmentName() {
        return segmentName;
    }

    public void setSegmentName(List<SegmentName> segmentName) {
        this.segmentName = segmentName;
    }

    public List<MonthlyVolumeName> getMonthlyVolumeName() {
        return monthlyVolumeName;
    }

    public void setMonthlyVolumeName(List<MonthlyVolumeName> monthlyVolumeName) {
        this.monthlyVolumeName = monthlyVolumeName;
    }

}