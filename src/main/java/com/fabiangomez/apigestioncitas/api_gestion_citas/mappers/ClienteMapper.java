package com.fabiangomez.apigestioncitas.api_gestion_citas.mappers;

import org.mapstruct.Mapper;

import com.fabiangomez.apigestioncitas.api_gestion_citas.dtos.ClienteDTO;
import com.fabiangomez.apigestioncitas.api_gestion_citas.entities.Cliente;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    ClienteDTO toDTO(Cliente cliente);

    Cliente toEntity(ClienteDTO clienteDTO);

}
