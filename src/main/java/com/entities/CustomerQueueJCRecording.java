package com.entities;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CustomerQueueJCRecording {

    private String dataId;
    private StringProperty queueNo;
    private StringProperty srvGroupId;
    private StringProperty srvGroupName;
    private StringProperty customerName;
    private StringProperty customerId;
    private StringProperty srvCodeId;
    private StringProperty srvCodeName;
    private StringProperty statusType;
    private StringProperty statusTypeDesc;
    private StringProperty srvGroupLetter;
    private StringProperty customerType;
    private StringProperty customerTypeDesc;

    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }

    public String getCustomerTypeDesc() {
        setCustomerTypeDesc();
        return customerTypeDesc.get();
    }

    public StringProperty customerTypeDescProperty() {
        setCustomerTypeDesc();
        return customerTypeDesc;
    }

    public void setCustomerTypeDesc() {
        String desc = "";
        if (getCustomerType().equals("1")){
            desc = "普通";
        } else if (getCustomerType().equals("2")){
            desc = "急诊";
        }
        this.customerTypeDesc = new SimpleStringProperty(desc);
    }

    public String getCustomerType() {
        return customerType.get();
    }

    public StringProperty customerTypeProperty() {
        return customerType;
    }

    //number
    public void setCustomerType(String customerType) {
        this.customerType = new SimpleStringProperty(customerType);
    }



    public String getSrvGroupLetter() {
        return srvGroupLetter.get();
    }

    public StringProperty srvGroupLetterProperty() {
        return srvGroupLetter;
    }

    public void setSrvGroupLetter(String srvGroupLetter) {
        this.srvGroupLetter = new SimpleStringProperty(srvGroupLetter);
    }

    public String getQueueNo() {
        return queueNo.get();
    }

    public StringProperty queueNoProperty() {
        return queueNo;
    }

    public void setQueueNo(String queueNo) {
        this.queueNo = new SimpleStringProperty(queueNo);
    }

    public String getSrvGroupId() {
        return srvGroupId.get();
    }

    public StringProperty srvGroupIdProperty() {
        return srvGroupId;
    }

    public void setSrvGroupId(String srvGroupId) {
        this.srvGroupId = new SimpleStringProperty(srvGroupId);
    }

    public String getSrvGroupName() {
        return srvGroupName.get();
    }

    public StringProperty srvGroupNameProperty() {
        return srvGroupName;
    }

    public void setSrvGroupName(String srvGroupName) {
        this.srvGroupName = new SimpleStringProperty(srvGroupName);
    }

    public String getCustomerName() {
        return customerName.get();
    }

    public StringProperty customerNameProperty() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = new SimpleStringProperty(customerName);
    }

    public String getCustomerId() {
        return customerId.get();
    }

    public StringProperty customerIdProperty() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = new SimpleStringProperty(customerId);
    }

    public String getSrvCodeId() {
        return srvCodeId.get();
    }

    public StringProperty srvCodeIdProperty() {
        return srvCodeId;
    }

    public void setSrvCodeId(String srvCodeId) {
        this.srvCodeId = new SimpleStringProperty(srvCodeId);
    }

    public String getSrvCodeName() {
        return srvCodeName.get();
    }

    public StringProperty srvCodeNameProperty() {
        return srvCodeName;
    }

    public void setSrvCodeName(String srvCodeName) {
        this.srvCodeName = new SimpleStringProperty(srvCodeName);
    }

    public String getStatusType() {
        return statusType.get();
    }

    public StringProperty statusTypeProperty() {
        return statusType;
    }

    public void setStatusType(String statusType) {
        this.statusType = new SimpleStringProperty(statusType);
    }

    public String getStatusTypeDesc() {
        setStatusTypeDesc();
        return statusTypeDesc.get();
    }

    public StringProperty statusTypeDescProperty() {
        setStatusTypeDesc();
        return statusTypeDesc;
    }

    public void setStatusTypeDesc() {
        String statusTypeDesc = "开始等候";
        if (getStatusType().equals("3")){
            statusTypeDesc = "等候中";
        } else if (getStatusType().equals("4")){
            statusTypeDesc = "呼叫中";
        } else if (getStatusType().equals("5")){
            statusTypeDesc = "已呼叫";
        } else if (getStatusType().equals("6")){
            statusTypeDesc = "就诊中";
        } else if(getStatusType().equals("7")){
            statusTypeDesc = "结束";
        } else if (getStatusType().equals("12")){
            statusTypeDesc = "退号";
        } else {
            statusTypeDesc = "其他";
        }
        this.statusTypeDesc = new SimpleStringProperty(statusTypeDesc);
    }
}
