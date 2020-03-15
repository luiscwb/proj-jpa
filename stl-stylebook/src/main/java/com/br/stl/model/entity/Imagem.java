package com.br.stl.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.br.stl.util.Uteis;


@Entity
public class Imagem {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Lob
	@Column(nullable = false)
	private String base64;

	@Column(nullable = false)
	private String descricao;
	
	@Temporal(TemporalType.DATE)
	private Date inclussao;
	
	@Column(name = "hash_imagen", length = 32, nullable = false, unique = true)
	private String hashImagem;

	// 1: Imagems do sistema
	// 2: Imagems de usuario
	@Column(nullable = false)
	int tipo;
	
	public static Imagem createInstance(String descricao, String base64, int tipo) {
		Imagem img = new Imagem();
		img.setDescricao(descricao);
		img.setHashImagem(Uteis.getMD5Hash(base64));
		img.setInclussao(new Date());
		img.setBase64(base64);
		img.setTipo(tipo);
		
		return img;
	}
	
	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBase64() {
		return base64;
	}

	public void setBase64(String imagen) {
		this.base64 = imagen;
	}


	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getInclussao() {
		return inclussao;
	}

	public void setInclussao(Date inclussao) {
		this.inclussao = inclussao;
	}

	public String getHashImagem() {
		return hashImagem;
	}

	public void setHashImagem(String hashImagem) {
		this.hashImagem = hashImagem;
	}

	//Importante este construtor para devolver listas de objetos no CRUDREPOSITORY. Nao usar diretamente
	public Imagem(Long id, String descricao, Date inclussao) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.inclussao = inclussao;
	}

	public Imagem() {
		super();
	}
	
	
}
