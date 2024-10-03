package com.adr.service;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.MemoryHandler;


public class ExceptionLogger 
{
    
    private final MemoryHandler memoryHandler;
    private Logger logger;
    
    public ExceptionLogger()
    {
        logger = Logger.getLogger(ExceptionLogger.class.getName());
        memoryHandler = new MemoryHandler(new ConsoleHandler(), 100, Level.SEVERE );
    }
    
    public void logException( String excepMessage )
    {
        logger.severe(excepMessage);
    }
    
}
