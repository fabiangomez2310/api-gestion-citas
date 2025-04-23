package com.fabiangomez.apigestioncitas.api_gestion_citas.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import com.fabiangomez.apigestioncitas.api_gestion_citas.dtos.ProfesionalDTO;
import com.fabiangomez.apigestioncitas.api_gestion_citas.entities.Profesional;

@Mapper(componentModel = "spring")
public interface ProfesionalMapper {

    ProfesionalDTO toDTO(Profesional profesional);

    Profesional toEntity(ProfesionalDTO profesionalDTO);

    List<ProfesionalDTO> toDTOList(List<Profesional> profesionales);
}
