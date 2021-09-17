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
public class AnexoFilter extends DefaultFilter implements BaseFilter, Serializable {

    private static final long serialVersionUID = 648341667919234090L;

    @Override
    public BoolQueryBuilder getFilter() {
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();

        List<String> fields = new ArrayList<>();
        filterFields(fields,querry, queryBuilder, "file","fileName","uuid","idTarefa");

        return queryBuilder;
    }
}
