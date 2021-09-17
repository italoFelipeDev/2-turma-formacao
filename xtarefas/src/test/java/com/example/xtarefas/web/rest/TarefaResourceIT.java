package com.example.xtarefas.web.rest;

import com.example.xtarefas.builder.TarefaBuilder;
import com.example.xtarefas.config.containers.AbstractTestIT;
import com.example.xtarefas.config.containers.ContainersFactory;
import com.example.xtarefas.domain.Tarefa;
import com.example.xtarefas.event.TarefaEvent;
import com.example.xtarefas.service.TarefaService;
import com.example.xtarefas.service.elasticsearch.TarefaElasticSearchService;
import com.example.xtarefas.service.enumeration.StatusEnum;
import com.example.xtarefas.service.filter.ResponsavelFilter;
import com.example.xtarefas.service.mapper.TarefaMapper;
import com.example.xtarefas.util.TesteUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@Transactional
@Slf4j
@Testcontainers
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TarefaResourceIT extends AbstractTestIT<TarefaResource> {

    private static final String API = "/api/tarefa/";

    @Autowired
    private TarefaService tarefaService;

    @Autowired
    private TarefaElasticSearchService elasticSearchService;

    @Autowired
    private TarefaMapper mapper;

    @Autowired
    private TarefaBuilder tarefaBuilder;

    @Container
    private ContainersFactory containers = ContainersFactory.getInstance();

    @BeforeEach
    public void init() {super.init(new TarefaResource(tarefaService));}

    @Test
    @DisplayName("Salvar tarefa com sucesso")
    @SneakyThrows
    public void salvarTarefa(){

        Tarefa tarefa = tarefaBuilder.construirEntidade();

        getMockMvc().perform(post(API)
                .contentType(TesteUtil.APPLICATION_JSON_UTF8)
                .content(TesteUtil.convertObjectToJsonBytes(mapper.toDto(tarefa))))
                .andExpect(status().isCreated());

    }

    @Test
    @DisplayName("Buscar todas as tarefas")
    @SneakyThrows
    public void buscarTodos(){

        tarefaBuilder.construir();
        tarefaBuilder.construir();

        getMockMvc().perform(get(API))
                .andExpect(jsonPath("$.[*]").isNotEmpty())
                .andExpect(status().isOk());

    }

    @Test
    @DisplayName("Buscar tarefa Por id ")
    @SneakyThrows
    public void buscarPorId(){

        Tarefa tarefa = tarefaBuilder.construir();

        getMockMvc().perform(get(API + tarefa.getId()))
                .andExpect(jsonPath("$.nome").value(tarefa.getNome()))
                .andExpect(jsonPath("$.idResponsavel").value(tarefa.getResponsavel().getId().toString()))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Buscar tarefa Por id inexistente ")
    @SneakyThrows
    public void buscarPorIdInexistente(){

        getMockMvc().perform(get(API + 999))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Editar com sucesso")
    @SneakyThrows
    public void editarTarefa(){
        Tarefa tarefa = tarefaBuilder.construir();
        tarefa.setNome("A");

        getMockMvc().perform(put(API)
                .contentType(TesteUtil.APPLICATION_JSON_UTF8)
                .content(TesteUtil.convertObjectToJsonBytes(mapper.toDto(tarefa))))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Buscar responsaveis paginados")
    @SneakyThrows
    public void buscarPaginado(){

        Tarefa tarefa = tarefaBuilder.construir();
        Tarefa proximo = tarefaBuilder.customizar(cust -> cust.setNome("A")).construir();

        elasticSearchService.reindex(new TarefaEvent(tarefa.getId()));
        elasticSearchService.reindex(new TarefaEvent(proximo.getId()));

        ResponsavelFilter filtro = new ResponsavelFilter();
        filtro.setQuerry(proximo.getNome());

        getMockMvc().perform(post(API + "pesquisar")
                .contentType(TesteUtil.APPLICATION_JSON_UTF8)
                .content(getConverter().getObjectMapper().writeValueAsBytes(filtro)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isNotEmpty());
    }

    @Test
    @DisplayName("Deletar Responsavel por Id")
    @SneakyThrows
    public void deletarPorId(){
        Tarefa tarefa = tarefaBuilder.construir();

        getMockMvc().perform(delete(API + tarefa.getId()))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Alterar status")
    @SneakyThrows
    public void alterarStatus(){
        Tarefa tarefa = tarefaBuilder.construir();

        getMockMvc().perform(put(API + "/status/" + tarefa.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(StatusEnum.CONCLUIDA.toString()));
    }
}
