package com.adr.ilogging;

/**
 * Interfaz que define los métodos básicos para el logging.
 */
public interface ILogger {
    
    void logInfo( String message );
    void logWarning( String message );
    void logSevere( String message, Exception exception );
    
}
