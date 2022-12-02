package com.sifr.my.retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Model4MXonly implements Serializable {
    //TODO
    //change this to ModelMEeMF only and create separate ModelUXeVF
    //---------------
    @SerializedName("ap")//0=website;1=android=2=iOS;...
    @Expose
    private final int ap=1;
    @SerializedName("d")//id in d of specified m
    @Expose
    private int d;
    @SerializedName("od")//id in d of specified m
    @Expose
    private int od;
    @SerializedName("mf")//franchiser of (owner) id in d
    @Expose
    private int mf;
    //---------------
    @SerializedName("mfcl")//collection for mf
    @Expose
    private Model4MXonly mfcl = null;
    @SerializedName("m")//owner id in m
    @Expose
    private int m;
    @SerializedName("mc")//owner country code
    @Expose
    private int mc;
    @SerializedName("mo")//owner mobile no.
    @Expose
    private long mo;
    @SerializedName("e")//owner company no. if 0 individual
    @Expose
    private int e;
    @SerializedName("vf")//franchiser of (client) id in d
    @Expose
    private int vf;
    @SerializedName("vfcl")//collection for vf
    @Expose
    private Model4MXonly vfcl = null;
    @SerializedName("u")//company of v (example guard belonging to this u), id in m
    @Expose
    private int u;
    @SerializedName("uc")//company of v (example guard belonging to this u), country code
    @Expose
    private int uc;
    @SerializedName("uo")//company of v (example guard belonging to this u), mobile no.
    @Expose
    private long uo;
    @SerializedName("g")//company of v (example guard belonging to this u), company no.
    @Expose
    private int g;
    @SerializedName("v")//client id in m
    @Expose
    private int v;
    @SerializedName("vc")//client country code
    @Expose
    private int vc;
    @SerializedName("vo")//client mobile no.
    @Expose
    private long vo;
    @SerializedName("vn")//client name
    @Expose
    private String vn;
    @SerializedName("x")//id of relation r_[m] with base table q.wl
    @Expose
    private int x;
    @SerializedName("y")//id of logger
    @Expose
    private int y;
    @SerializedName("z")//id of relative logger
    @Expose
    private int z;
    @SerializedName("tk")//token
    @Expose
    private String tk;
    @SerializedName("dt")//date used for miscellaneous purposes
    @Expose
    private String dt;
    @SerializedName("ixd")//int ID used for miscellaneous purposes
    @Expose
    private int ixd;
    @SerializedName("ixd2")//int ID used for miscellaneous purposes
    @Expose
    private int ixd2;
    @SerializedName("str")//String used for miscellaneous purposes
    @Expose
    private String str;
    @SerializedName("str2")//String used for miscellaneous purposes
    @Expose
    private String str2;


    //---------------
    @SerializedName("suc")
    @Expose
    private Boolean suc;
    @SerializedName("msg")
    @Expose
    private String msg;


    public int getAp() {
        return ap;
    }

    public int getD() {
        return d;
    }

    public void setD(int d) {
        this.d = d;
    }

    public int getOd() {
        return od;
    }

    public void setOd(int od) {
        this.od = od;
    }

    public int getMf() {
        return mf;
    }

    public void setMf(int mf) {
        this.mf = mf;
    }

    public Model4MXonly getMfcl() {
        return mfcl;
    }

    public void setMfcl(Model4MXonly mfcl) {
        this.mfcl = mfcl;
    }

    public int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
    }

    public int getMc() {
        return mc;
    }

    public void setMc(int mc) {
        this.mc = mc;
    }

    public long getMo() {
        return mo;
    }

    public void setMo(long mo) {
        this.mo = mo;
    }

    public int getE() {
        return e;
    }

    public void setE(int e) {
        this.e = e;
    }

    public int getVf() {
        return vf;
    }

    public void setVf(int vf) {
        this.vf = vf;
    }

    public Model4MXonly getVfcl() {
        return vfcl;
    }

    public void setVfcl(Model4MXonly vfcl) {
        this.vfcl = vfcl;
    }

    public int getU() {
        return u;
    }

    public void setU(int u) {
        this.u = u;
    }

    public int getUc() {
        return uc;
    }

    public void setUc(int uc) {
        this.uc = uc;
    }

    public long getUo() {
        return uo;
    }

    public void setUo(long uo) {
        this.uo = uo;
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

    public int getVc() {
        return vc;
    }

    public void setVc(int vc) {
        this.vc = vc;
    }

    public long getVo() {
        return vo;
    }

    public void setVo(long vo) {
        this.vo = vo;
    }

    public String getVn() {
        return vn;
    }

    public void setVn(String vn) {
        this.vn = vn;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public String getTk() {
        return tk;
    }

    public void setTk(String tk) {
        this.tk = tk;
    }

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    public int getIxd() {
        return ixd;
    }

    public void setIxd(int ixd) {
        this.ixd = ixd;
    }

    public int getIxd2() {
        return ixd2;
    }

    public void setIxd2(int ixd2) {
        this.ixd2 = ixd2;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
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

    public String getStr2() {
        return str2;
    }

    public void setStr2(String str2) {
        this.str2 = str2;
    }
}