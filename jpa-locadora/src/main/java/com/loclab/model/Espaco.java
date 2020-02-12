package com.loclab.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Espaco {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String endereco;

	@Column(nullable = false)
	private Integer tipo;

	// Relacao bidirecional com Espaco
	@OneToMany(mappedBy = "espaco")
	private Set<Locacao> locacoes;

	public static Espaco cretateInstance(String endereco, Integer tipo) {
		Espaco espaco = new Espaco();
		espaco.setEndereco(endereco);
		espaco.setTipo(tipo);
		return espaco;
	}

	public Set<Locacao> getLocacoes() {
		return locacoes;
	}

	public void setLocacoes(Set<Locacao> locacoes) {
		this.locacoes = locacoes;
	}

	public Long getEspacoId() {
		return id;
	}

	public void setEspacoId(Long espacoId) {
		this.id = espacoId;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public Long getId() {
		return this.id;
	}

}
