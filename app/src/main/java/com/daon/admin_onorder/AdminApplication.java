package com.daon.admin_onorder;

import android.app.Application;
import android.util.Log;

import com.sam4s.printer.Sam4sPrint;

public class AdminApplication extends Application {
    private static Sam4sPrint printer = new Sam4sPrint();
    private static Sam4sPrint printer2 = new Sam4sPrint();

    public static void setPrinter(Sam4sPrint printer1){
        printer = printer1;
    }
    public static void setPrinter2(Sam4sPrint printer1){
        printer2 = printer1;
    }
    public static Sam4sPrint getPrinter(){
        return printer;
    }
    public static Sam4sPrint getPrinter2(){
        return printer2;
    }
}
