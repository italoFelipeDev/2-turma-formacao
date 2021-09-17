package com.example.xtarefas.service.filter;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ResponsavelFilter extends DefaultFilter implements BaseFilter, Serializable {

    private static final long serialVersionUID = 6853770743678706600L;

    @Override
    public BoolQueryBuilder getFilter() {

        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();

        List<String> fields = new ArrayList<>();
        filterFields(fields,querry, queryBuilder, "nome","email","status");

        addShouldTermQuery(queryBuilder,"dataNascimento", querry);

        return queryBuilder;
    }
}
