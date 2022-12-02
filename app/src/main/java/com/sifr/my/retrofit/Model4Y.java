package com.sifr.my.retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Model4Y implements Serializable {
    //this is not in MX as only id of y and z will be necessary when token is received.
    //---------------
    @SerializedName("yc")//loggers country code
    @Expose
    private int yc;
    @SerializedName("yo")//loggers mobile no
    @Expose
    private long yo;
    @SerializedName("y")//id of y
    @Expose
    private int y;
    @SerializedName("yn")//name of the logger owner
    @Expose
    private String yn;
    @SerializedName("zs")//relation string
    @Expose
    private String zs;
    @SerializedName("z")//id of relation
    @Expose
    private int z;
    @SerializedName("zn")//name of the logger relative
    @Expose
    private String zn;
    @SerializedName("pi")//password
    @Expose
    private String pi;
    @SerializedName("tk")//token
    @Expose
    private String tk;
    @SerializedName("ot")//otp sent to verify this mobile number (not z)
    @Expose
    private int ot;

    //---------------
    @SerializedName("suc")
    @Expose
    private Boolean suc;
    @SerializedName("msg")
    @Expose
    private String msg;


    public int getYc() {
        return yc;
    }

    public void setYc(int yc) {
        this.yc = yc;
    }

    public long getYo() {
        return yo;
    }

    public void setYo(long yo) {
        this.yo = yo;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getYn() {
        return yn;
    }

    public void setYn(String yn) {
        this.yn = yn;
    }

    public String getZs() {
        return zs;
    }

    public void setZs(String zs) {
        this.zs = zs;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public String getZn() {
        return zn;
    }

    public void setZn(String zn) {
        this.zn = zn;
    }

    public String getPi() {
        return pi;
    }

    public void setPi(String pi) {
        this.pi = pi;
    }

    public String getTk() {
        return tk;
    }

    public void setTk(String tk) {
        this.tk = tk;
    }

    public int getOt() {
        return ot;
    }

    public void setOt(int ot) {
        this.ot = ot;
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