package com.sifr.my.retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Model4snglXp implements Serializable {
  //---------------
  @SerializedName("d")//id of expenditure
  @Expose
  private int d;
  @SerializedName("n")//id name of expenditure
  @Expose
  private String n;
  @SerializedName("a")//date of expenditure
  @Expose
  private String a;
  @SerializedName("b")//id of expenditure is b in xpq table
  @Expose
  private int b;
  @SerializedName("c")//id of site
  @Expose
  private int c;
  @SerializedName("g")//quantity of expenditure
  @Expose
  private float g;
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

  public String getA() {
    return a;
  }

  public void setA(String a) {
    this.a = a;
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

  public float getG() {
    return g;
  }

  public void setG(float g) {
    this.g = g;
  }

  public Model4MXonly getMx() {
    return mx;
  }

  public void setMx(Model4MXonly mx) {
    this.mx = mx;
  }
}