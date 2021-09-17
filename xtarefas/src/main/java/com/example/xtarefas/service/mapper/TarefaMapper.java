package com.example.xtarefas.service.mapper;

import com.example.xtarefas.domain.Tarefa;
import com.example.xtarefas.service.dto.TarefaDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TarefaMapper {

    @Mapping(target = "responsavel.id", source = "idResponsavel")
    Tarefa toEntity(TarefaDTO dto);

    @InheritInverseConfiguration
    TarefaDTO toDto(Tarefa entity);

    List<TarefaDTO> toListagemDto(List<Tarefa> responsaveis);
}
