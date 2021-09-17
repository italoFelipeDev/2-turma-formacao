package com.example.xtarefas.repository;

import com.example.xtarefas.domain.Responsavel;
import com.example.xtarefas.domain.elasticsearch.ResponsavelDocument;
import com.example.xtarefas.repository.elastic.Reindexer;
import feign.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponsavelRepository extends JpaRepository<Responsavel,Long>, Reindexer {

    @Query("select new com.example.xtarefas.domain.elasticsearch.ResponsavelDocument("
            + "r.id, r.nome, r.email, r.dataNascimento) from Responsavel r where r.id = :id")
    ResponsavelDocument getDocument(@Param("id") Long id);

    @Query("select new com.example.xtarefas.domain.elasticsearch.ResponsavelDocument("
            + "r.id, r.nome, r.email, r.dataNascimento) from Responsavel r order by r.id")
    Page<ResponsavelDocument> reindexPage(Pageable pageable);

    @Override
    default String getEntity() {
        return "responsavel";
    }
}
