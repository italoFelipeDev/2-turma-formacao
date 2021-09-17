package com.example.xtarefas.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AnexoDTO implements Serializable {

    private static final long serialVersionUID = -5500836398711049447L;

    private Long id;

    private String file;

    private String fileName;

    private String uuid;

    private Long idTarefa;
}
