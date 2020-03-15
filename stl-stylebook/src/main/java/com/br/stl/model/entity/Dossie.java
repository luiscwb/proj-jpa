package com.br.stl.model.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Dossie {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column
	private Long idCliente;
	
	@Column
	private String imagemEspelhada;
	
	@Column
	private String objetivo;
	
	@Column
	private String rotina;
	
	@JsonIgnore
	@OneToMany(orphanRemoval = true, fetch = FetchType.LAZY)
	private Set<Imagem> imagens;


	@ManyToMany
	private Set<Caracteristica> desejos;
	
	@ManyToOne
	private Caracteristica escala;
	
	@ManyToOne
	private Caracteristica proporcao;
	
	@ManyToOne
	private Caracteristica contraste;
	
	@ManyToOne
	private Caracteristica subtom;
	
	@ManyToOne
	private Caracteristica profundidade;
	
	@ManyToOne
	private Caracteristica intensidade;
	
	@ManyToOne
	private Caracteristica cartelaCores;
	
	@ManyToMany
	private Set<Caracteristica> estilos;
	
	@Column
	private String coresPrediletas;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public String getImagemEspelhada() {
		return imagemEspelhada;
	}

	public void setImagemEspelhada(String imagemEspelhada) {
		this.imagemEspelhada = imagemEspelhada;
	}

	public String getObjetivo() {
		return objetivo;
	}

	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}

	public String getRotina() {
		return rotina;
	}

	public void setRotina(String rotina) {
		this.rotina = rotina;
	}

	public Set<Caracteristica> getDesejos() {
		return desejos;
	}

	public void setDesejos(Set<Caracteristica> desejos) {
		this.desejos = desejos;
	}

	public Caracteristica getEscala() {
		return escala;
	}

	public void setEscala(Caracteristica escala) {
		this.escala = escala;
	}

	public Caracteristica getProporcao() {
		return proporcao;
	}

	public void setProporcao(Caracteristica proporcao) {
		this.proporcao = proporcao;
	}

	public Caracteristica getContraste() {
		return contraste;
	}

	public void setContraste(Caracteristica contraste) {
		this.contraste = contraste;
	}

	public Caracteristica getSubtom() {
		return subtom;
	}

	public void setSubtom(Caracteristica subtom) {
		this.subtom = subtom;
	}

	public Caracteristica getProfundidade() {
		return profundidade;
	}

	public void setProfundidade(Caracteristica profundidade) {
		this.profundidade = profundidade;
	}

	public Caracteristica getIntensidade() {
		return intensidade;
	}

	public void setIntensidade(Caracteristica intensidade) {
		this.intensidade = intensidade;
	}

	public Caracteristica getCartelaCores() {
		return cartelaCores;
	}

	public void setCartelaCores(Caracteristica cartelaCores) {
		this.cartelaCores = cartelaCores;
	}

	public Set<Caracteristica> getEstilos() {
		return estilos;
	}

	public void setEstilos(Set<Caracteristica> estilos) {
		this.estilos = estilos;
	}

	public String getCoresPrediletas() {
		return coresPrediletas;
	}

	public void setCoresPrediletas(String coresPrediletas) {
		this.coresPrediletas = coresPrediletas;
	}
	
	public Set<Imagem> getImagens() {
		return imagens;
	}

	public void setImagens(Set<Imagem> imagens) {
		this.imagens = imagens;
	}
}
	