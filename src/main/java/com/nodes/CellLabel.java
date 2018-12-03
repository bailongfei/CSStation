package com.nodes;

import com.entities.CustomerQueueJCRecording;
import javafx.scene.control.Label;

public class CellLabel extends Label {

    public CellLabel(CustomerQueueJCRecording customerQueueJCRecording,String labelText){
        if (customerQueueJCRecording.getCustomerType().equals("2")){
            setStyle("-fx-text-fill: red;-fx-background-color: rgb(0,0,0,.0);");
        }
        String status = customerQueueJCRecording.getStatusType();
        if (status.equals("4")||status.equals("5")){
            setStyle("-fx-text-fill: #5db92f;-fx-background-color: rgb(0,0,0,.0);");
        }
        setText(labelText);
    }

}
