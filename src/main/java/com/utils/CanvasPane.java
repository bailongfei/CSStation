package com.utils;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.text.SimpleDateFormat;
import java.util.Date;

class CanvasPane extends Canvas {


    private GraphicsContext gc;

    private void draw(String queueNo,String srvGroupName){
        gc = getGraphicsContext2D();
        paintInfo(queueNo,srvGroupName);
    }

    CanvasPane(double width,double height,String queueNo,String srvGroupName){
        super(width,height);
        draw(queueNo,srvGroupName);
    }

    private void paintInfo(String queueNo,String srvGroupName){
        gc.save();
        gc.setStroke(Color.BLACK);
        FontWeight fontWeight = FontWeight.BOLD;
        Font font = Font.font("SimHei",fontWeight, 60);
        gc.setFont(font);
        gc.fillText("华山医院北院",50,50);
        fontWeight = FontWeight.NORMAL;
        font = Font.font("SimHei",fontWeight, 40);
        gc.setFont(font);
        gc.fillText("排队就诊号",150,100);
        fontWeight = FontWeight.BOLD;
        font = Font.font("SimHei",fontWeight, 50);
        gc.setFont(font);
        gc.fillText(queueNo,180,150);

        fontWeight = FontWeight.BOLD;
        font = Font.font("SimHei",fontWeight, 50);
        gc.setFont(font);
        gc.fillText(srvGroupName,130,210);

        fontWeight = FontWeight.NORMAL;
        font = Font.font("SimHei",fontWeight, 30);
        gc.setFont(font);
        gc.fillText(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()),100,250);
        gc.strokeLine(5,260,545,260);
        fontWeight = FontWeight.NORMAL;
        font = Font.font("SimHei",fontWeight, 30);
        gc.setFont(font);
        gc.fillText("请注意您的就诊号码",100,300);
        gc.strokeLine(5,320,545,320);
        gc.fillText(" ",100,330);

    }


}
