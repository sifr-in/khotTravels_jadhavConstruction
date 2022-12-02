package com.sifr.my.retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Model4snglSite implements Serializable {
  //---------------
  @SerializedName("d")//id of site
  @Expose
  private int d;
  @SerializedName("n")//id name of site
  @Expose
  private String n;
  @SerializedName("a")//date time work must be started on
  @Expose
  private String a;
  @SerializedName("b")//date time work must be complted
  @Expose
  private String b;
  @SerializedName("c")//date time work actually started
  @Expose
  private String c;
  @SerializedName("l")//email of the site
  @Expose
  private String l;
  @SerializedName("p")//percent of work completed
  @Expose
  private int p;
  @SerializedName("s")//status of the site;
  @Expose
  private int s;

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

  public String getN() {
    return n;
  }

  public void setN(String n) {
    this.n = n;
  }

  public String getA() {
    return a;
  }

  public void setA(String a) {
    this.a = a;
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

  public String getL() {
    return l;
  }

  public void setL(String l) {
    this.l = l;
  }

  public int getP() {
    return p;
  }

  public void setP(int p) {
    this.p = p;
  }

  public int getS() {
    return s;
  }

  public void setS(int s) {
    this.s = s;
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