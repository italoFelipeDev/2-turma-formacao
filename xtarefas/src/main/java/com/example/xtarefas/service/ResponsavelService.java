package com.example.xtarefas.service;

import com.example.xtarefas.domain.Responsavel;
import com.example.xtarefas.domain.elasticsearch.ResponsavelDocument;
import com.example.xtarefas.event.ResponsavelEvent;
import com.example.xtarefas.repository.ResponsavelRepository;
import com.example.xtarefas.repository.elastic.ResponsavelSearchRepository;
import com.example.xtarefas.service.dto.ResponsavelDTO;
import com.example.xtarefas.service.exception.BadRequestException;
import com.example.xtarefas.service.filter.ResponsavelFilter;
import com.example.xtarefas.service.mapper.ResponsavelMapper;
import com.example.xtarefas.service.util.ConstanteUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ResponsavelService {

    private final ResponsavelRepository responsavelRepository;

    private final ResponsavelMapper responsavelMapper;

    private final ApplicationEventPublisher applicationEventPublisher;

    private final ResponsavelSearchRepository responsavelSearchRepository;

    public ResponsavelDTO salvar(ResponsavelDTO responsavelDTO){
        if(responsavelDTO.getDataNascimento().isAfter(LocalDate.now())){
            throw new BadRequestException("Data de nascimento é futura");
        }
        Responsavel responsavel = responsavelRepository.save(responsavelMapper.toEntity(responsavelDTO));
        applicationEventPublisher.publishEvent(new ResponsavelEvent(responsavel.getId()));
        return responsavelMapper.toDto(responsavel);
    }

    public ResponsavelDTO editar(ResponsavelDTO responsavelDTO) throws Exception {
        responsavelRepository.findById(responsavelDTO.getId()).orElseThrow(() -> new Exception(ConstanteUtil.MENSAGEM_ERRO_RESPONSAVEL));
        return responsavelMapper.toDto(responsavelRepository.save(responsavelMapper.toEntity(responsavelDTO)));
    }

    public List<ResponsavelDTO> obterTodos(){
        return responsavelMapper.toListagemDto(responsavelRepository.findAll());
    }

    public ResponsavelDTO obterPorId(Long id) throws Exception {
        return responsavelMapper.toDto(responsavelRepository.findById(id)
                .orElseThrow(() -> new BadRequestException(ConstanteUtil.MENSAGEM_ERRO_RESPONSAVEL)));
    }

    public void deletarPorId(Long id){
        responsavelRepository.deleteById(id);
    }

    public Page<ResponsavelDocument> pesquisar(ResponsavelFilter responsavelFilter, Pageable pageable){
        return responsavelSearchRepository.search(responsavelFilter.getFilter(),pageable);
    }
}
