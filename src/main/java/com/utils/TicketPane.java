package com.utils;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TicketPane {

    private Stage primaryStage;

    public TicketPane(String queueNo,String srvGroupName) {
        primaryStage = new Stage();
        Group group = new Group();
        CanvasPane c = new CanvasPane(458,500,queueNo,srvGroupName);
        group.getChildren().addAll(c);
        Scene scene = new Scene(group,458,500);
        primaryStage.setScene(scene);
        PrinterUtil.saveFile(group);
        PrinterUtil.print();
    }


    public void closeStage(){
        primaryStage.close();
    }

}
