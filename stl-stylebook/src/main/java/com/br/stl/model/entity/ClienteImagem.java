package com.br.stl.model.entity;

import java.sql.Blob;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;


@Entity
public class ClienteImagem {

	@Id
	private Long id;

	@OneToOne
	@MapsId
	private Cliente cliente;

	@JsonIgnore
	@Lob
	private Blob imagem;
	
	@JsonInclude
	@Transient
	private String imagemBase64;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date inclussao;
	
	
	public Date getInclussao() {
		return inclussao;
	}

	public void setInclussao(Date inclussao) {
		this.inclussao = inclussao;
	}

	public String getImagemBase64() {
		return imagemBase64;
	}

	public void setImagemBase64(String imagemBase64) {
		this.imagemBase64 = imagemBase64;
	}

	
	public Blob getImagem() {
		return imagem;
	}

	public void setImagem(Blob imagem) {
		this.imagem = imagem;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
