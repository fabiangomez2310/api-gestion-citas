package com.fabiangomez.apigestioncitas.api_gestion_citas.dtos;

import java.util.List;

import com.fabiangomez.apigestioncitas.api_gestion_citas.enums.EstadoProfesional;
import com.fabiangomez.apigestioncitas.api_gestion_citas.enums.TipoDocumento;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfesionalDTO {

    private Long id;

    @NotNull(message = "El tipo de documento no puede ser nulo.")
    private TipoDocumento tipoDocumento;

    @NotBlank(message = "El número de documento no puede estar vacío.")
    @Size(min = 5, max = 20, message = "El número de documento debe tener entre 5 y 20 caracteres.")
    private String numeroDocumento;

    @NotBlank(message = "El nombre no puede estar vacío.")
    @Size(max = 50, message = "El nombre debe tener máximo 50 caracteres.")
    private String nombres;

    @NotBlank(message = "El apellido no puede estar vacío.")
    @Size(max = 50, message = "El apellido debe tener máximo 50 caracteres.")
    private String apellidos;

    @NotBlank(message = "El correo no puede estar vacío.")
    @Email(message = "El correo debe ser válido.")
    @Size(max = 50, message = "El correo debe tener máximo 50 caracteres.")
    private String correo;

    @NotBlank(message = "El teléfono no puede estar vacío.")
    @Size(min = 10, max = 20, message = "El teléfono debe tener entre 10 y 20 caracteres.")
    private String telefono;

    @NotNull(message = "El estado del profesional no puede ser nulo.")
    private EstadoProfesional estado;   

    private List<CitaDTO> citas;
}
