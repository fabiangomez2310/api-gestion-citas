package com.fabiangomez.apigestioncitas.api_gestion_citas.exceptions;

public class ClienteNoEncontradoException extends RuntimeException {
    public ClienteNoEncontradoException(String mensaje){
        super(mensaje);
    }
}
