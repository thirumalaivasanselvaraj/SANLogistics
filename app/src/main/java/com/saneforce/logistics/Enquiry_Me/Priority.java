package com.saneforce.logistics.Enquiry_Me;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Priority {

    @SerializedName("Division_ID")
    @Expose
    private Integer divisionID;
    @SerializedName("Priority_Name")
    @Expose
    private String priorityName;
    @SerializedName("Priority_SName")
    @Expose
    private String prioritySName;

    public Integer getDivisionID() {
        return divisionID;
    }

    public void setDivisionID(Integer divisionID) {
        this.divisionID = divisionID;
    }

    public String getPriorityName() {
        return priorityName;
    }

    public void setPriorityName(String priorityName) {
        this.priorityName = priorityName;
    }

    public String getPrioritySName() {
        return prioritySName;
    }

    public void setPrioritySName(String prioritySName) {
        this.prioritySName = prioritySName;
    }

}