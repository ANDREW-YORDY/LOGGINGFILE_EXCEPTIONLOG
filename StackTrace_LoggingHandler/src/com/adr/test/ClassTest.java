package com.adr.test;

import com.adr.service.ExceptionLogger;
import com.adr.service.ExceptionLoggerService;

public class ClassTest {

    ExceptionLoggerService ctrService = new ExceptionLoggerService();
    ExceptionLogger ctrLogger = new ExceptionLogger();

    public static void runTest() {
        
        try {
            induceException();
        } catch (Exception e) {
            //method to print the stack trace on the console.
            e.printStackTrace();
        }
//        ctrService.showStoredException();

    }

    private static void induceException() throws Exception {
        int[] numbers = {1, 2, 3};
        System.out.println(numbers[3]);
    }

}
