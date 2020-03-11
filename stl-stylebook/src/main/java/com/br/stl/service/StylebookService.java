package com.br.stl.service;

import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.imageio.ImageIO;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.stl.model.entity.Cliente;
import com.br.stl.repository.ClienteRepository;

@Service
public class StylebookService {

	@Autowired
	ClienteRepository repoCliente;

	public List<Cliente> recuperarClientes() {
		Iterable<Cliente> itc = repoCliente.findAll();
		return StreamSupport.stream(itc.spliterator(), false).collect(Collectors.toList());
	}

	public void salvarCliente(Cliente cli) {
		cli.setImagem(Base64.decodeBase64(cli.getImagemBase64()));
		repoCliente.save(cli);
	}

	//https://thoughts-on-java.org/mapping-blobs-and-clobs-with-hibernate-and-jpa/
	public Image recuperarImagem(Long idCliente) {

		Optional<Cliente> optCliente = repoCliente.findById(idCliente);
		InputStream is;
		
		if (optCliente.isPresent()) {
			   is = new ByteArrayInputStream(optCliente.get().getImagem());
			   Image imag=ImageIO.read(is);
		}
		return null;
		
	}

}
