package com.sifr.my.retrofit;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class Model4snglMch implements Serializable {
    //---------------
    @SerializedName("d")//id of machine
    @Expose
    private int d;
    @SerializedName("n")//name of machine
    @Expose
    private String n;
    @SerializedName("a")//total number of machines
    @Expose
    private int a;
    @SerializedName("h")//date of machine number change
    @Expose
    private int h;
    @SerializedName("b")//id of machine
    @Expose
    private int b;
    @SerializedName("c")//id of site
    @Expose
    private int c;
    @SerializedName("g")//total number of machines on site
    @Expose
    private int g;
    @SerializedName("ixd")//for additional use; e.g. id of site to transfer from
    @Expose
    private int ixd;
    @SerializedName("mx")//collection for mf
    @Expose
    private Model4MXonly mx = null;


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

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getIxd() {
        return ixd;
    }

    public void setIxd(int ixd) {
        this.ixd = ixd;
    }

    public Model4MXonly getMx() {
        return mx;
    }

    public void setMx(Model4MXonly mx) {
        this.mx = mx;
    }
}