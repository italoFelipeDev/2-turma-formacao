package com.example.xtarefas.service.mapper;

import com.example.xtarefas.domain.Responsavel;
import com.example.xtarefas.domain.Tarefa;
import com.example.xtarefas.service.dto.TarefaDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-07-30T09:04:11-0300",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 1.8.0_292 (Private Build)"
)
@Component
public class TarefaMapperImpl implements TarefaMapper {

    @Override
    public Tarefa toEntity(TarefaDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Tarefa tarefa = new Tarefa();

        tarefa.setResponsavel( tarefaDTOToResponsavel( dto ) );
        tarefa.setId( dto.getId() );
        tarefa.setNome( dto.getNome() );
        tarefa.setDataConclusao( dto.getDataConclusao() );
        tarefa.setDataInicio( dto.getDataInicio() );
        tarefa.setStatus( dto.getStatus() );

        return tarefa;
    }

    @Override
    public TarefaDTO toDto(Tarefa entity) {
        if ( entity == null ) {
            return null;
        }

        TarefaDTO tarefaDTO = new TarefaDTO();

        tarefaDTO.setIdResponsavel( entityResponsavelId( entity ) );
        tarefaDTO.setId( entity.getId() );
        tarefaDTO.setNome( entity.getNome() );
        tarefaDTO.setDataConclusao( entity.getDataConclusao() );
        tarefaDTO.setDataInicio( entity.getDataInicio() );
        tarefaDTO.setStatus( entity.getStatus() );

        return tarefaDTO;
    }

    @Override
    public List<TarefaDTO> toListagemDto(List<Tarefa> responsaveis) {
        if ( responsaveis == null ) {
            return null;
        }

        List<TarefaDTO> list = new ArrayList<TarefaDTO>( responsaveis.size() );
        for ( Tarefa tarefa : responsaveis ) {
            list.add( toDto( tarefa ) );
        }

        return list;
    }

    protected Responsavel tarefaDTOToResponsavel(TarefaDTO tarefaDTO) {
        if ( tarefaDTO == null ) {
            return null;
        }

        Responsavel responsavel = new Responsavel();

        responsavel.setId( tarefaDTO.getIdResponsavel() );

        return responsavel;
    }

    private Long entityResponsavelId(Tarefa tarefa) {
        if ( tarefa == null ) {
            return null;
        }
        Responsavel responsavel = tarefa.getResponsavel();
        if ( responsavel == null ) {
            return null;
        }
        Long id = responsavel.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
