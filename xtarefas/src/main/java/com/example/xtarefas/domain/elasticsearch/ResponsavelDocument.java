package com.example.xtarefas.domain.elasticsearch;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Getter
@Setter
@Document(indexName = "index-responsavel")
@NoArgsConstructor
public class ResponsavelDocument extends BaseDocument{

    @MultiField(mainField = @Field(type = FieldType.Text,
            store = true, analyzer = TRIM_CASE_INSENSITIVE, fielddata = true),
            otherFields = {@InnerField(suffix = SORT, type = FieldType.Text,
                    store = true, analyzer = TRIM_CASE_INSENSITIVE, fielddata = true)})
    private String nome;

    @MultiField(mainField = @Field(type = FieldType.Text,
            store = true, analyzer = TRIM_CASE_INSENSITIVE, fielddata = true),
            otherFields = {@InnerField(suffix = SORT, type = FieldType.Text,
                    store = true, analyzer = TRIM_CASE_INSENSITIVE, fielddata = true)})
    private String email;

    @MultiField(mainField = @Field(type = FieldType.Keyword, store = true),
            otherFields = {@InnerField(suffix = SORT, type = FieldType.Date,
                    store = true, format = DateFormat.custom, pattern = DATE_PATTERN)})
    private String dataNascimento;

    public ResponsavelDocument(Long id,String nome, String email, LocalDate dataNascimento) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.dataNascimento = Objects.isNull(dataNascimento) ?
                dataNascimento.format(DateTimeFormatter.ofPattern(DATE_PATTERN)) : null ;
    }
}
