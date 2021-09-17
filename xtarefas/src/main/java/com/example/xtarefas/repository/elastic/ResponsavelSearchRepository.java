package com.example.xtarefas.repository.elastic;

import com.example.xtarefas.domain.elasticsearch.ResponsavelDocument;

public interface ResponsavelSearchRepository extends ElasticEntity<ResponsavelDocument,Long> {

    @Override
    default Class<ResponsavelDocument> getEntityClass(){
        return ResponsavelDocument.class;
    }
}
