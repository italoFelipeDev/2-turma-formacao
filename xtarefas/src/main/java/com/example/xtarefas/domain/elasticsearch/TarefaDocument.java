package com.example.xtarefas.domain.elasticsearch;

import com.example.xtarefas.domain.Responsavel;
import com.example.xtarefas.service.enumeration.StatusEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Getter
@Setter
@Document(indexName = "index-tarefa")
@NoArgsConstructor
public class TarefaDocument extends BaseDocument {

    @MultiField(mainField = @Field(type = FieldType.Text,
            store = true, analyzer = TRIM_CASE_INSENSITIVE, fielddata = true),
            otherFields = {@InnerField(suffix = SORT, type = FieldType.Text,
                    store = true, analyzer = TRIM_CASE_INSENSITIVE, fielddata = true)})
    private String nome;

    @MultiField(mainField = @Field(type = FieldType.Keyword, store = true),
            otherFields = {@InnerField(suffix = SORT, type = FieldType.Date,
                    store = true, format = DateFormat.custom, pattern = DATE_PATTERN)})
    private String dataConclusao;

    @MultiField(mainField = @Field(type = FieldType.Keyword, store = true),
            otherFields = {@InnerField(suffix = SORT, type = FieldType.Date,
                    store = true, format = DateFormat.custom, pattern = DATE_PATTERN)})
    private String dataInicio;

    @MultiField(mainField = @Field(type = FieldType.Text,
            store = true, analyzer = TRIM_CASE_INSENSITIVE, fielddata = true),
            otherFields = {@InnerField(suffix = SORT, type = FieldType.Text,
                    store = true, analyzer = TRIM_CASE_INSENSITIVE, fielddata = true)})
    private String status;

    @MultiField(mainField = @Field(type = FieldType.Text,
            store = true, analyzer = TRIM_CASE_INSENSITIVE, fielddata = true),
            otherFields = {@InnerField(suffix = SORT, type = FieldType.Text,
                    store = true, analyzer = TRIM_CASE_INSENSITIVE, fielddata = true)})
    private String idResponsavel;

    public TarefaDocument(Long id,String nome, LocalDate dataInicio, LocalDate dataConclusao, StatusEnum status, Responsavel responsavel) {
        this.id = id;
        this.nome = nome;
        this.dataConclusao = Objects.isNull(dataConclusao) ? dataConclusao.format(DateTimeFormatter.ofPattern(DATE_PATTERN)) : null;
        this.dataInicio = Objects.isNull(dataInicio) ? dataInicio.format(DateTimeFormatter.ofPattern(DATE_PATTERN)) : null ;
        this.status = status.toString();
        this.idResponsavel = responsavel.getId().toString();
    }
}
