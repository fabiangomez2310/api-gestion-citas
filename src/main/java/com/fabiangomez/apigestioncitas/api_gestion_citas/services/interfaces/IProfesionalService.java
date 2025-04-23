package com.fabiangomez.apigestioncitas.api_gestion_citas.services.interfaces;

import java.util.List;
import java.util.Optional;

import com.fabiangomez.apigestioncitas.api_gestion_citas.dtos.ProfesionalDTO;

public interface IProfesionalService {

    ProfesionalDTO crearProfesional(ProfesionalDTO profesionalDTO);

    List<ProfesionalDTO> obtenerTodosLosProfesionales();

    Optional<ProfesionalDTO> obtenerProfesionalPorId(Long id);

    ProfesionalDTO actualizarProfesional(Long id, ProfesionalDTO profesionalDTO);

    void eliminarProfesional(Long id);

    List<ProfesionalDTO> buscarProfesionalesPorNombresOApellidos(String nombres, String apellidos);

}
