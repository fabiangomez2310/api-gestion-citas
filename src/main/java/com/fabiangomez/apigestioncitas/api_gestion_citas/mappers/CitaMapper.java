package com.fabiangomez.apigestioncitas.api_gestion_citas.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.fabiangomez.apigestioncitas.api_gestion_citas.dtos.CitaDTO;
import com.fabiangomez.apigestioncitas.api_gestion_citas.entities.Cita;

@Mapper(componentModel = "spring")
public interface CitaMapper {

    @Mapping(target = "clienteId", source = "cliente.id")
    @Mapping(target = "profesionalId", source = "profesional.id")
    CitaDTO toDTO(Cita cita);

    @Mapping(target = "cliente.id", source = "clienteId")
    @Mapping(target = "profesional.id", source = "profesionalId")
    Cita toEntity(CitaDTO citaDTO);

    List<CitaDTO> toDTOList(List<Cita> citas);

    List<Cita> toEntityList(List<CitaDTO> citasDTO);
}
