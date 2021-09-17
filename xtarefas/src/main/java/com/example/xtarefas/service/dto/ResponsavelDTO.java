package com.example.xtarefas.service.dto;

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
public class ResponsavelDTO implements Serializable {

    private static final long serialVersionUID = -950635477175072248L;

    private Long id;

    private String nome;

    private String email;

    private LocalDate dataNascimento;
}
