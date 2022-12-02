package com.sifr.my.retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Model4snglClient implements Serializable {
  //---------------
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
  private Model4snglClient mfcl = null;
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
  @SerializedName("r")//don't confuse this with post (posts have specific names. this can have names like 'family', 'friend', etc.) id from wg as group/post id. If company has site and department it will here only. e.g. site1dept1clerk1
  @Expose
  private int r;
  @SerializedName("i")//owner company has enered this party as: party type 0=not defined;1=customer(taker from us)(r column from wr table);2=employee;3=franchisee;4=client;5=reference giver to us(last reference giver id. for others different table will be there.);6:subscriber;7=software service subscriber
  @Expose
  private int i;
  @SerializedName("vf")//franchiser of (client) id in d
  @Expose
  private int vf;
  @SerializedName("vfcl")//collection for vf
  @Expose
  private Model4snglClient vfcl = null;
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
  @SerializedName("q")//don't confuse this with post (posts have specific names. this can have names like 'family', 'friend', etc.) id from wg as group/post id. If company has site and department it will here only. e.g. site1dept1clerk1 this will be shown from specified m
  @Expose
  private int q;
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
  @SerializedName("w")//id of desigrole of this v(from u_ table) in this given u in this table.
  @Expose
  private int w;


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

  public Model4snglClient getMfcl() {
    return mfcl;
  }

  public void setMfcl(Model4snglClient mfcl) {
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

  public int getR() {
    return r;
  }

  public void setR(int r) {
    this.r = r;
  }

  public int getI() {
    return i;
  }

  public void setI(int i) {
    this.i = i;
  }

  public int getVf() {
    return vf;
  }

  public void setVf(int vf) {
    this.vf = vf;
  }

  public Model4snglClient getVfcl() {
    return vfcl;
  }

  public void setVfcl(Model4snglClient vfcl) {
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

  public int getQ() {
    return q;
  }

  public void setQ(int q) {
    this.q = q;
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

  public int getW() {
    return w;
  }

  public void setW(int w) {
    this.w = w;
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