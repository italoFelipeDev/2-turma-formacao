package com.example.xtarefas.service.mapper;

import com.example.xtarefas.domain.Responsavel;
import com.example.xtarefas.service.dto.ResponsavelDTO;
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
public class ResponsavelMapperImpl implements ResponsavelMapper {

    @Override
    public Responsavel toEntity(ResponsavelDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Responsavel responsavel = new Responsavel();

        responsavel.setId( dto.getId() );
        responsavel.setNome( dto.getNome() );
        responsavel.setEmail( dto.getEmail() );
        responsavel.setDataNascimento( dto.getDataNascimento() );

        return responsavel;
    }

    @Override
    public ResponsavelDTO toDto(Responsavel entity) {
        if ( entity == null ) {
            return null;
        }

        ResponsavelDTO responsavelDTO = new ResponsavelDTO();

        responsavelDTO.setId( entity.getId() );
        responsavelDTO.setNome( entity.getNome() );
        responsavelDTO.setEmail( entity.getEmail() );
        responsavelDTO.setDataNascimento( entity.getDataNascimento() );

        return responsavelDTO;
    }

    @Override
    public List<ResponsavelDTO> toListagemDto(List<Responsavel> responsaveis) {
        if ( responsaveis == null ) {
            return null;
        }

        List<ResponsavelDTO> list = new ArrayList<ResponsavelDTO>( responsaveis.size() );
        for ( Responsavel responsavel : responsaveis ) {
            list.add( toDto( responsavel ) );
        }

        return list;
    }
}
