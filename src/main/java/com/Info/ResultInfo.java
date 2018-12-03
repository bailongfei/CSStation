package com.Info;

public class ResultInfo<T> {

    private Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    private String information;

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    private T t;

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }
}
