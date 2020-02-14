package com.loclab.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = false, unique = true)
	private String cpf;
	
	//Relacionamento bidrecional com Locacao
	@OneToMany(mappedBy = "cliente", fetch = FetchType.EAGER)
	private Set<Locacao> locacoes;


	
	
	public Long getId() {
		return id;
	}



	public Long getClienteId() {
		return id;
	}

	
	
	public static Cliente createInstance(String nome, String cpf) {
		Cliente cliente = new Cliente();		
		cliente.nome = nome;
		cliente.cpf = cpf;
		
		return cliente;
	}


	public void setClienteId(Long clienteId) {
		this.id = clienteId;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getCpf() {
		return cpf;
	}


	public void setCpf(String cpf) {
		this.cpf = cpf;
	}


	public Set<Locacao> getLocacoes() {
		return locacoes;
	}


	public void setLocacoes(Set<Locacao> locacoes) {
		this.locacoes = locacoes;
	}


}
