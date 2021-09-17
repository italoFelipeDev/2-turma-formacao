package com.example.xtarefas.web.rest;

import com.example.xtarefas.builder.ResponsavelBuilder;
import com.example.xtarefas.config.containers.AbstractTestIT;
import com.example.xtarefas.config.containers.ContainersFactory;
import com.example.xtarefas.domain.Responsavel;
import com.example.xtarefas.event.ResponsavelEvent;
import com.example.xtarefas.service.ResponsavelService;
import com.example.xtarefas.service.elasticsearch.ResponsavelElasticSearchService;
import com.example.xtarefas.service.filter.ResponsavelFilter;
import com.example.xtarefas.service.mapper.ResponsavelMapper;
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

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@Transactional
@Slf4j
@Testcontainers
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ResponsavelResourceIT extends AbstractTestIT<ResponsavelResource> {

    private static final String API = "/api/responsavel/";

    @Autowired
    private ResponsavelService responsavelService;

    @Autowired
    private ResponsavelElasticSearchService elasticSearchService;

    @Autowired
    private ResponsavelMapper mapper;

    @Autowired
    private ResponsavelBuilder responsavelBuilder;

    @Container
    private ContainersFactory containers = ContainersFactory.getInstance();

    @BeforeEach
    public void init() {super.init(new ResponsavelResource(responsavelService));}

    @Test
    @DisplayName("Salvar responsavel com sucesso")
    @SneakyThrows
    public void salvarResponsavel(){

        Responsavel responsavel = responsavelBuilder.construirEntidade();

        getMockMvc().perform(post(API)
                .contentType(TesteUtil.APPLICATION_JSON_UTF8)
                .content(TesteUtil.convertObjectToJsonBytes(mapper.toDto(responsavel))))
                .andExpect(status().isCreated());

    }

    @Test
    @DisplayName("Salvar Responsavel erro")
    @SneakyThrows
    public void salvarResponsavelErro(){
        Responsavel responsavel = responsavelBuilder.construirEntidade();
        responsavel.setDataNascimento(LocalDate.now().plusDays(2));

        getMockMvc().perform(post(API)
                .contentType(TesteUtil.APPLICATION_JSON_UTF8)
                .content(TesteUtil.convertObjectToJsonBytes(mapper.toDto(responsavel))))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Buscar todos Responsaveis")
    @SneakyThrows
    public void buscarTodos(){
        responsavelBuilder.construir();
        responsavelBuilder.construir();

        getMockMvc().perform(get(API))
                .andExpect(jsonPath("$.[*]").isNotEmpty())
                .andExpect(status().isOk());

    }

    @Test
    @DisplayName("Buscar Responsavel Por id ")
    @SneakyThrows
    public void buscarPorId(){
        Responsavel responsavel = responsavelBuilder.construir();

        getMockMvc().perform(get(API + responsavel.getId()))
                .andExpect(jsonPath("$.nome").value(responsavel.getNome()))
                .andExpect(jsonPath("$.dataNascimento").value(responsavel.getDataNascimento().toString()))
                .andExpect(jsonPath("$.email").value(responsavel.getEmail()))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Buscar Responsavel Por id inexistente ")
    @SneakyThrows
    public void buscarPorIdInexistente(){

        getMockMvc().perform(get(API + 999))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Editar com sucesso")
    @SneakyThrows
    public void editarResponsavel(){
        Responsavel responsavel = responsavelBuilder.construir();
        responsavel.setNome("Guilherme");

        getMockMvc().perform(put(API)
                .contentType(TesteUtil.APPLICATION_JSON_UTF8)
                .content(TesteUtil.convertObjectToJsonBytes(mapper.toDto(responsavel))))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Buscar responsaveis paginados")
    @SneakyThrows
    public void buscarPaginado(){

        Responsavel responsavel = responsavelBuilder.construir();
        Responsavel proximo = responsavelBuilder.customizar(cust -> cust.setNome("Guilherme")).construir();

        elasticSearchService.reindex(new ResponsavelEvent(responsavel.getId()));
        elasticSearchService.reindex(new ResponsavelEvent(proximo.getId()));

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
        Responsavel responsavel = responsavelBuilder.construir();

        getMockMvc().perform(delete(API + responsavel.getId()))
                .andExpect(status().isOk());
    }
}
