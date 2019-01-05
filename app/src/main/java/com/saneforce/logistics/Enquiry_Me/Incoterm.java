package com.saneforce.logistics.Enquiry_Me;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Incoterm {

    @SerializedName("Division_ID")
    @Expose
    private Integer divisionID;
    @SerializedName("IncotermsName")
    @Expose
    private String incotermsName;


    public Incoterm(Integer divisionID,String incotermsName){

        this.divisionID = divisionID;
        this.incotermsName = incotermsName;
    }




    public Integer getDivisionID() {
        return divisionID;
    }

    public void setDivisionID(Integer divisionID) {
        this.divisionID = divisionID;
    }

    public String getIncotermsName() {
        return incotermsName;
    }

    public void setIncotermsName(String incotermsName) {
        this.incotermsName = incotermsName;
    }
    @Override
    public String toString() {
        return incotermsName ;
    }

}
