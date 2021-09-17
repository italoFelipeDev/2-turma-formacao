package com.example.xtarefas.service.filter;

import lombok.Getter;
import lombok.Setter;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class TarefaFilter  extends DefaultFilter implements BaseFilter, Serializable {

    private static final long serialVersionUID = 4827497157954010419L;

    @Override
    public BoolQueryBuilder getFilter() {
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();

        List<String> fields = new ArrayList<>();
        filterFields(fields,querry, queryBuilder, "nome","status","idResponsavel");

        addShouldTermQuery(queryBuilder,"dataConclusao", querry);
        addShouldTermQuery(queryBuilder,"dataInicio", querry);

        return queryBuilder;
    }
}
