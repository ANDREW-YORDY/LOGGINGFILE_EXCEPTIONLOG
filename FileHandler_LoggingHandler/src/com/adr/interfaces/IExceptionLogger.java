package com.adr.interfaces;


public interface IExceptionLogger 
{

    // Método para añadir una excepción.
    void addException(String excepMessage);

    // Método para mostrar las excepciones almacenadas.
    void showStoredException();

}

