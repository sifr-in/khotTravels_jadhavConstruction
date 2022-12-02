package com.sifr.my.retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Model4LstTravelStops implements Serializable {

    //---------------
    @SerializedName("tsLst")//vehicle travel stop list
    @Expose
    private List<Model4TravelStops> tsLst = null;

    //---------------
    @SerializedName("suc")
    @Expose
    private Boolean suc;
    @SerializedName("msg")
    @Expose
    private String msg;


    public List<Model4TravelStops> getTsLst() {
        return tsLst;
    }

    public void setTsLst(List<Model4TravelStops> tsLst) {
        this.tsLst = tsLst;
    }

    public Boolean getSuc() {
        return suc;
    }

    public void setSuc(Boolean suc) {
        this.suc = suc;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}