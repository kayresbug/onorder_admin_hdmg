package com.daon.admin_onorder;

import com.sam4s.printer.Sam4sPrint;

public class isPrinter {
    public Sam4sPrint setPrinter1(){
        Sam4sPrint sam4sPrint = new Sam4sPrint();
        try {
            Thread.sleep(400);
            sam4sPrint.openPrinter(Sam4sPrint.DEVTYPE_ETHERNET, "172.30.1.45", 9100);
            sam4sPrint.resetPrinter();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return sam4sPrint;
    }
    public Sam4sPrint setPrinter2(){
        Sam4sPrint sam4sPrint = new Sam4sPrint();
        try {
            Thread.sleep(400);
            sam4sPrint.openPrinter(Sam4sPrint.DEVTYPE_ETHERNET, "172.30.1.59", 9100);
            sam4sPrint.resetPrinter();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return sam4sPrint;
    }

    public void closePrint1(Sam4sPrint sam4sPrint){
        try {
            Thread.sleep(400);
            sam4sPrint.closePrinter();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    public void closePrint2(Sam4sPrint sam4sPrint){
        try {
            Thread.sleep(400);
            sam4sPrint.closePrinter();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
