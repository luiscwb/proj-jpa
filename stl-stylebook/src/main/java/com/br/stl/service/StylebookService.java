package com.br.stl.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.apache.tomcat.util.codec.binary.Base64;
import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.stl.model.entity.Cliente;
import com.br.stl.model.entity.ClienteImagem;
import com.br.stl.repository.ClienteRepository;
import com.br.stl.repository.ImagemRepository;

@Service
public class StylebookService {

	@Autowired
	ClienteRepository repoCliente;

	@Autowired
	ImagemRepository repoImagem;

	public List<Cliente> recuperarClientes() {
		Iterable<Cliente> itc = repoCliente.findAll();
		return StreamSupport.stream(itc.spliterator(), false).collect(Collectors.toList());
	}

	public void salvarImagem(ClienteImagem imagem) {

		Blob b = BlobProxy.generateProxy(Base64.decodeBase64(imagem.getImagemBase64()));
		imagem.setImagem(b);
	}

	// https://thoughts-on-java.org/mapping-blobs-and-clobs-with-hibernate-and-jpa/
	// https://medium.com/@nitishk72/flutter-uploading-image-to-server-aec76876b9e1
	public ClienteImagem recuperarImagem(Long idImagem) {

		Optional<ClienteImagem> optImagem = repoImagem.findById(idImagem);
		if (optImagem.isPresent()) {
			try {
				InputStream imagemInputStream = optImagem.get().getImagem().getBinaryStream();

				ByteArrayOutputStream buffer = new ByteArrayOutputStream();
				int nRead;
				byte[] data = new byte[1024];

				while ((nRead = imagemInputStream.read(data, 0, data.length)) != -1) {
					buffer.write(data, 0, nRead);
				}

				buffer.flush();
				byte[] byteArray = buffer.toByteArray();
				
				optImagem.get().setImagemBase64(Base64.encodeBase64URLSafeString(byteArray));
			} 
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return null;
	}

	public void salvarCliente(Cliente cli) {
		repoCliente.save(cli);
	}

}
