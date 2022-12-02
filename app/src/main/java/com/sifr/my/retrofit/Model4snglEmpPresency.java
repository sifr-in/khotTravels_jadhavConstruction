package com.sifr.my.retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Model4snglEmpPresency implements Serializable {

    //---------------
    @SerializedName("d")//id of this table ae_[]
    @Expose
    private String d;
    @SerializedName("g")//id of site
    @Expose
    private String g;
    @SerializedName("a")//date
    @Expose
    private String a;
    @SerializedName("v")//id from d as employee
    @Expose
    private String v;
    @SerializedName("vc")//employee country code
    @Expose
    private String vc;
    @SerializedName("vo")//client mobile no.
    @Expose
    private String vo;
    @SerializedName("vn")//employee name
    @Expose
    private String vn;
    @SerializedName("b")//work measured in 0=days;1=hours;2=week;3=month;4=year;
    @Expose
    private String b;
    @SerializedName("c")//total (b) units this person has worked
    @Expose
    private String c;
    @SerializedName("chg")//to know changes made in this object class and send those which are changed;
    @Expose
    private int chg;

    //---------------
    @SerializedName("suc")
    @Expose
    private Boolean suc;
    @SerializedName("msg")
    @Expose
    private String msg;


    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    public String getG() {
        return g;
    }

    public void setG(String g) {
        this.g = g;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
    }

    public String getVc() {
        return vc;
    }

    public void setVc(String vc) {
        this.vc = vc;
    }

    public String getVo() {
        return vo;
    }

    public void setVo(String vo) {
        this.vo = vo;
    }

    public String getVn() {
        return vn;
    }

    public void setVn(String vn) {
        this.vn = vn;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public int getChg() {
        return chg;
    }

    public void setChg(int chg) {
        this.chg = chg;
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