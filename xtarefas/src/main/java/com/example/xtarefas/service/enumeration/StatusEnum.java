package com.example.xtarefas.service.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum StatusEnum {
    CONCLUIDA(0),
    INCOMPLETA(1);


    private Integer valor;
}
