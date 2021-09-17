package com.example.xtarefas.service;

import com.example.xtarefas.domain.Anexo;
import com.example.xtarefas.domain.elasticsearch.AnexoDocument;
import com.example.xtarefas.event.AnexoEvent;
import com.example.xtarefas.repository.AnexoRepository;
import com.example.xtarefas.repository.elastic.AnexoSearchRepository;
import com.example.xtarefas.service.dto.AnexoDTO;
import com.example.xtarefas.service.exception.BadRequestException;
import com.example.xtarefas.service.feign.DocumentoClient;
import com.example.xtarefas.service.filter.AnexoFilter;
import com.example.xtarefas.service.mapper.AnexoMapper;
import com.example.xtarefas.service.util.ConstanteUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class AnexoService {

    private final AnexoRepository anexoRepository;

    private final AnexoMapper anexoMapper;

    private final DocumentoClient documentoClient;

    private final ApplicationEventPublisher applicationEventPublisher;

    private final AnexoSearchRepository anexoSearchRepository;

    public AnexoDTO salvar(AnexoDTO anexoDTO){
        anexoDTO.setUuid(UUID.randomUUID().toString());
        documentoClient.salvar(anexoDTO);
        Anexo anexo = anexoMapper.toEntity(anexoDTO);
        anexoRepository.save(anexo);
        applicationEventPublisher.publishEvent(new AnexoEvent(anexo.getId()));
        return anexoMapper.toDto(anexo);
    }

    public AnexoDTO editar(AnexoDTO anexoDTO) throws Exception {
        anexoRepository.findById(anexoDTO.getId()).orElseThrow(() -> new BadRequestException(ConstanteUtil.MENSAGEM_ERRO_ANEXO));
        return anexoMapper.toDto(anexoRepository.save(anexoMapper.toEntity(anexoDTO)));
    }

    public List<AnexoDTO> obterTodos(){
        return anexoMapper.toListagemDto(anexoRepository.findAll());
    }

    public AnexoDTO obterPorId(Long id) throws Exception {
        AnexoDTO anexo = anexoMapper.toDto(anexoRepository.findById(id)
                .orElseThrow(() -> new BadRequestException(ConstanteUtil.MENSAGEM_ERRO_ANEXO)));
        String file = documentoClient.buscarDocument(anexo.getUuid());
        if(Objects.isNull(file)){
            throw new BadRequestException(ConstanteUtil.MENSAGEM_ERRO_ANEXO);
        }
        anexo.setFile(file);
        return anexo;
    }

    public void deletarPorId(Long id) throws Exception {
        AnexoDTO anexo = this.obterPorId(id);
        documentoClient.deletar(anexo.getFile());
        anexoRepository.deleteById(id);
    }

    public Page<AnexoDocument> pesquisar(AnexoFilter anexoFilter, Pageable pageable){
        return anexoSearchRepository.search(anexoFilter.getFilter(),pageable);
    }
}
