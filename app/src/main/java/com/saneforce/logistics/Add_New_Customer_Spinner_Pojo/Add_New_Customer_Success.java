package com.saneforce.logistics.Add_New_Customer_Spinner_Pojo;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Add_New_Customer_Success {
    @SerializedName("Customertype")
    @Expose
    private List<Customertype> customertype = null;
    @SerializedName("CustomerCategory")
    @Expose
    private List<CustomerCategory> customerCategory = null;

    public List<Customertype> getCustomertype() {
        return customertype;
    }

    public void setCustomertype(List<Customertype> customertype) {
        this.customertype = customertype;
    }

    public List<CustomerCategory> getCustomerCategory() {
        return customerCategory;
    }

    public void setCustomerCategory(List<CustomerCategory> customerCategory) {
        this.customerCategory = customerCategory;
    }

}