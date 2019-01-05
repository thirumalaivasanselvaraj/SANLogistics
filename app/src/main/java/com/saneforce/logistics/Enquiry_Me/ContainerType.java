package com.saneforce.logistics.Enquiry_Me;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ContainerType {

    @SerializedName("Container_ID")
    @Expose
    private Integer containerID;
    @SerializedName("Container_Name")
    @Expose
    private String containerName;
    @SerializedName("Container_Flag")
    @Expose
    private String containerFlag;

    public Integer getContainerID() {
        return containerID;
    }

    public void setContainerID(Integer containerID) {
        this.containerID = containerID;
    }

    public String getContainerName() {
        return containerName;
    }

    public void setContainerName(String containerName) {
        this.containerName = containerName;
    }

    public String getContainerFlag() {
        return containerFlag;
    }

    public void setContainerFlag(String containerFlag) {
        this.containerFlag = containerFlag;
    }

}