package com.br.stl.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@Entity
public class Imagem {

	@Id
	private Long id;
	
	@Lob
	@Column(nullable = false)
	private String imagem;

	@Transient
	private String nomeArquivo;
	
	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

	public Long getId() {
		return id;
	}

	public String getImagem() {
		return imagem;
	}


	public void setImagem(String imagem) {
		this.imagem = imagem;
	}


	public ClienteImagem getClienteImagem() {
		return clienteImagem;
	}


	public void setClienteImagem(ClienteImagem clienteImagem) {
		this.clienteImagem = clienteImagem;
	}


	@OneToOne(fetch = FetchType.LAZY)
	@MapsId
	private ClienteImagem clienteImagem;
	

}
