package com.example.xtarefas.web.rest;

import com.example.xtarefas.domain.elasticsearch.TarefaDocument;
import com.example.xtarefas.service.TarefaService;
import com.example.xtarefas.service.dto.TarefaDTO;
import com.example.xtarefas.service.filter.TarefaFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tarefa")
@RequiredArgsConstructor
public class TarefaResource {

    private final TarefaService tarefaService;

    @PostMapping
    public ResponseEntity<TarefaDTO> salvar(@RequestBody TarefaDTO tarefaDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tarefaService.salvar(tarefaDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TarefaDTO> obterPorId(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok().body(tarefaService.obterPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<TarefaDTO>> obterPorId(){
        return ResponseEntity.ok().body(tarefaService.obterTodos());
    }

    @PutMapping
    public ResponseEntity<TarefaDTO> editar(@RequestBody TarefaDTO tarefaDTO) throws Exception {
        return ResponseEntity.ok(tarefaService.editar(tarefaDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable Long id){
        tarefaService.deletarPorId(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/status/{id}")
    public ResponseEntity<TarefaDTO> alterarStatus(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok().body(tarefaService.alterarStatus(id));
    }

    @PostMapping("/pesquisar")
    public ResponseEntity<Page<TarefaDocument>> pesquisar(@RequestBody TarefaFilter filter, Pageable pageable){
        return ResponseEntity.ok(tarefaService.pesquisar(filter,pageable));
    }
}
