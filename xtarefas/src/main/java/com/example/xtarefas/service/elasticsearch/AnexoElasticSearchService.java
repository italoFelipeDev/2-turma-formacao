package com.example.xtarefas.service.elasticsearch;

import com.example.xtarefas.domain.elasticsearch.AnexoDocument;
import com.example.xtarefas.event.AnexoEvent;
import com.example.xtarefas.repository.AnexoRepository;
import com.example.xtarefas.repository.elastic.AnexoSearchRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AnexoElasticSearchService {

    private final AnexoRepository anexoRepository;

    private final AnexoSearchRepository anexoSearchRepository;

    @TransactionalEventListener(fallbackExecution = true)
    public void reindex(AnexoEvent anexoEvent){
        log.debug("Iniciando indexação do anexo a partir de: {}", anexoEvent.getId());
        AnexoDocument anexoDocument = anexoRepository.getDocument(anexoEvent.getId());
        anexoSearchRepository.save(anexoDocument);
    }
}
