package com.example.xtarefas.service.dto;

import com.example.xtarefas.service.enumeration.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TarefaDTO implements Serializable {

    private static final long serialVersionUID = -5784590743394761358L;

    private Long id;

    private String nome;

    private LocalDate dataConclusao;

    private LocalDate dataInicio;

    private StatusEnum status;

    private Long idResponsavel;
}
