package com.br.stl.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.br.stl.model.entity.Cliente;
import com.br.stl.model.entity.Imagem;
import com.br.stl.service.StylebookService;
import com.br.stl.util.Constantes;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class StylebookTest {

	@Autowired
	StylebookService service;

	private static List<Cliente> listClientes = new ArrayList<Cliente>();
	private static List<Imagem>  listImagens  = new ArrayList<Imagem>();

	
	@Test
	@Order(1)
	public void apagarBase() {
		service.apagarBase();
	}
	
	
	@Test
	@Order(2)
	public void inserirClientes() {

		service.salvarCliente( Cliente.createInstance("Luis Maini Fortes", "00933707339", "41996448025"));
		service.salvarCliente(Cliente.createInstance("Afeo D Bem", "00933707340", "41996448030"));
		service.salvarCliente(Cliente.createInstance("Fernando William", "00933707341", "41996448035"));
		
		service.getRepoCliente().findAll().forEach( (cli) -> listClientes.add(cli) );
		
		assertEquals( service.getRepoCliente().count(), listClientes.size() );
	}

	@Test
	@Order(3)
	public void inserirImagens() {

		String imgBase64;
		Imagem img;
		
		for (int i=1; i<6; i++) {
			imgBase64 = getBase64Imagem("aurora"+i+".jpg");
			img = Imagem.createInstance("Imagem do Aurora Boeral " + i, imgBase64, Constantes.TIPO_IMAGEM_USUARIO);
			service.salvarImagem(img);
		}
		
		
		service.getRepoImagem().findAll().forEach( (imagem) -> listImagens.add(imagem));
		
		assertEquals( listImagens.size(), service.getRepoImagem().count());
	}
	
	@Test
	@Order(4)
	public void associarImagen() {
		Cliente cli = service.recuperarClientePorCpf("00933707339");
		service.salvarImagemParaCliente(cli, listImagens.get(0));
		service.salvarImagemParaCliente(cli, listImagens.get(2));
		service.salvarImagemParaCliente(cli, listImagens.get(4));
			
		
		cli = service.recuperarClientePorCpf("00933707340");
		service.salvarImagemParaCliente(cli, listImagens.get(1));
		service.salvarImagemParaCliente(cli, listImagens.get(3));
		
		assertEquals( service.getRepoCliente().countImages(), listImagens.size());
	}	
	
	@Test
	@Order(5)
	public void desAssociarImagen() {
		Cliente cli = service.recuperarClientePorCpf("00933707339");
		service.exlcuirImagemParaCliente(cli, listImagens.get(0));
		
	}	
	
	
	@Test
	@Order(7)
	public void recuperarImagensCliente() {
		
		Cliente cli = service.recuperarClientePorCpf("00933707340");
		Object[][] imagens = service.getRepoCliente().findAllImagesByClientId(cli.getId());
		
		assertEquals(2, 2);
		
	}
	
	
	@Test
	@Order(7)
	public void apagarCliente() {
		Cliente cli = service.recuperarClientePorCpf("00933707339");
		service.exluirCliente(cli);
	}	
	
	
	
	
	
	private String getBase64Imagem(String nome) {

		File file = new File("/temp/" + nome);
		byte[] picInBytes = new byte[(int) file.length()];

		FileInputStream fileInputStream;
		try {
			fileInputStream = new FileInputStream(file);
			fileInputStream.read(picInBytes);
			fileInputStream.close();

			return Base64.getEncoder().encodeToString(picInBytes);

		} 
		catch (Exception ex) {
			return null;
		}
	}

}
