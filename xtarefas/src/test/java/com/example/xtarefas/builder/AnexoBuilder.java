package com.example.xtarefas.builder;

import com.example.xtarefas.domain.Anexo;
import com.example.xtarefas.service.AnexoService;
import com.example.xtarefas.service.dto.AnexoDTO;
import com.example.xtarefas.service.mapper.AnexoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class AnexoBuilder extends ConstrutorDeEntidade<Anexo>{

    @Autowired
    private AnexoService anexoService;

    @Autowired
    private AnexoMapper anexoMapper;

    @Autowired
    private TarefaBuilder tarefaBuilder;

    @Override
    public Anexo construirEntidade() {
        Anexo anexo = new Anexo();
        anexo.setFile("arquivo.pdf");
        anexo.setFileName("Test");
        anexo.setTarefa(tarefaBuilder.construir());
        return anexo;
    }

    @Override
    protected Anexo persistir(Anexo entidade) {
        AnexoDTO dto = anexoService.salvar(anexoMapper.toDto(entidade));
        return anexoMapper.toEntity(dto);
    }

    @Override
    public Anexo obterPorId(Long id) {
        return null;
    }

    @Override
    public Collection<Anexo> obterTodos() {
        return null;
    }
}
