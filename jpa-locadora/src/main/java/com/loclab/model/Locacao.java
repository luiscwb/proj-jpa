package com.loclab.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"cliente_id", "espaco_id"})})//So uma locacao por cleinte do mesmo espaco
public class Locacao {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private Date dataInicial;
	
	@Column(nullable = false)
	private Date dataFinal;
	
	//Relacao bidrecional com Cliente:
	@ManyToOne//(fetch = FetchType.LAZY)
	@JoinColumn(name = "cliente_id", nullable = false)
	private Cliente cliente;

	//Relacao bidirecional com Espaco
	@ManyToOne//(fetch = FetchType.LAZY)
	@JoinColumn(name = "espaco_id", nullable = false)
	private Espaco espaco;
	
	public static Locacao createInstance(Cliente cliente, Espaco espaco, Date dtIni, Date dtFim) {
		Locacao locacao = new Locacao();
		locacao.cliente = cliente;
		locacao.espaco = espaco;
		locacao.setDataInicial(dtIni);
		locacao.setDataFinal(dtFim);
		return locacao;
	}
	
	
	public Long getLocacaoId() {
		return id;
	}

	public Espaco getEspaco() {
		return espaco;
	}

	public void setEspaco(Espaco espaco) {
		this.espaco = espaco;
	}

	public void setLocacaoId(Long locacaoId) {
		this.id = locacaoId;
	}

	public Date getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}


	public Long getId() {
		return this.id;
	}
	
}
