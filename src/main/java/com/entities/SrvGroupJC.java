package com.entities;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SrvGroupJC {

    private StringProperty srvGroupId;
    private StringProperty srvGroupName;
    private StringProperty srvGroupCode;
    private StringProperty srvGroupLetter;

    public void setSrvGroupId(String srvGroupId) {
        this.srvGroupId = new SimpleStringProperty(srvGroupId);
    }

    public void setSrvGroupName(String srvGroupName) {
        this.srvGroupName = new SimpleStringProperty(srvGroupName);
    }

    public void setSrvGroupCode(String srvGroupCode) {
        this.srvGroupCode = new SimpleStringProperty(srvGroupCode);
    }

    public void setSrvGroupLetter(String srvGroupLetter) {
        this.srvGroupLetter = new SimpleStringProperty(srvGroupLetter);
    }

    public String getSrvGroupId() {
        return srvGroupId.get();
    }

    public StringProperty srvGroupIdProperty() {
        return srvGroupId;
    }

    public String getSrvGroupName() {
        return srvGroupName.get();
    }

    public StringProperty srvGroupNameProperty() {
        return srvGroupName;
    }

    public String getSrvGroupCode() {
        return srvGroupCode.get();
    }

    public StringProperty srvGroupCodeProperty() {
        return srvGroupCode;
    }

    public String getSrvGroupLetter() {
        return srvGroupLetter.get();
    }

    public StringProperty srvGroupLetterProperty() {
        return srvGroupLetter;
    }
}
