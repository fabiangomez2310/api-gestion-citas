package com.fabiangomez.apigestioncitas.api_gestion_citas.controllers;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fabiangomez.apigestioncitas.api_gestion_citas.dtos.ClienteDTO;
import com.fabiangomez.apigestioncitas.api_gestion_citas.services.interfaces.IClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private IClienteService clienteService;

    // Obtener todos los clientes
    @GetMapping
    public ResponseEntity<List<ClienteDTO>> obtenerTodosLosClientes() {
        List<ClienteDTO> clientes = clienteService.obtenerTodosLosClientes();
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    // Obtener cliente por ID
    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> obtenerClientePorId(@PathVariable("id") Long id) {
        Optional<ClienteDTO> cliente = clienteService.obtenerClientePorId(id);
        return cliente.map(c -> new ResponseEntity<>(c, HttpStatus.OK))
                      .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Crear un nuevo cliente
    @PostMapping
    public ResponseEntity<ClienteDTO> crearCliente(@RequestBody ClienteDTO clienteDTO) {
        ClienteDTO clienteCreado = clienteService.crearCliente(clienteDTO);
        return new ResponseEntity<>(clienteCreado, HttpStatus.CREATED);
    }

    // Actualizar cliente
    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> actualizarCliente(@PathVariable("id") Long id, 
                                                        @RequestBody ClienteDTO clienteDTO) {
        try {
            ClienteDTO clienteActualizado = clienteService.actualizarCliente(id, clienteDTO);
            return new ResponseEntity<>(clienteActualizado, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Eliminar cliente
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable("id") Long id) {
        try {
            clienteService.eliminarCliente(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Actualizar estado de un cliente
    @PatchMapping("/{id}/estado")
    public ResponseEntity<ClienteDTO> actualizarEstado(@PathVariable("id") Long id,
                                                      @RequestParam("nuevoEstado") String nuevoEstado) {
        try {
            ClienteDTO clienteActualizado = clienteService.actualizarEstado(id, nuevoEstado);
            return new ResponseEntity<>(clienteActualizado, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
