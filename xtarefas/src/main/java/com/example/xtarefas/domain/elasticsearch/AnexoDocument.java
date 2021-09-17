package com.example.xtarefas.domain.elasticsearch;

import com.example.xtarefas.domain.Tarefa;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.*;


@Getter
@Setter
@Document(indexName = "index-anexo")
@NoArgsConstructor
public class AnexoDocument extends BaseDocument {

    @MultiField(mainField = @Field(type = FieldType.Text,
            store = true, analyzer = TRIM_CASE_INSENSITIVE, fielddata = true),
            otherFields = {@InnerField(suffix = SORT, type = FieldType.Text,
                    store = true, analyzer = TRIM_CASE_INSENSITIVE, fielddata = true)})
    private String file;

    @MultiField(mainField = @Field(type = FieldType.Text,
            store = true, analyzer = TRIM_CASE_INSENSITIVE, fielddata = true),
            otherFields = {@InnerField(suffix = SORT, type = FieldType.Text,
                    store = true, analyzer = TRIM_CASE_INSENSITIVE, fielddata = true)})
    private String fileName;

    @MultiField(mainField = @Field(type = FieldType.Text,
            store = true, analyzer = TRIM_CASE_INSENSITIVE, fielddata = true),
            otherFields = {@InnerField(suffix = SORT, type = FieldType.Text,
                    store = true, analyzer = TRIM_CASE_INSENSITIVE, fielddata = true)})
    private String uuid;

    @MultiField(mainField = @Field(type = FieldType.Text,
            store = true, analyzer = TRIM_CASE_INSENSITIVE, fielddata = true),
            otherFields = {@InnerField(suffix = SORT, type = FieldType.Text,
                    store = true, analyzer = TRIM_CASE_INSENSITIVE, fielddata = true)})
    private String idTarefa;

    public AnexoDocument(Long id, String file, String fileName, String uuid, Tarefa tarefa){
        this.id = id;
        this.file = file;
        this.fileName = fileName;
        this.uuid = uuid;
        this.idTarefa = tarefa.getId().toString();
    }
}
