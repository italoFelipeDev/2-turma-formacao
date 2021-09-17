package com.example.xtarefas.web.rest;

import com.example.xtarefas.domain.elasticsearch.AnexoDocument;
import com.example.xtarefas.service.AnexoService;
import com.example.xtarefas.service.dto.AnexoDTO;
import com.example.xtarefas.service.filter.AnexoFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/anexo")
@RequiredArgsConstructor
public class AnexoResource {

    private final AnexoService anexoService;

    @PostMapping
    public ResponseEntity<AnexoDTO> salvar(@RequestBody AnexoDTO anexoDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(anexoService.salvar(anexoDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnexoDTO> obterPorId(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(anexoService.obterPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<AnexoDTO>> obterPorId(){
        return ResponseEntity.ok(anexoService.obterTodos());
    }

    @PutMapping
    public ResponseEntity<AnexoDTO> editar(@RequestBody AnexoDTO anexoDTO) throws Exception {
        return ResponseEntity.ok(anexoService.editar(anexoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable Long id) throws Exception {
        anexoService.deletarPorId(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/pesquisar")
    public ResponseEntity<Page<AnexoDocument>> pesquisar(@RequestBody AnexoFilter filter, Pageable pageable){
        return ResponseEntity.ok(anexoService.pesquisar(filter,pageable));
    }
}
