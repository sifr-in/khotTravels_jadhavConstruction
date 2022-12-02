package com.sifr.my.retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Model4ButtonsEPermissions {

    @SerializedName("d")//id of permission
    @Expose
    private int d;
    @SerializedName("r")//id of m as assigner (to know how many more this y can assign below)
    @Expose
    private int r;
    @SerializedName("n")//number of pages this y can assign below
    @Expose
    private int n;
    @SerializedName("l")//personal page label
    @Expose
    private String l;
    @SerializedName("di")//(pr table) 1=display as page (will show button)
    @Expose
    private int di;
    @SerializedName("lb")//(pr table) page label by sifr
    @Expose
    private String lb;
    @SerializedName("xp")//(pr table) page label explanation
    @Expose
    private String xp;
    @SerializedName("h")//permitted for 0=website;1=android;2=iOS
    @Expose
    private int h;
    @SerializedName("w")//id of page=g in this table as parent id;
    @Expose
    private int w;
    @SerializedName("g")//id of page from pr table
    @Expose
    private int g;
    @SerializedName("e")//company number
    @Expose
    private int e;
    @SerializedName("m")//owner m id of this page
    @Expose
    private int m;
    @SerializedName("u")//client company m id
    @Expose
    private int u;
    @SerializedName("v")//client indi m id
    @Expose
    private int v;
    @SerializedName("x")//client relation id
    @Expose
    private int x;
    @SerializedName("y")//logger indi m id
    @Expose
    private int y;
    @SerializedName("yc")//loggers country code
    @Expose
    private int yc;
    @SerializedName("yo")//loggers mobile no
    @Expose
    private long yo;
    @SerializedName("yn")//name of the logger owner
    @Expose
    private String yn;
    @SerializedName("z")//client relation id
    @Expose
    private int z;
    @SerializedName("o")//page locking status: 0=page of em can be used by single uvx; 1=page of em can be used by all uvx (single yz); 2=page of em can be used by all uvx (all yz);
    @Expose
    private int o;
    @SerializedName("p")//when yz=0=perms of page: 0=if yz logged in; 1=even if not logged in; 3=if uvx has x>0 means the em has accepted him on specific role;
    @Expose
    private int p;
    @SerializedName("k")//token of uv to use this page. it will be null if not in use.
    @Expose
    private String k;
    @SerializedName("a")//permission on add 0=not permitted yet; 1=self records; 2=loggers and filled by logger; 3=all records; 4=records in range (from range table);
    @Expose
    private int a;
    @SerializedName("i")//permission to view 0=not permitted yet; 1=self records; 2=loggers and filled by logger; 3=all records; 4=records in range (from range table);
    @Expose
    private int i;
    @SerializedName("b")//permission on edit 0=not permitted yet; 1=self records; 2=loggers and filled by logger; 3=all records; 4=records in range (from range table);
    @Expose
    private int b;
    @SerializedName("c")//permission on delete 0=not permitted yet; 1=self records; 2=loggers and filled by logger; 3=all records; 4=records in range (from range table);
    @Expose
    private int c;
    @SerializedName("q")//permitted till this date time
    @Expose
    private String q;
    @SerializedName("t")//insert/updated onat this will be used to know when was token updated
    @Expose
    private String t;
    @SerializedName("s")//status of permission
    @Expose
    private int s;


    public int getD() {
        return d;
    }

    public void setD(int d) {
        this.d = d;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public String getL() {
        return l;
    }

    public void setL(String l) {
        this.l = l;
    }

    public int getDi() {
        return di;
    }

    public void setDi(int di) {
        this.di = di;
    }

    public String getLb() {
        return lb;
    }

    public void setLb(String lb) {
        this.lb = lb;
    }

    public String getXp() {
        return xp;
    }

    public void setXp(String xp) {
        this.xp = xp;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getE() {
        return e;
    }

    public void setE(int e) {
        this.e = e;
    }

    public int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
    }

    public int getU() {
        return u;
    }

    public void setU(int u) {
        this.u = u;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public int getV() {
        return v;
    }

    public void setV(int v) {
        this.v = v;
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

    public int getYc() {
        return yc;
    }

    public void setYc(int yc) {
        this.yc = yc;
    }

    public long getYo() {
        return yo;
    }

    public void setYo(long yo) {
        this.yo = yo;
    }

    public String getYn() {
        return yn;
    }

    public void setYn(String yn) {
        this.yn = yn;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public int getO() {
        return o;
    }

    public void setO(int o) {
        this.o = o;
    }

    public int getP() {
        return p;
    }

    public void setP(int p) {
        this.p = p;
    }

    public String getK() {
        return k;
    }

    public void setK(String k) {
        this.k = k;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
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

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public String getT() {
        return t;
    }

    public void setT(String t) {
        this.t = t;
    }

    public int getS() {
        return s;
    }

    public void setS(int s) {
        this.s = s;
    }
}