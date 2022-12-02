package com.sifr.my.retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Model4successEid implements Serializable {

    //---------------
    //last inserted id
    @SerializedName("d")
    @Expose
    private String d;
    //token to identify (it will be token to verify from jwt, to reduce workload)
    @SerializedName("tk")
    @Expose
    private String tk;
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

    public String getTk() {
        return tk;
    }

    public void setTk(String tk) {
        this.tk = tk;
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