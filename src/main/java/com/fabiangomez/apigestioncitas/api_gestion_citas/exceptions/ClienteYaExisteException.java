package com.fabiangomez.apigestioncitas.api_gestion_citas.exceptions;

public class ClienteYaExisteException extends RuntimeException{

    public ClienteYaExisteException(String mensaje){
        super(mensaje);
    }

}
