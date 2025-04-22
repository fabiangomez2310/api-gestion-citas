package com.fabiangomez.apigestioncitas.api_gestion_citas.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import com.fabiangomez.apigestioncitas.api_gestion_citas.dtos.ClienteDTO;
import com.fabiangomez.apigestioncitas.api_gestion_citas.entities.Cliente;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    ClienteDTO toDTO(Cliente cliente);

    Cliente toEntity(ClienteDTO clienteDTO);

    List<ClienteDTO> toDTOList(List<Cliente> clientes);
    
}
