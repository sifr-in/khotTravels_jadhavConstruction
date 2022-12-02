package com.sifr.my.retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Model4lstPrgrsRpt implements Serializable {

    //---------------
    @SerializedName("mx")
    @Expose
    private Model4MXonly mx = null;
    //---------------
    @SerializedName("lst1")//list of progress report
    @Expose
    private List<Model4snglProgrsRprtOfSite> lst1 = null;
    //---------------
    @SerializedName("suc")
    @Expose
    private Boolean suc;
    @SerializedName("msg")
    @Expose
    private String msg;


    public Model4MXonly getMx() {
        return mx;
    }

    public void setMx(Model4MXonly mx) {
        this.mx = mx;
    }

    public List<Model4snglProgrsRprtOfSite> getLst1() {
        return lst1;
    }

    public void setLst1(List<Model4snglProgrsRprtOfSite> lst1) {
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