package com.example.xtarefas.service.mapper;

import com.example.xtarefas.domain.Anexo;
import com.example.xtarefas.domain.Tarefa;
import com.example.xtarefas.service.dto.AnexoDTO;
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
public class AnexoMapperImpl implements AnexoMapper {

    @Override
    public Anexo toEntity(AnexoDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Anexo anexo = new Anexo();

        anexo.setTarefa( anexoDTOToTarefa( dto ) );
        anexo.setId( dto.getId() );
        anexo.setFile( dto.getFile() );
        anexo.setFileName( dto.getFileName() );
        anexo.setUuid( dto.getUuid() );

        return anexo;
    }

    @Override
    public AnexoDTO toDto(Anexo entity) {
        if ( entity == null ) {
            return null;
        }

        AnexoDTO anexoDTO = new AnexoDTO();

        anexoDTO.setIdTarefa( entityTarefaId( entity ) );
        anexoDTO.setId( entity.getId() );
        anexoDTO.setFile( entity.getFile() );
        anexoDTO.setFileName( entity.getFileName() );
        anexoDTO.setUuid( entity.getUuid() );

        return anexoDTO;
    }

    @Override
    public List<AnexoDTO> toListagemDto(List<Anexo> anexos) {
        if ( anexos == null ) {
            return null;
        }

        List<AnexoDTO> list = new ArrayList<AnexoDTO>( anexos.size() );
        for ( Anexo anexo : anexos ) {
            list.add( toDto( anexo ) );
        }

        return list;
    }

    protected Tarefa anexoDTOToTarefa(AnexoDTO anexoDTO) {
        if ( anexoDTO == null ) {
            return null;
        }

        Tarefa tarefa = new Tarefa();

        tarefa.setId( anexoDTO.getIdTarefa() );

        return tarefa;
    }

    private Long entityTarefaId(Anexo anexo) {
        if ( anexo == null ) {
            return null;
        }
        Tarefa tarefa = anexo.getTarefa();
        if ( tarefa == null ) {
            return null;
        }
        Long id = tarefa.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
