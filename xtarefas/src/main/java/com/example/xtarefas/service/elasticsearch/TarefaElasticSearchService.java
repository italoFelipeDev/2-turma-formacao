package com.example.xtarefas.service.elasticsearch;

import com.example.xtarefas.domain.elasticsearch.TarefaDocument;
import com.example.xtarefas.event.TarefaEvent;
import com.example.xtarefas.repository.TarefaRepository;
import com.example.xtarefas.repository.elastic.TarefaSearchRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TarefaElasticSearchService {

    private final TarefaRepository tarefaRepository;
    private final TarefaSearchRepository tarefaSearchRepository;

    @TransactionalEventListener(fallbackExecution = true)
    public void reindex(TarefaEvent tarefaEvent){
        log.debug("Iniciando indexação de tarefa a partir de: {}", tarefaEvent.getId());
        TarefaDocument tarefaDocument = tarefaRepository.getDocument(tarefaEvent.getId());
        tarefaSearchRepository.save(tarefaDocument);
    }
}
