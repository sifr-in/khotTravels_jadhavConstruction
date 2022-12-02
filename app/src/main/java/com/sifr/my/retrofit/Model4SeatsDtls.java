package com.sifr.my.retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

public class Model4SeatsDtls {

    @SerializedName("d")
    @Expose
    private String d;//id of seat from ves
    @SerializedName("i")
    @Expose
    private String i;//id of vehicle
    @SerializedName("p")
    @Expose
    private String p;//position on seats grid
    @SerializedName("b")
    @Expose
    private String b;//display or hide
    @SerializedName("a")
    @Expose
    private String a;//seat number
    @SerializedName("g")
    @Expose
    private int g;//gender of occupier;0=don't know;1=male;2=female;
    @SerializedName("v")
    @Expose
    private int v;//id from d as seat occupier;
    @SerializedName("s")
    @Expose
    private String s;//status 0=available;1=booked;2=occupied;

    //---------------
    @SerializedName("r")//status of seat on given date
    @Expose
    private String r;//id vb_[m] vehicle booked table


    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    public String getI() {
        return i;
    }

    public void setI(String i) {
        this.i = i;
    }

    public String getP() {
        return p;
    }

    public void setP(String p) {
        this.p = p;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getV() {
        return v;
    }

    public void setV(int v) {
        this.v = v;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public String getR() {
        return r;
    }

    public void setR(String r) {
        this.r = r;
    }
}