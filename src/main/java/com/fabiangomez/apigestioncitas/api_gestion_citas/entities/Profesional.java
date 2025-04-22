package com.fabiangomez.apigestioncitas.api_gestion_citas.entities;

import com.fabiangomez.apigestioncitas.api_gestion_citas.enums.EstadoProfesional;
import com.fabiangomez.apigestioncitas.api_gestion_citas.enums.TipoDocumento;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "profesional")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Profesional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_documento", nullable = false)
    @NotNull(message = "El tipo de documento no puede ser nulo.")
    private TipoDocumento tipoDocumento;

    @Column(name = "numero_documento", nullable = false, length = 20)
    @NotBlank(message = "El número de documento no puede estar vacío.")
    @Size(min = 5, max = 20, message = "El número de documento debe tener entre 5 y 20 caracteres.")
    private String numeroDocumento;

    @Column(name = "nombres", nullable = false, length = 50)
    @NotBlank(message = "El nombre no puede estar vacío.")
    @Size(max = 50, message = "El nombre debe tener máximo 50 caracteres.")
    private String nombres;

    @Column(name = "apellidos", nullable = false, length = 50)
    @NotBlank(message = "El apellido no puede estar vacío.")
    @Size(max = 50, message = "El apellido debe tener máximo 50 caracteres.")
    private String apellidos;

    @Column(name = "correo", nullable = false, length = 50)
    @NotBlank(message = "El correo no puede estar vacío.")
    @Email(message = "El correo debe ser válido.")
    @Size(max = 50, message = "El correo debe tener máximo 50 caracteres.")
    private String correo;

    @Column(name = "telefono", nullable = false, length = 20)
    @NotBlank(message = "El teléfono no puede estar vacío.")
    @Size(max = 20, message = "El teléfono debe tener máximo 20 caracteres.")
    private String telefono;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    @NotNull(message = "El estado del profesional no puede ser nulo.")
    private EstadoProfesional estado;

}
