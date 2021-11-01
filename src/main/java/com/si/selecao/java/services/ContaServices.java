package com.si.selecao.java.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.si.selecao.java.repositories.ContaRepository;

@Service
public class ContaServices {

	@Autowired
	private ContaRepository contaRepository;

	public void deposita(double quantidade, Long id) {

		contaRepository.realizarDeposito(quantidade, id);
	}

	public void sacar(double quantidade, Long id) {
		contaRepository.realizarSaque(quantidade, id);
	}

}
