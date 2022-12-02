package com.sifr.my.retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Model4VehicleSeatsLst implements Serializable {

    //---------------
    @SerializedName("tk")//token
    @Expose
    private String tk;
    @SerializedName("m")//id of owner
    @Expose
    private int m;
    @SerializedName("ven")//vehicle number
    @Expose
    private String ven;
    @SerializedName("ve")//vehicle id
    @Expose
    private int ve;
    @SerializedName("c")//date of travel
    @Expose
    private String c;
    @SerializedName("o")//receipt no
    @Expose
    private String o;
    @SerializedName("g")//id from d as booker
    @Expose
    private int g;
    @SerializedName("v")//id from d as customer
    @Expose
    private int v;
    @SerializedName("k")//total amount
    @Expose
    private float k;
    @SerializedName("l")//received amount
    @Expose
    private float l;
    @SerializedName("n")//id of recieved medium 0=cash;1=UPI;2=cheque;3=DD;4=NEFT;5=RTGS;
    @Expose
    private int n;
    @SerializedName("p")//pickup point
    @Expose
    private int p;
    @SerializedName("r")//drop point
    @Expose
    private int r;


    @SerializedName("vc")//client country code
    @Expose
    private int vc;
    @SerializedName("vo")//client mobile no.
    @Expose
    private long vo;
    @SerializedName("vn")//client name
    @Expose
    private String vn;


    //---------------
    @SerializedName("mx")//collection for mx
    @Expose
    private Model4MXonly mx = new Model4MXonly();
    //---------------
    @SerializedName("uv")//model for client
    @Expose
    private Model4snglClient uv = new Model4snglClient();
    //---------------
    @SerializedName("vesLst")//vehicle seats list
    @Expose
    private List<Model4SeatsDtls> vesLst = null;
    //---------------
    @SerializedName("tsLst")//vehicle travel stop list
    @Expose
    private List<Model4TravelStops> tsLst = null;
    //---------------
    @SerializedName("luLst")//luggage list
    @Expose
    private List<Model4snglLuggage> luLst = null;


    //---------------
    @SerializedName("suc")
    @Expose
    private Boolean suc;
    @SerializedName("msg")
    @Expose
    private String msg;


    public String getTk() {
        return tk;
    }

    public void setTk(String tk) {
        this.tk = tk;
    }

    public int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
    }

    public String getVen() {
        return ven;
    }

    public void setVen(String ven) {
        this.ven = ven;
    }

    public int getVe() {
        return ve;
    }

    public void setVe(int ve) {
        this.ve = ve;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getO() {
        return o;
    }

    public void setO(String o) {
        this.o = o;
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

    public float getK() {
        return k;
    }

    public void setK(float k) {
        this.k = k;
    }

    public float getL() {
        return l;
    }

    public void setL(float l) {
        this.l = l;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public int getP() {
        return p;
    }

    public void setP(int p) {
        this.p = p;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
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

    public Model4MXonly getMx() {
        return mx;
    }

    public void setMx(Model4MXonly mx) {
        this.mx = mx;
    }

    public Model4snglClient getUv() {
        return uv;
    }

    public void setUv(Model4snglClient uv) {
        this.uv = uv;
    }

    public List<Model4SeatsDtls> getVesLst() {
        return vesLst;
    }

    public void setVesLst(List<Model4SeatsDtls> vesLst) {
        this.vesLst = vesLst;
    }

    public List<Model4TravelStops> getTsLst() {
        return tsLst;
    }

    public void setTsLst(List<Model4TravelStops> tsLst) {
        this.tsLst = tsLst;
    }

    public List<Model4snglLuggage> getLuLst() {
        return luLst;
    }

    public void setLuLst(List<Model4snglLuggage> luLst) {
        this.luLst = luLst;
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