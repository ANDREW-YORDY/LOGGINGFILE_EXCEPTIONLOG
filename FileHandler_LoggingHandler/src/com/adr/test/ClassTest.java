package com.adr.test;

import com.adr.service.AdvancedExceptionLogger;
import com.adr.service.ExceptionLoggerService;

public class ClassTest {

    ExceptionLoggerService ctrService = new ExceptionLoggerService();
//    AdvancedExceptionLogger ctrLogger = new AdvancedExceptionLogger();

    public static void runTest1() {
        try {
            induceException();
        } catch (Exception e) {
            //method to print the stack trace on the console.
            e.printStackTrace();
        }
//        ctrService.showStoredException();
//        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    public static void runTest2() {

        try {
//            throw new Exception("Error de prueba");
            induceException();
        } catch (Exception e) {
            StackTraceElement[] stackTrace = e.getStackTrace();
            for (StackTraceElement element : stackTrace) {
                System.out.println("Clase:   " + element.getClassName());
                System.out.println("Método:  " + element.getMethodName());
                System.out.println("Archivo: " + element.getFileName());
                System.out.println("Línea:   " + element.getLineNumber());
                System.out.println("----->   ");
            }
        }
//        ctrService.showStoredException();
    }

    private static void induceException() throws Exception {
        int[] numbers = {1, 2, 3};
        System.out.println(numbers[3]);
    }

}
