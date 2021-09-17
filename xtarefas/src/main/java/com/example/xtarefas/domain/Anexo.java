package com.example.xtarefas.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="TB_ANEXO")
@Getter
@Setter
@NoArgsConstructor
public class Anexo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_ANEXO")
    @SequenceGenerator(name = "SQ_ANEXO", sequenceName = "SQ_ANEXO", allocationSize = 1)
    @Column(name = "CO_ANEXO")
    private Long id;

    @Column(name="NO_FILE")
    private String file;

    @Column(name="NO_FILENAME")
    private String fileName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="CO_TAREFA")
    private Tarefa tarefa;

    @Column(name="NU_UUID")
    private String uuid;
}
