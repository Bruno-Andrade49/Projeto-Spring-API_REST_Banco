package com.si.selecao.java.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.si.selecao.java.entities.Conta;

@Transactional(readOnly = false)
@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {

	@Modifying
	@Query("update Conta c set c.saldo = c.saldo + ?1 where c.idConta = ?2")
	void realizarDeposito(double quantidade, Long id);

	@Modifying
	@Query("update Conta c set c.saldo = c.saldo - ?1 where c.idConta = ?2")
	void realizarSaque(double quantidade, Long id);
	
	Conta findByIdConta(Long id);

}
