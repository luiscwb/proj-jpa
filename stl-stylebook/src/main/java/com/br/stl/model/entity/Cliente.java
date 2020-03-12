package com.br.stl.model.entity;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private String nomeCompleto;
	
	@Column(nullable = false, unique = true)
	private String cpf;
	
	@Column
	private String endereco;
	
	@Column(nullable = false)
	private String telefone;
	
//	@JsonIgnore
//	@Lob
//	@Basic(fetch = FetchType.LAZY)
//	private byte[] imagem;
	
//	@JsonInclude
//	@Transient
//	private String imagemBase64;
	
	
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;

//	public String getImagemBase64() {
//		return imagemBase64;
//	}
//
//	public void setImagemBase64(String imagemBase64) {
//		this.imagemBase64 = imagemBase64;
//	}

	//Campos obrigaotiros para cirar um cliente
	public static Cliente createInstance(String nome, String cpf, String telefone) {
		Cliente c = new Cliente();
		c.setCpf(cpf);
		c.setNomeCompleto(nome);
		c.setTelefone(telefone);
		return c;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

//	public byte[] getImagem() {
//		return imagem;
//	}
//
//	public void setImagem(byte[] imagem) {
//		this.imagem = imagem;
//	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	
}
