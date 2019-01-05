package com.saneforce.logistics.Customer_Me;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CustomerMe {
    @SerializedName("Emp_Code")
    @Expose
    private String empCode;
    @SerializedName("Emp_Name")
    @Expose
    private String empName;
    @SerializedName("Emp_UserName")
    @Expose
    private String empUserName;
    @SerializedName("Emp_Password")
    @Expose
    private String empPassword;
    @SerializedName("Emp_Joining_Date")
    @Expose
    private EmpJoiningDate empJoiningDate;
    @SerializedName("Reporting_To_Emp")
    @Expose
    private String reportingToEmp;
    @SerializedName("Country_Code")
    @Expose
    private Integer countryCode;
    @SerializedName("Country_Name")
    @Expose
    private String countryName;
    @SerializedName("Emp_ContactAdd_One")
    @Expose
    private String empContactAddOne;
    @SerializedName("Emp_ContactAdd_Two")
    @Expose
    private String empContactAddTwo;
    @SerializedName("Emp_City_Pincode")
    @Expose
    private String empCityPincode;
    @SerializedName("Emp_Email")
    @Expose
    private String empEmail;
    @SerializedName("Emp_Mobile")
    @Expose
    private String empMobile;
    @SerializedName("Emp_DOB")
    @Expose
    private EmpDOB empDOB;
    @SerializedName("Emp_Flag")
    @Expose
    private Integer empFlag;
    @SerializedName("Emp_HQ")
    @Expose
    private String empHQ;
    @SerializedName("Division_Code")
    @Expose
    private Integer divisionCode;

    @SerializedName("Emp_Type")
    @Expose
    private Integer empType;
    @SerializedName("Emp_ID")
    @Expose
    private String empID;
    @SerializedName("SubDivision_Code")
    @Expose
    private String subDivisionCode;
    @SerializedName("Designation_Code")
    @Expose
    private Integer designationCode;
    @SerializedName("Designation_Name")
    @Expose
    private String designationName;
    @SerializedName("Designation_Short_Name")
    @Expose
    private String designationShortName;

    @SerializedName("File_Path")
    @Expose
    private String filePath;

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpUserName() {
        return empUserName;
    }

    public void setEmpUserName(String empUserName) {
        this.empUserName = empUserName;
    }

    public String getEmpPassword() {
        return empPassword;
    }

    public void setEmpPassword(String empPassword) {
        this.empPassword = empPassword;
    }

    public EmpJoiningDate getEmpJoiningDate() {
        return empJoiningDate;
    }

    public void setEmpJoiningDate(EmpJoiningDate empJoiningDate) {
        this.empJoiningDate = empJoiningDate;
    }

    public String getReportingToEmp() {
        return reportingToEmp;
    }

    public void setReportingToEmp(String reportingToEmp) {
        this.reportingToEmp = reportingToEmp;
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

    public String getEmpContactAddOne() {
        return empContactAddOne;
    }

    public void setEmpContactAddOne(String empContactAddOne) {
        this.empContactAddOne = empContactAddOne;
    }

    public String getEmpContactAddTwo() {
        return empContactAddTwo;
    }

    public void setEmpContactAddTwo(String empContactAddTwo) {
        this.empContactAddTwo = empContactAddTwo;
    }

    public String getEmpCityPincode() {
        return empCityPincode;
    }

    public void setEmpCityPincode(String empCityPincode) {
        this.empCityPincode = empCityPincode;
    }

    public String getEmpEmail() {
        return empEmail;
    }

    public void setEmpEmail(String empEmail) {
        this.empEmail = empEmail;
    }

    public String getEmpMobile() {
        return empMobile;
    }

    public void setEmpMobile(String empMobile) {
        this.empMobile = empMobile;
    }

    public EmpDOB getEmpDOB() {
        return empDOB;
    }

    public void setEmpDOB(EmpDOB empDOB) {
        this.empDOB = empDOB;
    }

    public Integer getEmpFlag() {
        return empFlag;
    }

    public void setEmpFlag(Integer empFlag) {
        this.empFlag = empFlag;
    }

    public String getEmpHQ() {
        return empHQ;
    }

    public void setEmpHQ(String empHQ) {
        this.empHQ = empHQ;
    }

    public Integer getDivisionCode() {
        return divisionCode;
    }

    public void setDivisionCode(Integer divisionCode) {
        this.divisionCode = divisionCode;
    }



    public Integer getEmpType() {
        return empType;
    }

    public void setEmpType(Integer empType) {
        this.empType = empType;
    }

    public String getEmpID() {
        return empID;
    }

    public void setEmpID(String empID) {
        this.empID = empID;
    }

    public String getSubDivisionCode() {
        return subDivisionCode;
    }

    public void setSubDivisionCode(String subDivisionCode) {
        this.subDivisionCode = subDivisionCode;
    }

    public Integer getDesignationCode() {
        return designationCode;
    }

    public void setDesignationCode(Integer designationCode) {
        this.designationCode = designationCode;
    }

    public String getDesignationName() {
        return designationName;
    }

    public void setDesignationName(String designationName) {
        this.designationName = designationName;
    }

    public String getDesignationShortName() {
        return designationShortName;
    }

    public void setDesignationShortName(String designationShortName) {
        this.designationShortName = designationShortName;
    }


    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

}
