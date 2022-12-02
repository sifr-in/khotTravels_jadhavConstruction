package com.sifr.my.retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Model4snglStr implements Serializable {
  //---------------
  @SerializedName("d")//id of site
  @Expose
  private int d;
  @SerializedName("n")//id name of site
  @Expose
  private String n;

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
}