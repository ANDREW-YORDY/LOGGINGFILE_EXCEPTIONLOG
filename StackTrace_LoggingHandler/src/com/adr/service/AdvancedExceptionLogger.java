package com.adr.service;

import com.adr.configs.DuplicateMessageFilter;
import com.adr.ilogging.ILogStorage;
import com.adr.ilogging.ILogger;
import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.*;

/**
 * Clase avanzada para el manejo de logs de excepciones. Implementa el patrón
 * Singleton y la interfaz AutoCloseable para un manejo eficiente de recursos.
 */
public class AdvancedExceptionLogger implements ILogger, AutoCloseable {

    private static AdvancedExceptionLogger instance; // Instancia única de la clase (patrón Singleton)
    private Logger logger;                           // Logger principal de Java
    private final Handler fileHandler;               // Handlers para manejar la salida de los logs
    private final Handler consoleHandler;            // Handler para manejar la salida de los logs
    private final ILogStorage logStorage;            // Interfaz para almacenar los logs (permite flexibilidad en el almacenamiento)

    public AdvancedExceptionLogger(String logDirectory, ILogStorage logStorage) throws IOException {
        //Iniciaalizar Logger.
        this.logger = Logger.getLogger(AdvancedExceptionLogger.class.getName());
        this.logStorage = logStorage;

        //Crear directorio de log si no existen.
        Path logPath = Paths.get( System.getProperty("user.home"), "MyAppLogs", logDirectory );
        Files.createDirectories(logPath);

        // Configura los handlers.
        this.fileHandler    = configureFileHandler(logPath);
        this.consoleHandler = configureConsoleHandler();

        DuplicateMessageFilter filter = new DuplicateMessageFilter();
        fileHandler.setFilter(filter);
        consoleHandler.setFilter(filter);

        // Añade los handlers al logger
        logger.addHandler(fileHandler);
        logger.addHandler(consoleHandler);
        logger.setUseParentHandlers(false);  // Evita que los logs se propaguen al logger padre.

        System.out.println("Los logs se están guardando en: " + logPath.toAbsolutePath());
    }
    
    /**
     * Método para obtener la instancia única de la clase (patrón Singleton).
     * @param logDirectory
     * @param logStorage
     * @return 
     * @throws java.io.IOException
     */
    public static synchronized AdvancedExceptionLogger getInstance
        (String logDirectory, ILogStorage logStorage) throws IOException 
    {
        if (instance == null) {
            instance = new AdvancedExceptionLogger(logDirectory, logStorage);
        }
        return instance;
    }

    /**
     * Configura el FileHandler para escribir logs en un archivo.
     */
    private FileHandler configureFileHandler(Path logPath) throws IOException {
        //Nombre del archivo con fecha.
        String fileName = logPath.resolve("logs_" + LocalDate.now().
        format(DateTimeFormatter.ofPattern("yyyMMdd")) + ".log").toString();
        
        FileHandler fileHandler = new FileHandler(fileName, true);
        fileHandler.setLevel(Level.ALL);
        fileHandler.setFormatter(createFormatter());
        return fileHandler;
    }

    /**
     * Configura el ConsoleHandler para mostrar logs en la consola.
     */
    private ConsoleHandler configureConsoleHandler() 
    {
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.ALL);
        consoleHandler.setFormatter(createFormatter());
        return consoleHandler;
    }
    
    /**
     * Crea un formateador personalizado para los logs.
     */
    private Formatter createFormatter() 
    {
        return new Formatter() {
            @Override
            public String format(LogRecord record) {
                Throwable thrown = record.getThrown();
                StackTraceElement source = ( thrown != null &&  thrown.getStackTrace().length > 0 ) ? thrown.getStackTrace()[0] : new StackTraceElement("Unknown","Unknown","Unknown", -1);
            return String.format( "[%s] %s: %s at %s.%s(%s:%d)%n",LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),  
                    record.getLevel(),
                    record.getMessage(),
                    source.getClass(),
                    source.getMethodName(),
                    source.getFileName(),
                    source.getLineNumber()
                    );
            }
        };
        
        
    }

    /**
     * Registra un mensaje de log de nivel INFO.
     */
    @Override
    public void logInfo(String message) 
    {
        logger.log(Level.INFO, message);
        logStorage.store(Level.INFO, message);
    }

    /**
     * Registra un mensaje de log de nivel WARNING.
     */
    @Override
    public void logWarning(String message) 
    {
        logger.log(Level.WARNING, message);
        logStorage.store(Level.WARNING, message);
    }

    /**
     * Registra un mensaje de log de nivel SEVERE junto con la excepción.
     */
    @Override
    public void logSevere(String message, Exception exception) 
    {
        logger.log(Level.SEVERE, message);
        logStorage.store(Level.SEVERE, message +": "+ exception.toString());
    }

    /**
     * Cierra los handlers para liberar recursos. Este método se llama
     * automáticamente cuando se usa try-with-resources.
     */
    @Override
    public void close()
    {
        fileHandler.close();
        consoleHandler.close();
    }
    
    

}
