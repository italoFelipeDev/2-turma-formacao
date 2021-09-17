package com.example.xtarefas.service.elasticsearch;

import com.example.xtarefas.domain.elasticsearch.ResponsavelDocument;
import com.example.xtarefas.event.ResponsavelEvent;
import com.example.xtarefas.repository.ResponsavelRepository;
import com.example.xtarefas.repository.elastic.ResponsavelSearchRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ResponsavelElasticSearchService {

    private final ResponsavelRepository responsavelRepository;

    private final ResponsavelSearchRepository responsavelSearchRepository;

    @TransactionalEventListener(fallbackExecution = true)
    public void reindex(ResponsavelEvent responsavelEvent){
        log.debug("Iniciando indexação de respónsavel a partir de: {}", responsavelEvent.getId());
        ResponsavelDocument responsavelDocument = responsavelRepository.getDocument(responsavelEvent.getId());
        responsavelSearchRepository.save(responsavelDocument);
    }
}
