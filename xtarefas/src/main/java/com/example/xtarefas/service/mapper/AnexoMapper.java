package com.example.xtarefas.service.mapper;

import com.example.xtarefas.domain.Anexo;
import com.example.xtarefas.service.dto.AnexoDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AnexoMapper {

    @Mapping(target = "tarefa.id", source = "idTarefa")
    Anexo toEntity(AnexoDTO dto);

    @InheritInverseConfiguration
    AnexoDTO toDto(Anexo entity);

    List<AnexoDTO> toListagemDto(List<Anexo> anexos);
}
