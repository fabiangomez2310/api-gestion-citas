package com.fabiangomez.apigestioncitas.api_gestion_citas.dtos;

import com.fabiangomez.apigestioncitas.api_gestion_citas.enums.EstadoCliente;
import com.fabiangomez.apigestioncitas.api_gestion_citas.enums.TipoDocumento;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ClienteDTO {

    private Long id;

    @NotNull(message = "El tipo de documento no puede ser nulo")
    private TipoDocumento tipoDocumento;

    @NotBlank(message = "El numero de documento no puede estar vacio")
    @Size(min = 5, max = 20, message = "Debe tener entre 5 y 20 caracteres")
    private String numeroDocumento;

    @NotBlank(message = "El nombre completo no puede estar vacio")
    private String nombreCompleto;

    @NotBlank(message = "El correo no puede estar vacio")
    @Email(message = "Debe ser un correo valido")
    private String correo;

    @NotBlank(message = "El telefono no puede estar vacio")
    private String telefono;

    @NotNull(message = "El estado no puede ser nulo")
    private EstadoCliente estado;
}
