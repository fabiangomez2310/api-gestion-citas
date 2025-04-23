package com.fabiangomez.apigestioncitas.api_gestion_citas.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.fabiangomez.apigestioncitas.api_gestion_citas.dtos.ProfesionalDTO;
import com.fabiangomez.apigestioncitas.api_gestion_citas.entities.Profesional;
import com.fabiangomez.apigestioncitas.api_gestion_citas.exceptions.ProfesionalNoEncontradoException;
import com.fabiangomez.apigestioncitas.api_gestion_citas.exceptions.RecursoDuplicadoException;
import com.fabiangomez.apigestioncitas.api_gestion_citas.mappers.ProfesionalMapper;
import com.fabiangomez.apigestioncitas.api_gestion_citas.repositories.ProfesionalRepository;
import com.fabiangomez.apigestioncitas.api_gestion_citas.services.interfaces.IProfesionalService;

@Service
public class ProfesionalServiceImpl implements IProfesionalService {

    private final ProfesionalRepository profesionalRepository;

    private final ProfesionalMapper profesionalMapper;

    public ProfesionalServiceImpl(ProfesionalRepository profesionalRepository, ProfesionalMapper profesionalMapper) {
        this.profesionalRepository = profesionalRepository;
        this.profesionalMapper = profesionalMapper;
    }

    @Override
    public ProfesionalDTO crearProfesional(ProfesionalDTO profesionalDTO) {

        if (profesionalRepository.existsByNumeroDocumento(profesionalDTO.getNumeroDocumento())) {
            throw new RecursoDuplicadoException(
                    "Ya existe un profesional con el numero de documento: " + profesionalDTO.getNumeroDocumento());
        }
        if (profesionalRepository.existsByCorreo(profesionalDTO.getCorreo())) {
            throw new RecursoDuplicadoException(
                    "Ya existe un profesional con el correo electronico: " + profesionalDTO.getCorreo());
        }

        if (profesionalRepository.existsByTelefono(profesionalDTO.getTelefono())) {
            throw new RecursoDuplicadoException(
                    "Ya existe un profesional con el teléfono: " + profesionalDTO.getTelefono());
        }

        Profesional profesional = profesionalMapper.toEntity(profesionalDTO);

        Profesional guardado = profesionalRepository.save(profesional);

        return profesionalMapper.toDTO(guardado);

    }

    @Override
    public List<ProfesionalDTO> obtenerTodosLosProfesionales() {
        List<Profesional> profesional = profesionalRepository.findAll();
        return profesionalMapper.toDTOList(profesional);
    }

    @Override
    public Optional<ProfesionalDTO> obtenerProfesionalPorId(Long id) {
        Optional<Profesional> profesionalOptional = profesionalRepository.findById(id);
        if (profesionalOptional.isEmpty()) {
            throw new ProfesionalNoEncontradoException("Profesional con id " + id + " no encontrado.");
        }
        return Optional.of(profesionalMapper.toDTO(profesionalOptional.get()));
    }

    @Override
    public ProfesionalDTO actualizarProfesional(Long id, ProfesionalDTO profesionalDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actualizarProfesional'");
    }

    @Override
    public void eliminarProfesional(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminarProfesional'");
    }

    @Override
    public List<ProfesionalDTO> buscarProfesionalesPorNombresOApellidos(String nombres, String apellidos) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarProfesionalesPorNombresOApellidos'");
    }
}
