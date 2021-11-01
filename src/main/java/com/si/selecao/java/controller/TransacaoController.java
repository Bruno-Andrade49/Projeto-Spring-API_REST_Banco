package com.si.selecao.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.si.selecao.java.entities.Conta;
import com.si.selecao.java.entities.Transacao;
import com.si.selecao.java.repositories.TransacaoRepository;

@RestController
public class TransacaoController {
	
	
	
	@Autowired
	private TransacaoRepository transacaoRepository;
	

	
	@PostMapping(path = "transacao/salvar/")
	public Transacao salvarTransacao(@RequestBody Transacao trans){
		Transacao transacao = transacaoRepository.findAll().get(0);
		transacao.setId_Conta(new Conta());
		return transacaoRepository.save(trans);
	}
	
}
