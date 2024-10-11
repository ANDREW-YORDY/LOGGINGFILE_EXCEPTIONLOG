package com.adr.app;

import com.adr.service.ExceptionLoggerControler;

public class AppRun {

    public static void main(String[] args) 
    {
        ExceptionLoggerControler ctrLogger = new ExceptionLoggerControler();
        ctrLogger.runException();       
        
//        System.out.println("PrintStackTrace*: \n");
//        ClassTest.runTest1();
//        ClassTest.runTest2();
        
    }
}
