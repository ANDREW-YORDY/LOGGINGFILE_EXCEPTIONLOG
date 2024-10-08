package com.adr.service;

import com.adr.ilogging.ILogStorage;
import com.adr.ilogging.ILogger;
import java.io.IOException;
import java.util.logging.*;
import com.adr.config.FileHandlerManager;

/**
 * Clase avanzada para el manejo de logs de excepciones. Implementa el patrón
 * Singleton y la interfaz AutoCloseable para un manejo eficiente de recursos.
 */
public class AdvancedExceptionLogger implements ILogger, AutoCloseable 
{

    private static AdvancedExceptionLogger instance; // Instancia única de la clase (patrón Singleton)
    private Logger logger;                           // Logger principal de Java
    private final Handler fileHandler;               // Handlers para manejar la salida de los logs
    private final Handler consoleHandler;            // Handler para manejar la salida de los logs
    private final ILogStorage logStorage;            // Interfaz para almacenar los logs (permite flexibilidad en el almacenamiento)

    public AdvancedExceptionLogger(String logDirectory, ILogStorage logStorage) throws IOException 
    {
        this.logger     = Logger.getLogger(AdvancedExceptionLogger.class.getName());
        this.logStorage = logStorage;
        
        // Crear el FileHandler usando la clase FileHandlerManager.
        FileHandlerManager fileHandlerManager = new FileHandlerManager(logDirectory);
        FileHandler fileHandler1 = fileHandlerManager.createFileHandler();
        
        //Configurar el consoleHandler.
        this.consoleHandler  = new ConsoleHandler();
        /***********Continuar**********/
        
        return null;
    }

    @Override
    public void logInfo(String message) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void logWarning(String message) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void logSevere(String message, Exception exception) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void close() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
