package com.example.xtarefas.service.feign;

import com.example.xtarefas.service.dto.AnexoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient( name="xdocumentos", url ="${application.feing.url-documents}")
public interface DocumentoClient {

    @GetMapping("api/documentos/{uuid}")
    String buscarDocument(@PathVariable("uuid")String uuid);

    @PostMapping("api/documentos")
    String salvar(AnexoDTO anexoDTO);

    @DeleteMapping("api/documentos/{uuid}")
    void deletar(@PathVariable("uuid") String uuid);
}
