package com.br.stl.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.stl.model.entity.Cliente;
import com.br.stl.model.entity.ClienteImagem;
import com.br.stl.model.entity.Imagem;
import com.br.stl.repository.ClienteImagemRepository;
import com.br.stl.repository.ClienteRepository;
import com.br.stl.repository.ImagemRepository;

@Service
public class StylebookService {

	@Autowired
	ClienteRepository repoCliente;

	@Autowired
	ImagemRepository repoImagem;
	
	@Autowired
	ClienteImagemRepository repoImagemCliente;

	
	public void apagarBase() {
		repoImagem.deleteAll();
		repoImagemCliente.deleteAll();
		repoCliente.deleteAll();
	}
	
 	public void salvarCliente(Cliente cli) {
		repoCliente.save(cli);
	}
	
	
	@Transactional
	public void salvarImagemDoCliente(Cliente cli, Imagem img) {
		ClienteImagem cliImg = ClienteImagem.createInstance(cli, img, "nome.jpg");
		img.setClienteImagem(cliImg);
		repoImagemCliente.save(cliImg);
		repoImagem.save(img);
	}
	

	public List<ClienteImagem> recuperarClienteImagens(Long idCliente) {
		
		return null;
	}
	
	public Imagem recuperarImagem(Long idImagem) {
		return null;
	}
	
	
	
	public List<Cliente> recuperarClientes() {
		Iterable<Cliente> itc = repoCliente.findAll();
		return StreamSupport.stream(itc.spliterator(), false).collect(Collectors.toList());
	}

	public void salvarImagem(ClienteImagem imagem) {
		imagem.setInclussao(new Date());
		//imagem.setHashImagem(Uteis.getMD5Hash(imagem.getImagem()));
		//repoImagem.save(imagem);
	}

	// https://thoughts-on-java.org/mapping-blobs-and-clobs-with-hibernate-and-jpa/
	// https://medium.com/@nitishk72/flutter-uploading-image-to-server-aec76876b9e1


}
