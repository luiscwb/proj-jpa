package com.br.stl.model.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private String nomeCompleto;
	
	@Column(nullable = false, unique = true)
	private String cpf;
	
	@Column(nullable = false)
	private String telefone;	
	
	@Column
	private String endereco;


	@Temporal(TemporalType.DATE)
	private Date dataNascimento;

	
	@Temporal(TemporalType.DATE)
	private Date dataCadastro;
	
	@Column
	private String email;
	
	@Column
	private int estadoCivil;
	
	@Column
	private boolean filhos;
	
	@Column
	private String profissao;
	
	
	@JsonIgnore
	@OneToOne(orphanRemoval = true, fetch = FetchType.LAZY)
	private Imagem fotografia;
	
	
	@JsonIgnore
	@OneToMany(orphanRemoval = true, fetch = FetchType.LAZY)
	private Set<Imagem> dossies;
	
	
	//Campos obrigaotiros para cirar um cliente
	public static Cliente createInstance(String nome, String cpf, String telefone) {
		Cliente c = new Cliente();
		c.setCpf(cpf);
		c.setNomeCompleto(nome);
		c.setTelefone(telefone);
		c.setDataCadastro(new Date());
		return c;
	}

	
	public Date getDataCadastro() {
		return dataCadastro;
	}


	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public int getEstadoCivil() {
		return estadoCivil;
	}


	public void setEstadoCivil(int estadoCivil) {
		this.estadoCivil = estadoCivil;
	}


	public boolean isFilhos() {
		return filhos;
	}


	public void setFilhos(boolean filhos) {
		this.filhos = filhos;
	}


	public String getProfissao() {
		return profissao;
	}


	public void setProfissao(String profissao) {
		this.profissao = profissao;
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

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}


	public Set<Imagem> getDossies() {
		return dossies;
	}


	public void setDossies(Set<Imagem> dossies) {
		this.dossies = dossies;
	}



		
}
