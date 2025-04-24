package com.fabiangomez.apigestioncitas.api_gestion_citas.dtos;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CitaDTO {

    @NotNull(message = "El ID de la cita no puede ser nulo.")
    private Long id;

    @NotNull(message = "La fecha y hora no puede ser nula.")
    private LocalDateTime fechaHora;

    @NotBlank(message = "La duración no puede estar vacía.")
    @Size(max = 50, message = "La duración no puede exceder los 50 caracteres.")
    private String duracion;

    @NotBlank(message = "El motivo no puede estar vacío.")
    @Size(max = 250, message = "El motivo no puede exceder los 250 caracteres.")
    private String motivo;

    @NotNull(message = "El ID del cliente no puede ser nulo.")
    private Long clienteId;

    @NotNull(message = "El ID del profesional no puede ser nulo.")
    private Long profesionalId;

    @NotBlank(message = "El estado no puede estar vacío.")
    @Size(max = 20, message = "El estado no puede exceder los 20 caracteres.")
    private String estado;

    @Size(max = 500, message = "Las notas no pueden exceder los 500 caracteres.")
    private String notas;

    private LocalDateTime creadaEn;

    private LocalDateTime actualizadaEn;

}
