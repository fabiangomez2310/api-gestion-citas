package com.fabiangomez.apigestioncitas.api_gestion_citas.entities;

import java.time.LocalDateTime;

import com.fabiangomez.apigestioncitas.api_gestion_citas.enums.EstadoCita;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import Jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cita")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha_hora", nullable = false)
    @NotNull(message = "La fecha y hora no pueden ser nulas.")
    private LocalDateTime fechaHora;

    @Column(name = "duracion", nullable = false)
    @NotBlank(message = "La duracion no puede estar vacia.")
    private String duracion;

    @Column(name = "motivo", nullable = false, length = 255)
    @NotBlank(message = "El motivo no puede estar vacio.")
    private String motivo;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    @NotNull(message = "El estado de la cita no puede ser nulo.")
    private EstadoCita estado;

    @Column(name = "notas", length = 500)
    private String notas;

    @Column(name = "creada_en", nullable = false)
    private LocalDateTime creadaEn;

    @Column(name = "actualizada_en", nullable = false)
    private LocalDateTime actualizadaEn;
    
    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    @NotNull(message = "El cliente es obligatorio.")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "profesional_id", nullable = false)
    @NotNull(message = "El profesional es obligatorio.")
    private Profesional profesional;

    @PrePersist
    protected void onCreate(){
        this.creadaEn = LocalDateTime.now();
        this.actualizadaEn = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate(){
        this.actualizadaEn = LocalDateTime.now();
    }

}
