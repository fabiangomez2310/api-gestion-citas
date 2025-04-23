package com.fabiangomez.apigestioncitas.api_gestion_citas.exceptions;

public class ProfesionalNoEncontradoException extends RuntimeException {
    public ProfesionalNoEncontradoException(String mensaje){
        super(mensaje);
    }
}
