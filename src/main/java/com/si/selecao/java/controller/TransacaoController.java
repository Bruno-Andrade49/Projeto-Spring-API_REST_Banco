package com.si.selecao.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.si.selecao.java.repositories.TransacaoRepository;

@RestController
public class TransacaoController {
	@Autowired
	private TransacaoRepository transacaoRepository;

}
