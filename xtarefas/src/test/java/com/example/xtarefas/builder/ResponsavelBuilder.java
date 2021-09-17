package com.example.xtarefas.builder;

import com.example.xtarefas.domain.Responsavel;
import com.example.xtarefas.service.ResponsavelService;
import com.example.xtarefas.service.dto.ResponsavelDTO;
import com.example.xtarefas.service.mapper.ResponsavelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Collection;

@Component
public class ResponsavelBuilder extends ConstrutorDeEntidade<Responsavel> {

    @Autowired
    private ResponsavelService responsavelService;

    @Autowired
    private ResponsavelMapper responsavelMapper;

    @Override
    public Responsavel construirEntidade() {
        Responsavel responsavel = new Responsavel();
        responsavel.setNome("Test");
        responsavel.setEmail("Test");
        responsavel.setDataNascimento(LocalDate.now().minusYears(21));
        return responsavel;
    }

    @Override
    protected Responsavel persistir(Responsavel entidade) {
        ResponsavelDTO dto = responsavelService.salvar(responsavelMapper.toDto(entidade));
        return responsavelMapper.toEntity(dto);
    }

    @Override
    public Responsavel obterPorId(Long id) {
        return null;
    }

    @Override
    public Collection<Responsavel> obterTodos() {
        return null;
    }
}
