package com.fabiangomez.apigestioncitas.api_gestion_citas.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.fabiangomez.apigestioncitas.api_gestion_citas.dtos.ClienteDTO;
import com.fabiangomez.apigestioncitas.api_gestion_citas.entities.Cliente;

@Mapper(componentModel = "spring", uses = { CitaMapper.class })
public interface ClienteMapper {

    @Mapping(target = "citas", source = "citas")
    ClienteDTO toDTO(Cliente cliente);

    @Mapping(target = "citas", source = "citas")
    Cliente toEntity(ClienteDTO clienteDTO);

    List<ClienteDTO> toDTOList(List<Cliente> clientes);

    List<Cliente> toEntityList(List<ClienteDTO> clienteDTOs);
}
