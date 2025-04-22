package com.fabiangomez.apigestioncitas.api_gestion_citas.services.interfaces;

import java.util.List;
import java.util.Optional;

import com.fabiangomez.apigestioncitas.api_gestion_citas.dtos.ClienteDTO;

public interface IClienteService {

    Optional<ClienteDTO> obtenerClientePorId(Long id);
    List<ClienteDTO> obtenerTodosLosClientes();
    ClienteDTO crearCliente(ClienteDTO clienteDTO);
    ClienteDTO actualizarCliente(Long id, ClienteDTO clienteDTO);
    void eliminarCliente(Long id);
    ClienteDTO actualizarEstado(Long id, String nuevoEstado);
    
}
