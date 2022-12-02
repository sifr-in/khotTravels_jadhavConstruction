package com.sifr.my.retrofit;

import java.util.HashMap;
import java.util.Map;

public class ModelSerProdJob {

    private String d;//id of bis=bill item in stock
    private String p;//id of po1=product
    private String n;//name of product from po1
    private String c;//image of product from po1
    private String me;//item measured in
    private String up;//units in package
    private String lm;//loose units measured in
    private String dc;//description
    private String ta;//total amount to be charged
    private String h;//mrp
    private int od;//id of above
    private boolean seltdSPJ;//true if product is selected
    private boolean ordStts;//order status 0=submitted;1=accepted;
    private float qty;//item quantity
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    public String getP() {
        return p;
    }

    public void setP(String p) {
        this.p = p;
    }

    public String getN() {
        return n;
    }

    public void setN(String n) {
        this.n = n;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getMe() {
        return me;
    }

    public void setMe(String me) {
        this.me = me;
    }

    public String getUp() {
        return up;
    }

    public void setUp(String up) {
        this.up = up;
    }

    public String getLm() {
        return lm;
    }

    public void setLm(String lm) {
        this.lm = lm;
    }

    public String getDc() {
        return dc;
    }

    public void setDc(String dc) {
        this.dc = dc;
    }

    public String getTa() {
        return ta;
    }

    public void setTa(String ta) {
        this.ta = ta;
    }

    public String getH() {
        return h;
    }

    public void setH(String h) {
        this.h = h;
    }

    public int getOd() {
        return od;
    }

    public void setOd(int od) {
        this.od = od;
    }

    public boolean isSeltdSPJ() {
        return seltdSPJ;
    }

    public void setSeltdSPJ(boolean seltdSPJ) {
        this.seltdSPJ = seltdSPJ;
    }

    public boolean isOrdStts() {
        return ordStts;
    }

    public void setOrdStts(boolean ordStts) {
        this.ordStts = ordStts;
    }

    public float getQty() {
        return qty;
    }

    public void setQty(float qty) {
        this.qty = qty;
    }

    public Map<String, Object> getAdditionalProperties() {
        return additionalProperties;
    }

    public void setAdditionalProperties(Map<String, Object> additionalProperties) {
        this.additionalProperties = additionalProperties;
    }
}