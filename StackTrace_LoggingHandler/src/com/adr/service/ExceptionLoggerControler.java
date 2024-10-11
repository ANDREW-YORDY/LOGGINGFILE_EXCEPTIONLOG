package com.adr.service;

import java.io.IOException;

/**
 * Clase controladora que maneja el flujo de ejecución de excepciones y logging.
 */
public class ExceptionLoggerControler {

    private static final String LOG_DIRECTORY = "LOGS.FILE"; // Carpeta para almacenar los logs

    /**
     * Ejecuta el proceso de inducir una excepción y registrarla.
     */
    public void runException() {
        ExceptionLoggerService logStorage = new ExceptionLoggerService(); // Servicio que almacena los logs.
//        AdvancedExceptionLogger        ctrLogger  = new AdvancedExceptionLogger();

        try (AdvancedExceptionLogger logger = AdvancedExceptionLogger.getInstance(LOG_DIRECTORY, logStorage)) {
            // Intenta inducir una excepción.
            try {
                induceException();  // Método que lanzará una excepción.
            } catch (ArrayIndexOutOfBoundsException e) // Capturamos la excepción específica
            {
                String exceptionMessage = "ArrayIndexOutOfBoundsException capturada: " + e.toString();
                logger.logSevere(exceptionMessage, e);  // Registramos la excepción en el logger
            } catch (Exception e) // Capturamos cualquier otra excepción general
            {
                // Si se produce una excepción, la registra usando el logger.
                String exceptionMessage = "ERROR!!*: " + e.toString();
                logger.logSevere(exceptionMessage, e);  // Registramos la excepción en el logger
//                ctrService.addException(exceptionMessage);
//                ctrLogger.logException(exceptionMessage);
            }
            // Mostramos las excepciones almacenadas en consola.            
            logStorage.showStoredException();
        } catch (IOException e) {
            // Manejo de excepciones de IO al inicializar el logger
            System.err.println("rror initializing logger: " + e.getMessage());
        }
    }

    /**
     * Método que induce una excepción de forma deliberada.
     *
     * @throws ArrayIndexOutOfBoundsException
     */
    private static void induceException() throws ArrayIndexOutOfBoundsException {
        int[] numbers = {1, 2, 3};
        // Esto causará una ArrayIndexOutOfBoundsException
        System.out.println(numbers[3]);
    }

}
