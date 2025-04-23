package com.fabiangomez.apigestioncitas.api_gestion_citas.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fabiangomez.apigestioncitas.api_gestion_citas.dtos.ProfesionalDTO;
import com.fabiangomez.apigestioncitas.api_gestion_citas.services.interfaces.IProfesionalService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/profesionales")
public class ProfesionalController {

    private final IProfesionalService profesionalService;

    public ProfesionalController(IProfesionalService profesionalService) {
        this.profesionalService = profesionalService;
    }

    @GetMapping
    public ResponseEntity<List<ProfesionalDTO>> obtenerTodosLosProfesionales() {
        List<ProfesionalDTO> profesionales = profesionalService.obtenerTodosLosProfesionales();
        return new ResponseEntity<>(profesionales, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfesionalDTO> obtenerProfesionalPorId(@PathVariable("id") Long id) {
        Optional<ProfesionalDTO> profesional = profesionalService.obtenerProfesionalPorId(id);
        return profesional.map(p -> new ResponseEntity<>(p, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<ProfesionalDTO> crearProfesional(@RequestBody @Valid ProfesionalDTO profesionalDTO) {
        ProfesionalDTO profesionalCreado = profesionalService.crearProfesional(profesionalDTO);
        return new ResponseEntity<>(profesionalCreado, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfesionalDTO> actualizarProfesional(@PathVariable("id") Long id,
            @RequestBody @Valid ProfesionalDTO profesionalDTO) {
        ProfesionalDTO profesionalActualizado = profesionalService.actualizarProfesional(id, profesionalDTO);
        return new ResponseEntity<>(profesionalActualizado, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProfesional(@PathVariable("id") Long id) {
        profesionalService.eliminarProfesional(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<ProfesionalDTO>> buscarProfesionales(
            @RequestParam(required = false) String nombres,
            @RequestParam(required = false) String apellidos) {
        List<ProfesionalDTO> resultado = profesionalService.buscarProfesionalesPorNombresOApellidos(nombres, apellidos);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

}
