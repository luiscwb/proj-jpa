package com.loclab.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class TipoLocal {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false, length = 50)
	private String descricao;
	
	@OneToMany(mappedBy = "tipoLocal", fetch = FetchType.LAZY)
	private Set<Local> locais;
	
	public static TipoLocal createInstance(String descricao) {
		TipoLocal tipo = new TipoLocal();
		tipo.descricao = descricao;
		return tipo;
	}
	
	
}
