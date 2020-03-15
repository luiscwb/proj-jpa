package com.br.stl.model.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Consultora {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private String nomeCompleto;
	
	@Column(nullable = false)
	private String ususario;
	
	@OneToMany(orphanRemoval = true, fetch = FetchType.LAZY)
	private Set<Cliente> clientes;
	
	
	
	public static Consultora createInstance(String nomeCompleto, String usuario) {
		Consultora cons = new Consultora();
		cons.setNomeCompleto(nomeCompleto);
		cons.setUsusario(usuario);
		
		return cons;
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


	public String getUsusario() {
		return ususario;
	}


	public void setUsusario(String ususario) {
		this.ususario = ususario;
	}


	public Set<Cliente> getClientes() {
		return clientes;
	}


	public void setClientes(Set<Cliente> clientes) {
		this.clientes = clientes;
	}
	
	
}
