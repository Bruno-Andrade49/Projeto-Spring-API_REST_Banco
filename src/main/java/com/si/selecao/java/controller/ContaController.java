package com.si.selecao.java.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.si.selecao.java.entities.Conta;
import com.si.selecao.java.entities.Transacao;
import com.si.selecao.java.repositories.ContaRepository;
import com.si.selecao.java.repositories.TransacaoRepository;
import com.si.selecao.java.services.ContaServices;

@RestController
public class ContaController {

	@Autowired
	ContaRepository conRepository;

	@Autowired
	TransacaoRepository trRepository;

	@Autowired
	private ContaServices contaService;

	@GetMapping(path = "/conta")
	public List<Conta> getAllConta() {
		
		return conRepository.findAll();
		
	}

	// Criação de conta
	@PostMapping(path = "/conta")
	public Conta abrirConta(@RequestBody Conta conta) {
		
		conta.setDataCriaçao(LocalDateTime.now());
		return conRepository.save(conta);
		
	}

	// Depositar um valor na conta
	@PutMapping(path = "/conta/depositar/{quantidade}/{idConta}")
	public ResponseEntity<?> depositar(@PathVariable double quantidade, @PathVariable long idConta) {
		
		Conta conta = conRepository.findByIdConta(idConta);
		
		if (conta.isFlagAtivo() == true && quantidade > 0) {
			
			this.contaService.deposita(quantidade, idConta);
			
			Transacao transacao = new Transacao(conta, quantidade, LocalDateTime.now());
			trRepository.save(transacao);
			
			return new ResponseEntity<>("Depósito efetuado!", HttpStatus.OK);
			
		} else {
			
			return new ResponseEntity<>("Depósito não efetuado!", HttpStatus.OK);

		}
	}

	// Consultar saldo
	@GetMapping(path = "/conta/saldo/{idConta}")
	public String consultarSaldo(@PathVariable("idConta") long idConta) {
		
		return "Valor do saldo: " + conRepository.findById(idConta).get().getSaldo();

	}

	// Sacar um valor da conta
	@PutMapping(path = "conta/sacar/{quantidade}/{idConta}")
	public ResponseEntity<?> sacar(@PathVariable double quantidade, @PathVariable Long idConta) {
		
		Conta conta = conRepository.findByIdConta(idConta);

		if (quantidade > 0 && quantidade < conta.getSaldo() &&conta.getLimiteSaqueDiario() >= quantidade && conta.isFlagAtivo() == true) {
			
			this.contaService.sacar(quantidade, idConta);
			
			Transacao transacao = new Transacao(conta, quantidade, LocalDateTime.now());
			trRepository.save(transacao);
			return new ResponseEntity<>("Saque efetuado!", HttpStatus.OK);

		} else {
		
			return new ResponseEntity<>("Saque não efetuado!", HttpStatus.OK);

		}

	}

	// Visualizar extrato bancario
	@GetMapping(path = "conta/extrato/{idConta}")
	public List<Transacao> mostrarExtrato(@PathVariable Long idConta) {
		
		List<Transacao> transactions = conRepository.findByIdConta(idConta).getTransacoes();
		return transactions;
	}

	// Realizar um bloqueio de conta
	@PutMapping(path = "conta/bloqueio/{idConta}")
	public ResponseEntity<?> bloqueio(@PathVariable long idConta) {
		
		Conta conta = conRepository.findByIdConta(idConta);
		
		conta.setFlagAtivo(false);
		
		conRepository.save(conta);
		
		return new ResponseEntity<>("Bloqueio efetuado com sucesso!", HttpStatus.OK);
	}

}
