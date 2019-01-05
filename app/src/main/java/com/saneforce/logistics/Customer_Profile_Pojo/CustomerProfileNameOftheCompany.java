package com.saneforce.logistics.Customer_Profile_Pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CustomerProfileNameOftheCompany {

    @SerializedName("Emp_Code")
    @Expose
    private Integer empCode;
    @SerializedName("Emp_ID")
    @Expose
    private String empID;
    @SerializedName("Emp_Type")
    @Expose
    private Object empType;
    @SerializedName("Emp_HQ")
    @Expose
    private Object empHQ;
    @SerializedName("Division_Code")
    @Expose
    private String divisionCode;
    @SerializedName("SubDivision_Code")
    @Expose
    private Object subDivisionCode;
    @SerializedName("Created_Date")
    @Expose
    private String createdDate;
    @SerializedName("Nameofthe_Company")
    @Expose
    private String nameoftheCompany;
    @SerializedName("Addressofthe_Company")
    @Expose
    private String addressoftheCompany;
    @SerializedName("Contact_Person_Name")
    @Expose
    private String contactPersonName;
    @SerializedName("Designation")
    @Expose
    private String designation;
    @SerializedName("Mobile_Number")
    @Expose
    private String mobileNumber;
    @SerializedName("Customer_Email")
    @Expose
    private String customerEmail;
    @SerializedName("Customer_Category")
    @Expose
    private String customerCategory;
    @SerializedName("Customer_Type")
    @Expose
    private String customerType;

    public CustomerProfileNameOftheCompany(Integer empCode, String empID, String divisionCode, String createdDate, String nameoftheCompany, String addressoftheCompany, String contactPersonName, String designation, String mobileNumber, String customerEmail, String customerCategory) {

        this.empCode = empCode;
        this.empID = empID;
        this.divisionCode = divisionCode;
        this.createdDate = createdDate;
        this.nameoftheCompany = nameoftheCompany;
        this.addressoftheCompany = addressoftheCompany;
        this.contactPersonName = contactPersonName;
        this.designation = designation;
        this.mobileNumber = mobileNumber;
        this.customerEmail = customerEmail;
        this.customerCategory = customerCategory;

    }

    public Integer getEmpCode() {
        return empCode;
    }

    public void setEmpCode(Integer empCode) {
        this.empCode = empCode;
    }

    public String getEmpID() {
        return empID;
    }

    public void setEmpID(String empID) {
        this.empID = empID;
    }

    public Object getEmpType() {
        return empType;
    }

    public void setEmpType(Object empType) {
        this.empType = empType;
    }

    public Object getEmpHQ() {
        return empHQ;
    }

    public void setEmpHQ(Object empHQ) {
        this.empHQ = empHQ;
    }

    public String getDivisionCode() {
        return divisionCode;
    }

    public void setDivisionCode(String divisionCode) {
        this.divisionCode = divisionCode;
    }

    public Object getSubDivisionCode() {
        return subDivisionCode;
    }

    public void setSubDivisionCode(Object subDivisionCode) {
        this.subDivisionCode = subDivisionCode;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getNameoftheCompany() {
        return nameoftheCompany;
    }

    public void setNameoftheCompany(String nameoftheCompany) {
        this.nameoftheCompany = nameoftheCompany;
    }

    public String getAddressoftheCompany() {
        return addressoftheCompany;
    }

    public void setAddressoftheCompany(String addressoftheCompany) {
        this.addressoftheCompany = addressoftheCompany;
    }

    public String getContactPersonName() {
        return contactPersonName;
    }

    public void setContactPersonName(String contactPersonName) {
        this.contactPersonName = contactPersonName;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerCategory() {
        return customerCategory;
    }

    public void setCustomerCategory(String customerCategory) {
        this.customerCategory = customerCategory;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }
    @Override
    public String toString() {
        return nameoftheCompany ;
    }
}