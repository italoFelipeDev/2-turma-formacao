package com.example.xtarefas.repository.elastic;


import com.example.xtarefas.domain.elasticsearch.AnexoDocument;

public interface AnexoSearchRepository extends ElasticEntity<AnexoDocument,Long> {

    @Override
    default Class<AnexoDocument> getEntityClass(){
        return AnexoDocument.class;
    }
}
