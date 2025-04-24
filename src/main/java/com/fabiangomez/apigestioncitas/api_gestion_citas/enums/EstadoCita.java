package com.fabiangomez.apigestioncitas.api_gestion_citas.enums;

/**
 * Estados posibles para una cita en el sistema.
 */
public enum EstadoCita {
    PENDIENTE,   // Estado inicial al crear la cita
    CONFIRMADA,  // Confirmada por el profesional
    CANCELADA,   // Cancelada por el cliente o el profesional
    COMPLETADA   // Finalizada por el profesional
}
