package com.saneforce.logistics.Rate_finder_Fragment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RatefilnderResponse {

    @SerializedName("POL")
    @Expose
    private String pOL;
    @SerializedName("DEP")
    @Expose
    private String dEP;
    @SerializedName("MODE")
    @Expose
    private String mODE;
    @SerializedName("1TS")
    @Expose
    private String _1TS;
    @SerializedName("LINE")
    @Expose
    private String lINE;
    @SerializedName("SVC1")
    @Expose
    private String sVC1;
    @SerializedName("2TS")
    @Expose
    private String _2TS;
    @SerializedName("SVC2")
    @Expose
    private String sVC2;
    @SerializedName("FINAL_POD")
    @Expose
    private String fINALPOD;
    @SerializedName("ARR")
    @Expose
    private String aRR;
    @SerializedName("TOT_TRANSIT")
    @Expose
    private String tOTTRANSIT;
    @SerializedName("REMARK")
    @Expose
    private String rEMARK;
    @SerializedName("Country_Code")
    @Expose
    private String countryCode;
    @SerializedName("Country_Name")
    @Expose
    private String countryName;

    public String getPOL() {
        return pOL;
    }

    public void setPOL(String pOL) {
        this.pOL = pOL;
    }

    public String getDEP() {
        return dEP;
    }

    public void setDEP(String dEP) {
        this.dEP = dEP;
    }

    public String getMODE() {
        return mODE;
    }

    public void setMODE(String mODE) {
        this.mODE = mODE;
    }

    public String get1TS() {
        return _1TS;
    }

    public void set1TS(String _1TS) {
        this._1TS = _1TS;
    }

    public String getLINE() {
        return lINE;
    }

    public void setLINE(String lINE) {
        this.lINE = lINE;
    }

    public String getSVC1() {
        return sVC1;
    }

    public void setSVC1(String sVC1) {
        this.sVC1 = sVC1;
    }

    public String get2TS() {
        return _2TS;
    }

    public void set2TS(String _2TS) {
        this._2TS = _2TS;
    }

    public String getSVC2() {
        return sVC2;
    }

    public void setSVC2(String sVC2) {
        this.sVC2 = sVC2;
    }

    public String getFINALPOD() {
        return fINALPOD;
    }

    public void setFINALPOD(String fINALPOD) {
        this.fINALPOD = fINALPOD;
    }

    public String getARR() {
        return aRR;
    }

    public void setARR(String aRR) {
        this.aRR = aRR;
    }

    public String getTOTTRANSIT() {
        return tOTTRANSIT;
    }

    public void setTOTTRANSIT(String tOTTRANSIT) {
        this.tOTTRANSIT = tOTTRANSIT;
    }

    public String getREMARK() {
        return rEMARK;
    }

    public void setREMARK(String rEMARK) {
        this.rEMARK = rEMARK;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

}
