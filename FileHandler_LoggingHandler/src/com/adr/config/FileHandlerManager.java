package com.adr.config;

import java.io.IOException;
import java.nio.file.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.logging.FileHandler;
import java.util.logging.Level;


/**
 * Clase encargada de la gestión del FileHandler y la creación de archivos de log.
 */
public class FileHandlerManager 
{
    
    private Path logPath;
    
    /**
     * Constructor que inicializa el directorio donde se almacenarán los logs.
     */
    public FileHandlerManager( String logDirectory ) throws IOException
    {
        // Crear directorio de log si no existe.
        this.logPath = Paths.get( System.getProperty("user.home"), "LogsJava", logDirectory );
        Files.createDirectories( logPath );
        
    }
    
    /**
     * Método que crea y devuelve un FileHandler configurado.
     * @return 
     * @throws java.io.IOException
     */
    public FileHandler createFileHandler() throws IOException
    {
        // Nombre del archivo con fecha.
        String fileName = logPath.resolve( "logs_" + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")) + ".log").toString();
        
        //Crear y configurar el FileHandler
        FileHandler fileHandler = new FileHandler( fileName, true );
        fileHandler.setLevel(Level.ALL);
        fileHandler.setFormatter( new CustomLogFormatter() ); // Usa un formateador personalizado.
        return fileHandler;       
    }
        
}
