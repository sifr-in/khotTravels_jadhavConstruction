package com.sifr.my.retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Model4lstXp implements Serializable {
    //---------------
    @SerializedName("lst1")//list of expenditure
    @Expose
    private List<Model4snglXp> lst1 = null;
    //---------------
    @SerializedName("suc")
    @Expose
    private Boolean suc;
    @SerializedName("msg")
    @Expose
    private String msg;


    public List<Model4snglXp> getLst1() {
        return lst1;
    }

    public void setLst1(List<Model4snglXp> lst1) {
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