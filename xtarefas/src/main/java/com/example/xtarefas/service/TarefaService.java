package com.example.xtarefas.service;

import com.example.xtarefas.domain.Tarefa;
import com.example.xtarefas.domain.elasticsearch.TarefaDocument;
import com.example.xtarefas.event.TarefaEvent;
import com.example.xtarefas.repository.TarefaRepository;
import com.example.xtarefas.repository.elastic.TarefaSearchRepository;
import com.example.xtarefas.service.dto.TarefaDTO;
import com.example.xtarefas.service.enumeration.StatusEnum;
import com.example.xtarefas.service.exception.BadRequestException;
import com.example.xtarefas.service.filter.TarefaFilter;
import com.example.xtarefas.service.mapper.TarefaMapper;
import com.example.xtarefas.service.util.ConstanteUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class TarefaService {

    private final TarefaRepository tarefaRepository;

    private final TarefaMapper tarefaMapper;

    private final ApplicationEventPublisher applicationEventPublisher;

    private final TarefaSearchRepository tarefaSearchRepository;

    public TarefaDTO salvar(TarefaDTO tarefaDTO){
        Tarefa tarefa = tarefaMapper.toEntity(tarefaDTO);
        tarefa.setStatus(StatusEnum.INCOMPLETA);
        tarefa = tarefaRepository.save(tarefa);
        applicationEventPublisher.publishEvent(new TarefaEvent(tarefa.getId()));
        return tarefaMapper.toDto(tarefa);
    }

    public TarefaDTO editar(TarefaDTO tarefaDTO) throws Exception {
        tarefaRepository.findById(tarefaDTO.getId()).orElseThrow(() -> new BadRequestException(ConstanteUtil.MENSAGEM_ERRO_TAREFA));
        return tarefaMapper.toDto(tarefaRepository.save(tarefaMapper.toEntity(tarefaDTO)));
    }

    public List<TarefaDTO> obterTodos(){
        return tarefaMapper.toListagemDto(tarefaRepository.findAll());
    }

    public TarefaDTO obterPorId(Long id) throws Exception {
        return tarefaMapper.toDto(tarefaRepository.findById(id)
                .orElseThrow(() -> new BadRequestException(ConstanteUtil.MENSAGEM_ERRO_TAREFA)));
    }

    public void deletarPorId(Long id){
        tarefaRepository.deleteById(id);
    }

    public TarefaDTO alterarStatus(Long id) throws Exception {
        Tarefa tarefa =tarefaRepository.findById(id)
                .orElseThrow(() -> new BadRequestException(ConstanteUtil.MENSAGEM_ERRO_TAREFA));
        tarefa.setStatus(tarefa.getStatus() == StatusEnum.INCOMPLETA ? StatusEnum.CONCLUIDA : StatusEnum.INCOMPLETA);
        return tarefaMapper.toDto(tarefa);

    }

    public Page<TarefaDocument> pesquisar(TarefaFilter tarefaFilter, Pageable pageable){
        return tarefaSearchRepository.search(tarefaFilter.getFilter(),pageable);
    }
}
