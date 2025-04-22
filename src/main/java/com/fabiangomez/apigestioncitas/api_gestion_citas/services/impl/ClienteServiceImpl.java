package com.fabiangomez.apigestioncitas.api_gestion_citas.services.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.fabiangomez.apigestioncitas.api_gestion_citas.dtos.ClienteDTO;
import com.fabiangomez.apigestioncitas.api_gestion_citas.entities.Cliente;
import com.fabiangomez.apigestioncitas.api_gestion_citas.enums.EstadoCliente;
import com.fabiangomez.apigestioncitas.api_gestion_citas.exceptions.ClienteNoEncontradoException;
import com.fabiangomez.apigestioncitas.api_gestion_citas.exceptions.ClienteYaExisteException;
import com.fabiangomez.apigestioncitas.api_gestion_citas.mappers.ClienteMapper;
import com.fabiangomez.apigestioncitas.api_gestion_citas.repositories.ClienteRepository;
import com.fabiangomez.apigestioncitas.api_gestion_citas.services.interfaces.IClienteService;

@Service
public class ClienteServiceImpl implements IClienteService{
   
    private final ClienteRepository clienteRepository;
    
    private final ClienteMapper clienteMapper;

    public ClienteServiceImpl(ClienteRepository clienteRepository, ClienteMapper clienteMapper){
        this.clienteRepository=clienteRepository;
        this.clienteMapper=clienteMapper;
    }

    @Override
    public Optional<ClienteDTO> obtenerClientePorId(Long id) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);

        if(clienteOptional.isEmpty()){
            throw new ClienteNoEncontradoException("Cliente con id " + id + " no encontrado.");
        }

        return Optional.of(clienteMapper.toDTO(clienteOptional.get()));
    }

    @Override
    public List<ClienteDTO> obtenerTodosLosClientes() {
        List<Cliente> clientes = clienteRepository.findAll();
        return clienteMapper.toDTOList(clientes);
    }

    @Override
    public ClienteDTO crearCliente(ClienteDTO clienteDTO) {

        boolean existe = clienteRepository.existsByNumeroDocumento(clienteDTO.getNumeroDocumento());

        if(existe){
            throw new ClienteYaExisteException("Ya existe un cliente con el numero de documento: "+clienteDTO.getNumeroDocumento());
        }

        Cliente cliente = clienteMapper.toEntity(clienteDTO);
        Cliente clienteGuardado = clienteRepository.save(cliente);
        
        return clienteMapper.toDTO(clienteGuardado);
    }

    @Override
    public ClienteDTO actualizarCliente(Long id, ClienteDTO clienteDTO) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);

        if(clienteOptional.isEmpty()){
            throw new ClienteNoEncontradoException("Cliente con id "+id+" no encontrado.");
        }

        Cliente cliente = clienteOptional.get();
        
        cliente.setTipoDocumento(clienteDTO.getTipoDocumento());
        cliente.setNumeroDocumento(clienteDTO.getNumeroDocumento());
        cliente.setNombreCompleto(clienteDTO.getNombreCompleto());
        cliente.setCorreo(clienteDTO.getCorreo());
        cliente.setTelefono(clienteDTO.getTelefono());
        cliente.setEstado(clienteDTO.getEstado());

        Cliente clienteActualizado = clienteRepository.save(cliente);

        return clienteMapper.toDTO(clienteActualizado);

    }

    @Override
    public void eliminarCliente(Long id) {
        
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);

        if(clienteOptional.isEmpty()){
            throw new ClienteNoEncontradoException("Cliente con id "+id+" no encontrado.");
        }

        clienteRepository.deleteById(id);
    }

    @Override
    public ClienteDTO actualizarEstado(Long id, String nuevoEstado) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);

        if(clienteOptional.isEmpty()){
            throw new ClienteNoEncontradoException("Cliente con id "+id+" no encontrado.");
        }

        Cliente cliente = clienteOptional.get();

        try {
            cliente.setEstado(EstadoCliente.valueOf(nuevoEstado.toUpperCase()));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Estado no valido: "+nuevoEstado + ". Los valores válidos son: " + 
            Arrays.toString(EstadoCliente.values()));
        }


        Cliente clienteActualizado = clienteRepository.save(cliente);

        return clienteMapper.toDTO(clienteActualizado);
    }

}
