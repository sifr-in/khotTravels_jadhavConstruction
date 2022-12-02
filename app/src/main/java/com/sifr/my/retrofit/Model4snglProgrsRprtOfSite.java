package com.sifr.my.retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Model4snglProgrsRprtOfSite implements Serializable {
  //---------------
  @SerializedName("g")//id of site
  @Expose
  private int g;
  @SerializedName("a")//date of statement
  @Expose
  private String a;
  @SerializedName("n")//progress statement text
  @Expose
  private String n;


  public int getG() {
    return g;
  }

  public void setG(int g) {
    this.g = g;
  }

  public String getA() {
    return a;
  }

  public void setA(String a) {
    this.a = a;
  }

  public String getN() {
    return n;
  }

  public void setN(String n) {
    this.n = n;
  }
}