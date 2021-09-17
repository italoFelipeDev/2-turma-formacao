package com.example.xtarefas.builder;

import com.example.xtarefas.domain.Responsavel;
import com.example.xtarefas.domain.Tarefa;
import com.example.xtarefas.service.TarefaService;
import com.example.xtarefas.service.dto.TarefaDTO;
import com.example.xtarefas.service.enumeration.StatusEnum;
import com.example.xtarefas.service.mapper.TarefaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Collection;

@Component
public class TarefaBuilder extends ConstrutorDeEntidade<Tarefa> {

    @Autowired
    private TarefaService tarefaService;

    @Autowired
    private TarefaMapper tarefaMapper;

    @Autowired
    private ResponsavelBuilder responsavelBuilder;

    @Override
    public Tarefa construirEntidade() {
        Responsavel responsavel = responsavelBuilder.construir();
        Tarefa tarefa = new Tarefa();
        tarefa.setNome("Test");
        tarefa.setStatus(StatusEnum.INCOMPLETA);
        tarefa.setDataInicio(LocalDate.now().minusYears(21));
        tarefa.setDataConclusao(LocalDate.now().minusYears(11));
        tarefa.setResponsavel(responsavel);

        return tarefa;
    }

    @Override
    protected Tarefa persistir(Tarefa entidade) {
        TarefaDTO dto = tarefaService.salvar(tarefaMapper.toDto(entidade));
        return tarefaMapper.toEntity(dto);
    }

    @Override
    public Tarefa obterPorId(Long id) {
        return null;
    }

    @Override
    public Collection<Tarefa> obterTodos() {
        return null;
    }
}
