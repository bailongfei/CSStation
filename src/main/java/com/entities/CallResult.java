package com.entities;

public class CallResult {

    private Integer waitNum;

    private String srvGroupLetter;

    private Integer queueNo;

    private Integer rs;

    public CallResult() {
    }

    public Integer getWaitNum() {
        return waitNum;
    }

    public void setWaitNum(Integer waitNum) {
        this.waitNum = waitNum;
    }

    public String getSrvGroupLetter() {
        return srvGroupLetter;
    }

    public void setSrvGroupLetter(String srvGroupLetter) {
        this.srvGroupLetter = srvGroupLetter;
    }

    public Integer getQueueNo() {
        return queueNo;
    }

    public void setQueueNo(Integer queueNo) {
        this.queueNo = queueNo;
    }

    public Integer getRs() {
        return rs;
    }

    public void setRs(Integer rs) {
        this.rs = rs;
    }
}
