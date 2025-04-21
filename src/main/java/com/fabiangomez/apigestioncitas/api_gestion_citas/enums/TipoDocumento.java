package com.fabiangomez.apigestioncitas.api_gestion_citas.enums;


public enum TipoDocumento {
    CC("Cédula de Ciudadanía"),
    CE("Cédula de Extranjería"),
    PEP("Permiso Especial de Permanencia"),
    PPT("Permiso Temporal de Permanencia"),
    TI("Tarjeta de Identidad"),
    RC("Registro Civil");

    private final String descripcion;

    // Constructor para asociar la descripción con el valor
    TipoDocumento(String descripcion) {
        this.descripcion = descripcion;
    }

    // Método para obtener la descripción
    public String getDescripcion() {
        return descripcion;
    }
}
