package com.example.xtarefas.web.rest;

import com.example.xtarefas.domain.elasticsearch.ResponsavelDocument;
import com.example.xtarefas.service.ResponsavelService;
import com.example.xtarefas.service.dto.ResponsavelDTO;
import com.example.xtarefas.service.filter.ResponsavelFilter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/responsavel")
@RequiredArgsConstructor
@Slf4j
public class ResponsavelResource {

    private final ResponsavelService responsavelService;



    @PostMapping
    public ResponseEntity<ResponsavelDTO> salvar(@RequestBody ResponsavelDTO responsavelDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(responsavelService.salvar(responsavelDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponsavelDTO> obterPorId(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok().body(responsavelService.obterPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<ResponsavelDTO>> obterPorId(){
        return ResponseEntity.ok().body(responsavelService.obterTodos());
    }

    @PutMapping
    public ResponseEntity<ResponsavelDTO> editar(@RequestBody ResponsavelDTO responsavelDTO) throws Exception {
        return ResponseEntity.ok(responsavelService.editar(responsavelDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable Long id){
        responsavelService.deletarPorId(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/pesquisar")
    public ResponseEntity<Page<ResponsavelDocument>> pesquisar(@RequestBody ResponsavelFilter filter, Pageable pageable){
        return ResponseEntity.ok(responsavelService.pesquisar(filter,pageable));
    }
}
