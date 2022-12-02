package com.sifr.my.retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Model4vehicle implements Serializable {


    @SerializedName("mx")//collection for mx
    @Expose
    private Model4MXonly mx = null;
    //---------------
    @SerializedName("d")//id of vehicle
    @Expose
    private String d;
    @SerializedName("n")//number of vehicle
    @Expose
    private String n;
    @SerializedName("mm")//m of owner of vehicle
    @Expose
    private String mm;

    //---------------
    @SerializedName("suc")
    @Expose
    private Boolean suc;
    @SerializedName("msg")
    @Expose
    private String msg;


    public Model4MXonly getMx() {
        return mx;
    }

    public void setMx(Model4MXonly mx) {
        this.mx = mx;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    public String getN() {
        return n;
    }

    public void setN(String n) {
        this.n = n;
    }

    public String getMm() {
        return mm;
    }

    public void setMm(String mm) {
        this.mm = mm;
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