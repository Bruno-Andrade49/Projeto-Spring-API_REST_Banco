package com.si.selecao.java.entities;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
public class Transacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idTransacao;

	@JsonIgnore
	@ManyToOne(optional=true, fetch=FetchType.EAGER)
	@JoinColumn(name = "conta_Id", nullable = false)
	private Conta id_Conta;

	private double valor;
	
	private LocalDateTime dataTransacao;

	public Transacao(Conta id_Conta, double valor, LocalDateTime dataTransacao) {
		this.id_Conta = id_Conta;
		this.valor = valor;
		this.dataTransacao = dataTransacao;
	}

	public Transacao() {

	}

	public long getIdTransacao() {
		return idTransacao;
	}

	public Conta getId_Conta() {
		return id_Conta;
	}

	public void setId_Conta(Conta id_Conta) {
		this.id_Conta = id_Conta;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public LocalDateTime getDataTransacao() {
		return dataTransacao;
	}

	public void setDataTransacao(LocalDateTime localDateTime) {
		this.dataTransacao = localDateTime;
	}

}
