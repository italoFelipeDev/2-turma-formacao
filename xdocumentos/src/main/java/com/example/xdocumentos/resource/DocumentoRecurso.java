package com.example.xdocumentos.resource;

import com.example.xdocumentos.service.DocumentoService;
import com.example.xdocumentos.service.dto.DocumentoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/documentos")
@RequiredArgsConstructor
public class DocumentoRecurso {

    private final DocumentoService documentoService;

    @GetMapping("/{uuid}")
    public ResponseEntity<DocumentoDTO> buscar(@PathVariable("uuid") String uuid){
        return ResponseEntity.ok(documentoService.getDocument(uuid));
    }

    @PostMapping
    public ResponseEntity<String> salvar(@RequestBody DocumentoDTO documentoDTO){
        return ResponseEntity.ok(documentoService.save(documentoDTO));
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> deletar(@PathVariable("uuid") String uuid){
        documentoService.deletar(uuid);
        return ResponseEntity.ok().build();
    }
}
