package com.fabiangomez.apigestioncitas.api_gestion_citas.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.fabiangomez.apigestioncitas.api_gestion_citas.dtos.ProfesionalDTO;
import com.fabiangomez.apigestioncitas.api_gestion_citas.entities.Profesional;

@Mapper(componentModel = "spring", uses = { CitaMapper.class })
public interface ProfesionalMapper {

    @Mapping(target = "citas", source = "citas")
    ProfesionalDTO toDTO(Profesional profesional);

    @Mapping(target = "citas", source = "citas")
    Profesional toEntity(ProfesionalDTO profesionalDTO);

    List<ProfesionalDTO> toDTOList(List<Profesional> profesionales);

    List<Profesional> toEntityList(List<ProfesionalDTO> profesionalesDTO);
}