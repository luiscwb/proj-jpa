package com.br.stl.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.stl.model.entity.Cliente;
import com.br.stl.model.entity.Imagem;
import com.br.stl.repository.ClienteRepository;
import com.br.stl.repository.ImagemRepository;

@Service
public class StylebookService {

	@Autowired
	ClienteRepository repoCliente;

	@Autowired
	ImagemRepository repoImagem;
	
	public ClienteRepository getRepoCliente() {
		return repoCliente;
	}
	
	public ImagemRepository getRepoImagem() {
		return repoImagem;
	}
	
	
	@Transactional
	public void apagarBase() {
		repoCliente.delAllRelationImage();
		repoImagem.deleteAll();
		repoCliente.deleteAll();
	}
	
 	public void salvarCliente(Cliente cli) {
		repoCliente.save(cli);
	}
	
 	public void salvarImagem(Imagem img) {
		repoImagem.save(img);
	}
	
 	@Transactional
	public void salvarImagemParaCliente(Cliente cli, Imagem img) {
		repoCliente.addRelationImage( cli.getId(), img.getId());
	}
	
 	@Transactional
	public void exlcuirImagemParaCliente(Cliente cli, Imagem img) {
		repoCliente.delRelationImage( cli.getId(), img.getId() );
	}
 	
 	public void exluirCliente(Cliente cli) {
 		repoCliente.deleteById( cli.getId() );
 	}
	
	public Cliente recuperarClientePorCpf( String cpf) {
		return repoCliente.findByCpf(cpf);
	}
	
}