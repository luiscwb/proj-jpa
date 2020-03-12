package com.br.stl.model.entity;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import com.br.stl.util.Uteis;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"cliente_id", "hash_imagem"})})
public class ClienteImagem {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cliente_id", nullable = false)
	private Cliente cliente;
	
	@JsonIgnore
	@Temporal(TemporalType.DATE)
	private Date inclussao;

	@JsonIgnore
	@Column(name = "hash_imagem", length = 32, nullable = false)
	private String hashImagem;
	
	@Column(nullable = false)
	private String nomeArquivo;
	
	
	public static ClienteImagem createInstance(Cliente cliente, Imagem imagem, String nomeArquivo) {
		
		ClienteImagem cliImg = new ClienteImagem();
		
		cliImg.setCliente(cliente);
		cliImg.setHashImagem(Uteis.getMD5Hash(imagem.getImagem()));
		cliImg.setInclussao(new Date());
		cliImg.setNomeArquivo(nomeArquivo);
		
		return cliImg;
	}
	
	public String getNomeArquivo() {
		return nomeArquivo;
	}


	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}


	public String getHashImagem() {
		return hashImagem;
	}


	public void setHashImagem(String hashImagem) {
		this.hashImagem = hashImagem;
	}
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Date getInclussao() {
		return inclussao;
	}

	public void setInclussao(Date inclussao) {
		this.inclussao = inclussao;
	}
		
}