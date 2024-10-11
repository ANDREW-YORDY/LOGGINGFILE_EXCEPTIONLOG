package com.adr.service;

import com.adr.config.CustomLogFormatter;
import com.adr.ilogging.ILogStorage;
import com.adr.ilogging.ILogger;
import java.io.IOException;
import java.util.logging.*;
import com.adr.config.FileHandlerManager;

/**
 * Clase avanzada para el manejo de logs de excepciones. 
 * Implementa el patrón Singleton y la interfaz AutoCloseable para un manejo eficiente de recursos.
 */
public class AdvancedExceptionLogger implements ILogger, AutoCloseable {

    private static AdvancedExceptionLogger instance;  // Instancia única de la clase (patrón Singleton)
    private final Logger logger;                      // Logger principal de Java
//    private final Handler fileHandler;                // Handlers para manejar la salida de los logs
    private final Handler consoleHandler;             // Handler para manejar la salida de los logs
    private final ILogStorage logStorage;             // Interfaz para almacenar los logs (permite flexibilidad en el almacenamiento)

    // Constructor privado para evitar la creación de instancias externas (Singleton)
    private AdvancedExceptionLogger(String logDirectory, ILogStorage logStorage) throws IOException {
        this.logger = Logger.getLogger(AdvancedExceptionLogger.class.getName());
        this.logStorage = logStorage;

        // Crear el FileHandler usando la clase FileHandlerManager.
        FileHandlerManager fileHandlerManager = new FileHandlerManager(logDirectory);
//        this.fileHandler = fileHandlerManager.createFileHandler();

        // Configurar el consoleHandler.
        this.consoleHandler = new ConsoleHandler();
        this.consoleHandler.setLevel(Level.ALL);
        this.consoleHandler.setFormatter(new CustomLogFormatter());

        // Añadir handlers
//        logger.addHandler(fileHandler);
        logger.addHandler(consoleHandler);
        logger.setUseParentHandlers(false);  // Evita que los logs se propaguen al logger padre.
    }

    /**
     * Método estático que regresa la instancia única de AdvancedExceptionLogger.
     * Si no existe, crea una nueva instancia.
     * @param logDirectory
     * @param logStorage
     * @return 
     * @throws java.io.IOException
     */
    public static synchronized AdvancedExceptionLogger getInstance(String logDirectory, ILogStorage logStorage) throws IOException {
        if (instance == null) {
            instance = new AdvancedExceptionLogger(logDirectory, logStorage);  // Crear la instancia si no existe.
        }
        return instance;  // Retornar la instancia única.
    }

    @Override
    public void logInfo(String message) {
        logger.log(Level.INFO, message);
        logStorage.store(Level.INFO, message);
    }

    @Override
    public void logWarning(String message) {
        logger.log(Level.WARNING, message);
        logStorage.store(Level.WARNING, message);
    }

    @Override
    public void logSevere(String message, Exception exception) {
        logger.log(Level.SEVERE, message, exception);  // Incluye la excepción con el stacktrace.
        logStorage.store(Level.SEVERE, message + ": " + exception.toString());
    }

    @Override
    public void close() {
        consoleHandler.close();
    }
}
