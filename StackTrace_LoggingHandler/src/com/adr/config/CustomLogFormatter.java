package com.adr.config;

import java.util.logging.Formatter;
import java.util.logging.LogRecord;


/**
 * Formateador personalizado para los logs.
 */
public class CustomLogFormatter extends Formatter
{

    @Override
    public String format(LogRecord record) 
    {
        // Formato básico para logs normales.
        String logMessage = String.format( "%1$tF %1$tT %2$s %3$s%n",
                record.getMillis(),
                record.getLevel().getLocalizedName(),
                formatMessage(record)
        );
        
        // Si hay excepción, añadimos el stacktrace.
        if ( record.getThrown() != null )
        {
            StringBuilder sb = new StringBuilder( logMessage );
            for ( StackTraceElement element : record.getThrown().getStackTrace() )
            {
                sb.append( "\tat " ).append(element).append( System.lineSeparator() ) ;
            }
            return sb.toString();
        }
        
        return logMessage;
        
    }
    
}
