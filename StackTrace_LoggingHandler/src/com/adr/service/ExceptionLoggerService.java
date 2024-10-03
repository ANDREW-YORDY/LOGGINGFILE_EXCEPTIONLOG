package com.adr.service;

import com.adr.ihandler.IExceptionLogger;
import java.util.*;

public class ExceptionLoggerService implements IExceptionLogger {

    private final List<String> excepList = new ArrayList<>();

    @Override
    public void addException(String excepMessage) {
        excepList.add(excepMessage);
    }

    @Override
    public void showStoredException() {
        System.out.println("ERRORES ALMACENADOS TEMPORALMENTE: ");        
        excepList.forEach((x) -> System.out.println(x));
    }
    
    public List<String> getExceptionList()
    {
        return excepList;
    }

}
