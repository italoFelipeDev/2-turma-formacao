package com.example.xtarefas.domain;

import com.example.xtarefas.service.enumeration.StatusEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "TB_TAREFA")
@Getter
@Setter
@NoArgsConstructor
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_TAREFA")
    @SequenceGenerator(name = "SQ_TAREFA", sequenceName = "SQ_TAREFA", allocationSize = 1)
    @Column(name = "CO_TAREFA")
    private Long id;

    @Column(name = "NO_NOME")
    private String nome;

    @Column(name = "DT_CONCLUSAO")
    private LocalDate dataConclusao;

    @Column(name = "DT_INICIO")
    private LocalDate dataInicio;

    @Column(name = "NU_STATUS")
    private StatusEnum status;

    @OneToOne
    @JoinColumn(name = "CO_RESPONSAVEL")
    private Responsavel responsavel;

}
