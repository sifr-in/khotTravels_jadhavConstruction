package com.sifr.my.retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Model4snglLuggage implements Serializable {

  //---------------
  @SerializedName("d")//id from vel_[]
  @Expose
  private int d;
  @SerializedName("l")//luggage type id=0=don't know;1=pakit;2=pishwi;3=box;4=gon;
  @Expose
  private int l;
  @SerializedName("q")//quantity
  @Expose
  private float q;
  @SerializedName("c")//amount
  @Expose
  private float c;


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

  public int getL() {
    return l;
  }

  public void setL(int l) {
    this.l = l;
  }

  public float getQ() {
    return q;
  }

  public void setQ(float q) {
    this.q = q;
  }

  public float getC() {
    return c;
  }

  public void setC(float c) {
    this.c = c;
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