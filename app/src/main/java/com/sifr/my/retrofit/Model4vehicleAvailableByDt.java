package com.sifr.my.retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Model4vehicleAvailableByDt implements Serializable {

    //---------------
    @SerializedName("va")//id of this table
    @Expose
    private String va;
    @SerializedName("m")//id m
    @Expose
    private String m;
    @SerializedName("i")//id of vehicle
    @Expose
    private String i;
    @SerializedName("c")//date of travel
    @Expose
    private String c;
    @SerializedName("l")//id from start stop name
    @Expose
    private String l;
    @SerializedName("ln")//name from start stop name
    @Expose
    private String ln;
    @SerializedName("g")//id to end stop name
    @Expose
    private String g;
    @SerializedName("gn")//name to end stop name
    @Expose
    private String gn;
    @SerializedName("vn")//number of the vehicle
    @Expose
    private String vn;

    @SerializedName("mx")//collection for mf
    @Expose
    private Model4MXonly mx;

    //---------------
    @SerializedName("suc")
    @Expose
    private Boolean suc;
    @SerializedName("msg")
    @Expose
    private String msg;


    public String getVa() {
        return va;
    }

    public void setVa(String va) {
        this.va = va;
    }

    public String getM() {
        return m;
    }

    public void setM(String m) {
        this.m = m;
    }

    public String getI() {
        return i;
    }

    public void setI(String i) {
        this.i = i;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getL() {
        return l;
    }

    public void setL(String l) {
        this.l = l;
    }

    public String getLn() {
        return ln;
    }

    public void setLn(String ln) {
        this.ln = ln;
    }

    public String getG() {
        return g;
    }

    public void setG(String g) {
        this.g = g;
    }

    public String getGn() {
        return gn;
    }

    public void setGn(String gn) {
        this.gn = gn;
    }

    public String getVn() {
        return vn;
    }

    public void setVn(String vn) {
        this.vn = vn;
    }

    public Model4MXonly getMx() {
        return mx;
    }

    public void setMx(Model4MXonly mx) {
        this.mx = mx;
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