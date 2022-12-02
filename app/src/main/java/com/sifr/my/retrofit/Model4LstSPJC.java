package com.sifr.my.retrofit;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Model4LstSPJC {

    private List<ModelSerProdJob> dat1 = null;
    private Boolean suc;
    private String msg;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public List<ModelSerProdJob> getDat1() {
        return dat1;
    }

    public void setDat1(List<ModelSerProdJob> dat1) {
        this.dat1 = dat1;
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

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}