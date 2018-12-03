package com.utils;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.WritableImage;

import javax.imageio.ImageIO;
import javax.print.*;
import javax.print.attribute.AttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.MediaPrintableArea;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.MediaTray;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

class PrinterUtil{

    static void print() {
        DocFlavor flavor = DocFlavor.INPUT_STREAM.PNG;
        PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
        AttributeSet attributeSet = new HashPrintRequestAttributeSet();
        attributeSet.add(MediaSizeName.ISO_A7);
        attributeSet.add(new MediaPrintableArea(0,0,500,500,MediaPrintableArea.MM));
        printRequestAttributeSet.addAll(attributeSet);
        PrintService printService = PrintServiceLookup.lookupDefaultPrintService();
        if (printService!=null) {
            System.out.println(printService.getName());
            DocPrintJob pj = printService.createPrintJob();
            try{
                File file = new File("Ticket.png");
                InputStream fis = new FileInputStream(file);
                SimpleDoc doc = new SimpleDoc(fis, flavor, null);
                pj.print(doc, printRequestAttributeSet);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }


    static void saveFile(Node view){
        WritableImage image = view.snapshot(new SnapshotParameters(), null);
        try {
            File f = new File("Ticket.png");
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", f);
            System.out.println("finish");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
