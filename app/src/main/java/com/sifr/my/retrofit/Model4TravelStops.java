package com.sifr.my.retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Model4TravelStops implements Serializable {

    //---------------
    @SerializedName("d")//travel stop id
    @Expose
    private int d;
    @SerializedName("i")//vehicle id
    @Expose
    private int i;
    @SerializedName("n")//stop name
    @Expose
    private String n;
    @SerializedName("pod")//pickup or drop 0=pickup;1=drop
    @Expose
    private int pod;


    //---------------
    @SerializedName("suc")
    @Expose
    private Boolean suc;
    @SerializedName("msg")
    @Expose
    private String msg;


    public int getD() {
        return d;
    }

    public void setD(int d) {
        this.d = d;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public String getN() {
        return n;
    }

    public void setN(String n) {
        this.n = n;
    }

    public int getPod() {
        return pod;
    }

    public void setPod(int pod) {
        this.pod = pod;
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