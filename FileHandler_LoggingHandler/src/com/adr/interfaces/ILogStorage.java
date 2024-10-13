package com.adr.ilogging;

import java.util.logging.Level;

/**
 * Interfaz para abstraer el almacenamiento de logs.
 * Permite flexibilidad para cambiar el método de almacenamiento en el futuro.
 */
public interface ILogStorage {
    
    void store( Level level, String message );
    
}
