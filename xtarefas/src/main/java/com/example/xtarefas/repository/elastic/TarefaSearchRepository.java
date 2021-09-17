package com.example.xtarefas.repository.elastic;


import com.example.xtarefas.domain.elasticsearch.TarefaDocument;

public interface TarefaSearchRepository extends ElasticEntity<TarefaDocument,Long> {

    @Override
    default Class<TarefaDocument> getEntityClass(){
        return TarefaDocument.class;
    }
}
