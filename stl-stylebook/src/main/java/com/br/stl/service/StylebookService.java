package com.br.stl.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.stl.model.entity.Caracteristica;
import com.br.stl.model.entity.Cliente;
import com.br.stl.model.entity.Consultora;
import com.br.stl.model.entity.Dossie;
import com.br.stl.model.entity.Imagem;
import com.br.stl.repository.CaracteristicaRepository;
import com.br.stl.repository.ClienteRepository;
import com.br.stl.repository.ConsultoraRepository;
import com.br.stl.repository.DossieRepository;
import com.br.stl.repository.ImagemRepository;

@Service
public class StylebookService {

	@Autowired
	ClienteRepository repoCliente;

	@Autowired
	ImagemRepository repoImagem;
	
	@Autowired
	ConsultoraRepository repoConsultora;

	@Autowired
	DossieRepository repoDossie;
	
	@Autowired
	CaracteristicaRepository repoCaracteristica;
	
	
	//Testes e validacoes
	public CaracteristicaRepository getRepoCaracteristica() {
		return repoCaracteristica;
	}

	//Testes e validacoes
	public ConsultoraRepository getRepoConsultora() {
		return repoConsultora;
	}
	
	//Testes e validacoes
	public DossieRepository getRepoDossie() {
		return repoDossie;
	}

	//Testes e validacoes
	public ClienteRepository getRepoCliente() {
		return repoCliente;
	}
	
	//Testes e validacoes
	public ImagemRepository getRepoImagem() {
		return repoImagem;
	}
	
	//Testes e validacoes
	@Transactional
	public void apagarBase() {
		repoCaracteristica.deleteAll();
		repoImagem.deleteAll();
		repoCliente.deleteAll();
		repoConsultora.deleteAll();
	}
	
	//------------------------------------------------------------------------------------------------	
	// CARACTERISTICA
	
	public void salvarCaracteristica(Caracteristica car) {
		repoCaracteristica.save(car);
	}
	
 	@Transactional
	public void salvarImagemDeCaracteristica(Caracteristica car, Imagem img) {
 		repoCaracteristica.addImageForCaracteristica( car.getId(), img.getId());
	}
 	
	public List<Caracteristica> recuperarCaracteristicas() {
		Iterable<Caracteristica> iterCar = repoCaracteristica.findAll();
		return StreamSupport.stream(iterCar.spliterator(), false).collect(Collectors.toList());
	}
	
	public Caracteristica recuperarCaracteristicaPorId(Long id) {
		Optional<Caracteristica> optCar = repoCaracteristica.findById(id);
		if ( optCar.isPresent() )
			return optCar.get();
		else
			return null;
	}
	
	//------------------------------------------------------------------------------------------------
	// DOSSIE
	
	public void salvarDossie(Dossie dos) {
		repoDossie.save(dos);
	}
	
 	@Transactional
	public void salvarImagemDeDossie(Dossie dos, Imagem img) {
 		repoDossie.addImageForDossie( dos.getId(), img.getId());
	}
 	
 	@Transactional
	public void exlcuirImagemDeDossie(Dossie dos, Imagem img) {
 		repoDossie.delImageForDossie( dos.getId(), img.getId() );
		repoImagem.deleteById( img.getId() );
	}
 	
 	public List<Imagem> recuperarImagensPorDeDossieId( Long id) {
 		return repoDossie.findImagesByDossieId(id);
 	}
 	
	//------------------------------------------------------------------------------------------------
	// CONSULTORA
 	
	public void salvarConsultora(Consultora cons) {
		repoConsultora.save(cons);
	}

	public List<Cliente> recuperarClientesPorConsultoraId(Long id) {
		return repoConsultora.findClientesByConsultoraId(id);
 	}
	
	
	//------------------------------------------------------------------------------------------------
	// CLIENTE
	
	public Cliente recuperarClientePorCpf( String cpf) {
		return repoCliente.findByCpf(cpf);
	}
	
 	public void salvarCliente(Cliente cli) {
		repoCliente.save(cli);
	}
	
 	public void exluirClientePorId(Long id) {
 		repoCliente.deleteById(id);
 	}
	
 	public void exluirClientePorCpf(String cpf) {
 		repoCliente.deleteByCpf(cpf);
 	}
 	
 	
	//------------------------------------------------------------------------------------------------
 	// IMAGEM
 	
 	public Imagem salvarImagem(Imagem img) {
		return repoImagem.save(img);
	}
 	
 	public void excluirImagemPorId(Long id) {
 		repoImagem.deleteById(id);
 	}
 	
	
}