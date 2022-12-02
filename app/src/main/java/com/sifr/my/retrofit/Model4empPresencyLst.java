package com.sifr.my.retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Model4empPresencyLst {
    @SerializedName("mx")//collection for mf
    @Expose
    private Model4MXonly mx = null;
    private List<Model4snglEmpPresency> lst1 = null;//list of employees
    private Boolean suc;
    private String msg;


    public Model4MXonly getMx() {
        return mx;
    }

    public void setMx(Model4MXonly mx) {
        this.mx = mx;
    }

    public List<Model4snglEmpPresency> getLst1() {
        return lst1;
    }

    public void setLst1(List<Model4snglEmpPresency> lst1) {
        this.lst1 = lst1;
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