package com.fabiangomez.apigestioncitas.api_gestion_citas.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fabiangomez.apigestioncitas.api_gestion_citas.dtos.ProfesionalDTO;
import com.fabiangomez.apigestioncitas.api_gestion_citas.entities.Profesional;
import com.fabiangomez.apigestioncitas.api_gestion_citas.exceptions.ProfesionalNoEncontradoException;
import com.fabiangomez.apigestioncitas.api_gestion_citas.exceptions.RecursoDuplicadoException;
import com.fabiangomez.apigestioncitas.api_gestion_citas.exceptions.RecursoNoEncontradoException;
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

        validarDatosUnicos(profesionalDTO);

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
        // Consultar si el profesional existe, pues no es posible actualizar si no
        // existe.
        Profesional profesionalExistente = profesionalRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Profesional con el ID: " + id + " no encontrado"));

        // si encuentra los compara los numeros de documento para ver si son diferentes
        if (!profesionalDTO.getNumeroDocumento().equals(profesionalExistente.getNumeroDocumento())) {
            // si son diferentes entonces consulta que el numero de documento del DTO
            // (nuevo) no exista en la DB
            if (profesionalRepository.existsByNumeroDocumento(profesionalDTO.getNumeroDocumento())) {
                throw new RecursoDuplicadoException(
                        "Ya existe un profesional con el numero de documento: " + profesionalDTO.getNumeroDocumento());
            }
        }

        if (!profesionalDTO.getCorreo().equals(profesionalExistente.getCorreo())) {
            if (profesionalRepository.existsByCorreo(profesionalDTO.getCorreo())) {
                throw new RecursoDuplicadoException(
                        "Ya existe un profesional con el correo: " + profesionalDTO.getCorreo());
            }
        }

        if (!profesionalDTO.getTelefono().equals(profesionalExistente.getTelefono())) {
            if (profesionalRepository.existsByTelefono(profesionalDTO.getTelefono())) {
                throw new RecursoDuplicadoException(
                        "Ya existe un profesional con el telefono: " + profesionalDTO.getTelefono());
            }
        }

        profesionalExistente.setTipoDocumento(profesionalDTO.getTipoDocumento());
        profesionalExistente.setNumeroDocumento(profesionalDTO.getNumeroDocumento());
        profesionalExistente.setNombres(profesionalDTO.getNombres());
        profesionalExistente.setApellidos(profesionalDTO.getApellidos());
        profesionalExistente.setCorreo(profesionalDTO.getCorreo());
        profesionalExistente.setTelefono(profesionalDTO.getTelefono());
        profesionalExistente.setEstado(profesionalDTO.getEstado());

        Profesional actualizado = profesionalRepository.save(profesionalExistente);

        return profesionalMapper.toDTO(actualizado);
    }

    @Override
    public void eliminarProfesional(Long id) {
        Profesional existe = profesionalRepository.findById(id).orElseThrow(
                () -> new RecursoNoEncontradoException("Profesional con el ID: " + id + " no encontrado."));
        profesionalRepository.delete(existe);
    }

    @Override
    public List<ProfesionalDTO> buscarProfesionalesPorNombresOApellidos(String nombres, String apellidos) {
        List<Profesional> profesionales = profesionalRepository
                .findByNombresContainingIgnoreCaseOrApellidosContainingIgnoreCase(nombres, apellidos);

        return profesionales.stream()
                .map(profesionalMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Metodos privados
    private void validarDatosUnicos(ProfesionalDTO profesionalDTO) {
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
    }

}
