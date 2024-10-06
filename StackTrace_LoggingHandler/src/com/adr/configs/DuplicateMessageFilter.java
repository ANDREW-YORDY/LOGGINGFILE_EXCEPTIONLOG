package com.adr.configs;

import java.util.logging.Filter;
import java.util.logging.LogRecord;

/**
 * Filtro para evitar la duplicación de mensajes de log.
 */
public class DuplicateMessageFilter implements Filter
{
    
    private String lastMessage = ""; // Almacena el último mensaje registrado// Almacena el último mensaje registrado

    
    /**
     * Revisa si el mensaje de log es duplicado.
     *
     * @param record Mensaje de log
     * @return true si el mensaje no es duplicado, false en caso contrario
     */
    @Override
    public boolean isLoggable(LogRecord record) {
        if ( record.getMessage().equals(lastMessage) )
        {
            return false;                  // Si es duplicado, no se registra
        }
        lastMessage = record.getMessage(); // Actualizamos el último mensaje registrado
        return true; // Permitimos el registro si no es duplicado.
    }
    
    
    
}
