package com.loclab.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Local {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String endereco;

	@ManyToOne
	@JoinColumn(name = "tipo_id")
	private TipoLocal tipoLocal;

	@Column(nullable = false)
	private Double valorUnitario;
	
	// Relacao bidirecional com Local
	@OneToMany(mappedBy = "local")
	private Set<Locacao> locacoes;

	
	public static Local cretateInstance(String endereco, TipoLocal tipo, double valorUnitario) {
		Local local = new Local();
		local.setEndereco(endereco);
		local.setTipoLocal(tipo);
		local.valorUnitario = valorUnitario;
		return local;
	}

	public Long getId() {
		return id;
	}


	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}


	public TipoLocal getTipoLocal() {
		return tipoLocal;
	}

	public void setTipoLocal(TipoLocal tipo) {
		this.tipoLocal = tipo;
	}

	public Double getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(Double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}


	public Set<Locacao> getLocacoes() {
		return locacoes;
	}

	public void setLocacoes(Set<Locacao> locacoes) {
		this.locacoes = locacoes;
	}


}
