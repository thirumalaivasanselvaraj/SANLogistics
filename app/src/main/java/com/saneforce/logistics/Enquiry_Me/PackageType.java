package com.saneforce.logistics.Enquiry_Me;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PackageType {

    @SerializedName("Package_ID")
    @Expose
    private Integer packageID;
    @SerializedName("Package_Name")
    @Expose
    private String packageName;
    @SerializedName("Package_Flag")
    @Expose
    private Integer packageFlag;

    public Integer getPackageID() {
        return packageID;
    }

    public void setPackageID(Integer packageID) {
        this.packageID = packageID;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public Integer getPackageFlag() {
        return packageFlag;
    }
}
