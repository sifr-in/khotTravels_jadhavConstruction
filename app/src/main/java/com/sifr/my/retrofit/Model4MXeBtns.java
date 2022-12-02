package com.sifr.my.retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Model4MXeBtns implements Serializable {
    //---------------
    @SerializedName("mx")
    @Expose
    private Model4MXonly mx = null;

    @SerializedName("btn")
    @Expose
    private List<Model4ButtonsEPermissions> btn = null;

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

    public List<Model4ButtonsEPermissions> getBtn() {
        return btn;
    }

    public void setBtn(List<Model4ButtonsEPermissions> btn) {
        this.btn = btn;
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