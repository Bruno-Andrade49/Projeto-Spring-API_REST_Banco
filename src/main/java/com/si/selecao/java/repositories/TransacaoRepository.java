package com.si.selecao.java.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.si.selecao.java.entities.Transacao;


@Transactional(readOnly = false)
@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long>{
}
