package com.example.xtarefas.domain.elasticsearch;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.*;

import java.io.Serializable;

@Getter
@Setter
@Setting(settingPath = "/config/elastic/config.json")
public class BaseDocument implements Serializable {

    private static final long serialVersionUID = -788735380502352059L;

    protected static final String TRIM_CASE_INSENSITIVE = "trim_case_insensitive";
    protected static final String SORT = "sort";
    protected static final String DATE_PATTERN = "dd/MM/yyyy";

    @MultiField(mainField = @Field(type = FieldType.Long, store = true),
            otherFields = {@InnerField(suffix = SORT,type = FieldType.Long, store = true)})
    protected Long id;

}
