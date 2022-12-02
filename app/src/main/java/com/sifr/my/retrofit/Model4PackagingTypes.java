package com.sifr.my.retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Model4PackagingTypes implements Serializable {

    //---------------
    @SerializedName("d")//packaging type id
    @Expose
    private int d;
    @SerializedName("n")//packaging type name
    @Expose
    private String n;
    @SerializedName("q")//quantity in package
    @Expose
    private int q;
    @SerializedName("r")//rate of this package
    @Expose
    private float r;
    @SerializedName("a")//amount of this package
    @Expose
    private float a;


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

    public String getN() {
        return n;
    }

    public void setN(String n) {
        this.n = n;
    }

    public int getQ() {
        return q;
    }

    public void setQ(int q) {
        this.q = q;
    }

    public float getR() {
        return r;
    }

    public void setR(float r) {
        this.r = r;
    }

    public float getA() {
        return a;
    }

    public void setA(float a) {
        this.a = a;
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