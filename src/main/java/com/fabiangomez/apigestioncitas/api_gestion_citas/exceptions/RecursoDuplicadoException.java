package com.fabiangomez.apigestioncitas.api_gestion_citas.exceptions;

public class RecursoDuplicadoException extends RuntimeException{
    public RecursoDuplicadoException(String mensaje){
        super(mensaje);
    }
}
