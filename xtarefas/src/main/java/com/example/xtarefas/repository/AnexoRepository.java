package com.example.xtarefas.repository;

import com.example.xtarefas.domain.Anexo;
import com.example.xtarefas.domain.elasticsearch.AnexoDocument;
import com.example.xtarefas.repository.elastic.Reindexer;
import feign.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AnexoRepository extends JpaRepository<Anexo,Long>, Reindexer {

    @Query("select new com.example.xtarefas.domain.elasticsearch.AnexoDocument("
            + "a.id, a.file, a.fileName, a.uuid, a.tarefa) from Anexo a where a.id = :id")
    AnexoDocument getDocument(@Param("id") Long id);

    @Query("select new com.example.xtarefas.domain.elasticsearch.AnexoDocument("
            + "a.id, a.file, a.fileName, a.uuid, a.tarefa) from Anexo a order by a.id")
    Page<AnexoDocument> reindexPage(Pageable pageable);

    @Override
    default String getEntity() {
        return "anexo";
    }
}
