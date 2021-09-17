package com.example.xtarefas.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="TB_RESPONSAVEL")
@Getter
@Setter
@NoArgsConstructor
public class Responsavel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_RESPONSAVEL")
    @SequenceGenerator(name = "SQ_RESPONSAVEL", sequenceName = "SQ_RESPONSAVEL", allocationSize = 1)
    @Column(name = "CO_RESPONSAVEL")
    private Long id;

    @Column(name = "NO_NOME")
    private String nome;

    @Column(name = "NO_EMAIL")
    private String email;

    @Column(name = "DT_DATA_NASCIMENTO")
    private LocalDate dataNascimento;
}
