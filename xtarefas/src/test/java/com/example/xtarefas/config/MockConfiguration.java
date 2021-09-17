package com.example.xtarefas.config;

import com.example.xtarefas.service.dto.AnexoDTO;
import com.example.xtarefas.service.feign.DocumentoClient;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class MockConfiguration {

    @MockBean
    private DocumentoClient documentClient;

    @PostConstruct
    public void mock(){
        Mockito.when(documentClient.salvar(new AnexoDTO())).thenReturn("Deu certo");
    }
}
