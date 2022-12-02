package com.sifr.my.retrofit;

import java.util.List;

public class Model4lstVehicleAvailableByDt {

    private List<Model4vehicleAvailableByDt> lst1 = null;
    private Boolean suc;
    private String msg;

    public List<Model4vehicleAvailableByDt> getLst1() {
        return lst1;
    }

    public void setLst1(List<Model4vehicleAvailableByDt> lst1) {
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