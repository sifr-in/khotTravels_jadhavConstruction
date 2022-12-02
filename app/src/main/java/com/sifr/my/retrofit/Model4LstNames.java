package com.sifr.my.retrofit;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Model4LstNames {

    private List<Model4names> lstnm = null;
    private Boolean suc;
    private String msg;

    public List<Model4names> getLstnm() {
        return lstnm;
    }

    public void setLstnm(List<Model4names> lstnm) {
        this.lstnm = lstnm;
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