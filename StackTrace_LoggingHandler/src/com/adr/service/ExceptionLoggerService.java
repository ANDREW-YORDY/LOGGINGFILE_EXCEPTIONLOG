package com.adr.service;

import java.util.concurrent.CopyOnWriteArrayList;
//import com.adr.ihandler.IExceptionLogger;
import com.adr.ilogging.ILogStorage;
import java.util.*;
import java.util.logging.Level;


/**
 * Servicio para almacenar y mostrar excepciones.
 * Implementa ILogStorage para integrarse con AdvancedExceptionLogger.
 */
public class ExceptionLoggerService implements ILogStorage 
{

    // Lista thread-safe para almacenar mensajes de excepción.
    private final List<String> excepList = new CopyOnWriteArrayList<>(); // Lista thread-safe para almacenar mensajes de log

    /**
     * Almacena un mensaje de log con su nivel de severidad correspondiente.
     *
     * @param level Nivel de severidad del log
     * @param message Mensaje de log a almacenar
     */
    @Override
    public void store(Level level, String message) 
    {
        // Formatea el mensaje incluyendo el nivel de log y lo añade a la lista
        excepList.add(String.format("[%s] %s", level, message));
    }
    
        /**
     * Muestra todas las excepciones almacenadas.
     */
    public void showStoredException()
    {
        // Verificamos si hay errores almacenados
        if (excepList.isEmpty()) 
        {
            // Si no hay errores, mostramos este mensaje
            System.out.println("No hay errores almacenados.");  // Si no hay errores, mostramos un mensaje claro.
        } else {
            System.out.println("ERRORES ALMACENADOS TEMPORALMENTE: ");
            excepList.forEach((x) -> System.out.println(x)); // Iteramos sobre los errores.
        }
    }

    /**
     * Retorna una lista inmutable de excepciones.
     * @return Lista de excepciones almacenadas
     */
    // Se expone la lista solo con un método read-only.
    public List<String> getExceptionList() 
    {
        return Collections.unmodifiableList(excepList); // Protegemos la lista original contra modificaciones.
    }
    
    
    // ref: store():
//    @Override
//    public void addException(String excepMessage) 
//    {
//        // Agregamos el mensaje de la excepción a la lista.
//        excepList.add(excepMessage);
//    }


}
