package com.adr.service;


public class ExceptionLoggerControler {

    public void runException() {

        ExceptionLoggerService ctrService = new ExceptionLoggerService();
        ExceptionLogger ctrLogger = new ExceptionLogger();

        try {
            induceException();
        } catch (Exception e) {
            String exceptionMessage = "ERROR!!: " + e.toString();
            ctrService.addException(exceptionMessage);
            ctrLogger.ctrLogger(exceptionMessage);
        }
        ctrService.showStoredException();
    }

    private static void induceException() throws Exception 
    {
        int[] numbers = {1, 2, 3};
        System.out.println(numbers[3]);
    }

}
