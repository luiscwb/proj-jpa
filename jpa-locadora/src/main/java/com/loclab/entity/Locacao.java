package com.loclab.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"cliente_id", "local_id"})})//So uma locacao por cleinte do mesmo local
public class Locacao {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private Date dataInicial;
	
	@Column(nullable = false)
	private Date dataFinal;
	
	//Relacao bidrecional com Cliente:
	@ManyToOne(fetch = FetchType.LAZY) //Importante para nao gerar inner join desnecessario
	@JoinColumn(name = "cliente_id", nullable = false)
	private Cliente cliente;

	//Relacao bidirecional com Local
	@ManyToOne(fetch = FetchType.LAZY) //Importante para nao gerar inner join desnecessario
	@JoinColumn(name = "local_id", nullable = false)
	private Local local;
	
	// O id melhor deixar ser gerenciado pelo Hibarnate
	public static Locacao createInstance(Cliente cliente, Local local, Date dtIni, Date dtFim) {
		Locacao locacao = new Locacao();
		locacao.cliente = cliente;
		locacao.local = local;
		locacao.setDataInicial(dtIni);
		locacao.setDataFinal(dtFim);
		return locacao;
	}
	
	
	public Long getLocacaoId() {
		return id;
	}

	public Local getLocal() {
		return local;
	}

	public void setLocal(Local local) {
		this.local = local;
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
