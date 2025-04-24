package com.fabiangomez.apigestioncitas.api_gestion_citas.entities;



import java.util.List;

import com.fabiangomez.apigestioncitas.api_gestion_citas.enums.EstadoCliente;
import com.fabiangomez.apigestioncitas.api_gestion_citas.enums.TipoDocumento;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Table(name = "cliente")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_documento", nullable = false)
    @NotNull(message = "El tipo de documento no puede ser nulo.")
    private TipoDocumento tipoDocumento;

    @Column(name = "numero_documento", nullable = false, length = 20)
    @NotBlank(message = "El numero de documento no puede estar vacio")
    @Size(min = 5, max = 20, message = "El numero de documento debe tener entre 5 y 20 caracteres")
    private String numeroDocumento;

    @Column(name = "nombre_completo", nullable = false, length = 100)
    @NotBlank(message = "El nombre completo no puede estar vacio")
    @Size(max = 100, message = "El nombre completo debe tener máximo 100 caracteres")
    private String nombreCompleto; 

    @Column(name = "correo", nullable = false, length = 50)
    @NotBlank(message = "El correo no puede estar vacio")
    @Email(message = "El correo debe ser valido")
    @Size(max = 50, message = "El correo debe tener máximo 50 caracteres")
    private String correo;

    @Column(name = "telefono", nullable = false, length = 20)
    @NotBlank(message = "El telefono no puede estar vacio")
    @Size(max = 20, message = "El teléfono debe tener máximo 20 caracteres")
    private String telefono;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    @NotNull(message = "El estado del cliente no puede ser nulo")
    private EstadoCliente estado;

    @OneToMany(mappedBy = "cliente")
    private List<Cita> citas;

}
