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
public class Caracteristica {
	
	@Id
	private Long id;
	
	@Column(nullable = false)
	private String descricao;
	
	@OneToMany(orphanRemoval = true, fetch = FetchType.LAZY)
	private Set<Imagem> imagens;

	
	public static Caracteristica createInstance(Long id, String descricao) {
		Caracteristica car = new Caracteristica();
		car.setId(id);
		car.setDescricao(descricao);
		
		return car;
	}
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
