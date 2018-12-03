package com.utils;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

class Ticket extends AnchorPane implements Initializable {

    @FXML
    private Label queueNoLabel;
    @FXML
    private Label srvGroupNameLabel;
    @FXML
    private Label printTicketDate;

    private void loadXml(){
        FXMLLoader loader = new FXMLLoader(Ticket.class.getResource("/fxml/TicketPane.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    Ticket(String queueNo,String srvGroupName){
        loadXml();
        queueNoLabel.setText(queueNo);
        srvGroupNameLabel.setText(srvGroupName);
        String dateString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        printTicketDate.setText(dateString);
    }

}
