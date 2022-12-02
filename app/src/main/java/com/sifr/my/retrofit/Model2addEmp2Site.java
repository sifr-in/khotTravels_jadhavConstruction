package com.sifr.my.retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Model2addEmp2Site implements Serializable {
  //---------------
  @SerializedName("d")//id of site
  @Expose
  private int d;
  @SerializedName("mx")//collection for mf
  @Expose
  private Model4MXonly mx = null;

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

  public Model4MXonly getMx() {
    return mx;
  }

  public void setMx(Model4MXonly mx) {
    this.mx = mx;
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