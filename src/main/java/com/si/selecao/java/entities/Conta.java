package com.si.selecao.java.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class Conta implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idConta;

	private long pessoaId;

	@Column(nullable = false)
	private double saldo;
	
	private double limiteSaqueDiario;
	
	private boolean flagAtivo;
	
	private int tipoConta;


	private LocalDateTime dataCriaçao;

	
	@OneToMany(mappedBy = "id_Conta")
	private List<Transacao> transacoes;

	public Conta( long pessoaId, double saldo, double limiteSaqueDiario, boolean flagAtivo, int tipoConta, LocalDateTime dataCriaçao) {
		this.pessoaId = pessoaId;
		this.saldo = saldo;
		this.limiteSaqueDiario = limiteSaqueDiario;
		this.flagAtivo = flagAtivo;
		this.tipoConta = tipoConta;
		this.dataCriaçao = dataCriaçao;
	}

	public Conta() {

	}
	public List<Transacao> getTransacoes() {
		return transacoes;
	}

	public long getIdConta() {
		return idConta;
	}

	public void setIdConta(long idConta) {
		this.idConta = idConta;
	}

	public long getPessoaId() {
		return pessoaId;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public double getLimiteSaqueDiario() {
		return limiteSaqueDiario;
	}

	public void setLimiteSaqueDiario(double limiteSaqueDiario) {
		this.limiteSaqueDiario = limiteSaqueDiario;
	}

	public boolean isFlagAtivo() {
		return flagAtivo;
	}

	public void setFlagAtivo(boolean flagAtivo) {
		this.flagAtivo = flagAtivo;
	}

	public int getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(int tipoConta) {
		this.tipoConta = tipoConta;
	}

	public LocalDateTime getDataCriaçao() {
		return dataCriaçao;
	}

	public void setDataCriaçao(LocalDateTime data) {
		this.dataCriaçao = data;
	}

}
