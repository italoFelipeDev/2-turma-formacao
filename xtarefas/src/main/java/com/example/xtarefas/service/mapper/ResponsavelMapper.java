package com.example.xtarefas.service.mapper;

import com.example.xtarefas.domain.Responsavel;
import com.example.xtarefas.service.dto.ResponsavelDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ResponsavelMapper {

    Responsavel toEntity(ResponsavelDTO dto);

    ResponsavelDTO toDto(Responsavel entity);

    List<ResponsavelDTO> toListagemDto(List<Responsavel> responsaveis);
}
