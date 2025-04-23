package com.fabiangomez.apigestioncitas.api_gestion_citas.exceptions;

public class RecursoNoEncontradoException extends RuntimeException {
    public RecursoNoEncontradoException(String mensaje){
        super(mensaje);
    }
}
