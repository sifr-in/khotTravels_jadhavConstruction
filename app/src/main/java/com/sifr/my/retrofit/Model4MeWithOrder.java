package com.sifr.my.retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Model4MeWithOrder implements Serializable {

    //---------------
    @SerializedName("m")//id of m
    @Expose
    private int m;
    @SerializedName("e")//id of e
    @Expose
    private int e;
    @SerializedName("v")//id of e
    @Expose
    private int v;

    //---------------
    @SerializedName("psjLst")//id of product service job
    @Expose
    private List<ModelSerProdJob> psjLst = null;

    //---------------
    @SerializedName("suc")
    @Expose
    private Boolean suc;
    @SerializedName("msg")
    @Expose
    private String msg;


    public int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
    }

    public int getE() {
        return e;
    }

    public void setE(int e) {
        this.e = e;
    }

    public int getV() {
        return v;
    }

    public void setV(int v) {
        this.v = v;
    }

    public List<ModelSerProdJob> getPsjLst() {
        return psjLst;
    }

    public void setPsjLst(List<ModelSerProdJob> psjLst) {
        this.psjLst = psjLst;
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