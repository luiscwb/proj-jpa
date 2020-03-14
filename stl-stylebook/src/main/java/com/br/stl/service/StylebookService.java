package com.br.stl.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.stl.model.entity.Cliente;
import com.br.stl.model.entity.Imagen;
import com.br.stl.repository.ClienteRepository;
import com.br.stl.repository.ImagenRepository;

@Service
public class StylebookService {

	@Autowired
	ClienteRepository repoCliente;

	@Autowired
	ImagenRepository repoImagen;
	
	//Testes e validacoes
	public ClienteRepository getRepoCliente() {
		return repoCliente;
	}
	
	//Testes e validacoes
	public ImagenRepository getRepoImagen() {
		return repoImagen;
	}
	
	//Testes e validacoes
	@Transactional
	public void apagarBase() {
		repoCliente.delAllRelationImage();
		repoImagen.deleteAll();
		repoCliente.deleteAll();
	}
	
	
	
 	public void salvarCliente(Cliente cli) {
		repoCliente.save(cli);
	}
	
 	public void salvarImagen(Imagen img) {
		repoImagen.save(img);
	}
	
 	@Transactional
	public void salvarImagenParaCliente(Cliente cli, Imagen img) {
		repoCliente.addImageForClient( cli.getId(), img.getId());
	}
	
 	@Transactional
	public void exlcuirImagenParaCliente(Cliente cli, Imagen img) {
		repoCliente.delImageForClient( cli.getId(), img.getId() );
		repoImagen.deleteById( img.getId() );
	}
 	
 	public void exluirCliente(Cliente cli) {
 		repoCliente.deleteById( cli.getId() );
 	}
	
	public Cliente recuperarClientePorCpf( String cpf) {
		return repoCliente.findByCpf(cpf);
	}
	
}