package com.br.stl.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

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
		
		boolean ok = service.getRepoCliente().count()==0 && service.getRepoImagem().count()==0 && service.getRepoCliente().countImages() == 0;
		assertTrue(ok);
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
			imgBase64 = getBase64Imagem("aurora"+i+".jpeg");
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
		boolean ok = service.getRepoCliente().findAllImagesByClientId(cli.getId()).size() == 3;	
		
		cli = service.recuperarClientePorCpf("00933707340");
		service.salvarImagemParaCliente(cli, listImagens.get(1));
		service.salvarImagemParaCliente(cli, listImagens.get(3));
		ok = ok && service.getRepoCliente().findAllImagesByClientId(cli.getId()).size() == 2;
		
		assertTrue(ok);
	}	
	
	
	@Test
	@Order(5)
	public void DEAssociarImagen() {
		Cliente cli = service.recuperarClientePorCpf("00933707339");
		service.exlcuirImagemParaCliente(cli, listImagens.get(0));
		
	}	
	
	
	@Test
	@Order(7)
	public void recuperarImagensCliente() {
		
		Cliente cli = service.recuperarClientePorCpf("00933707340");
		List<Imagem> imagens = service.getRepoCliente().findAllImagesByClientId(cli.getId());
		imagens.forEach( (img) -> System.out.println( img.getDescricao() ) );
		
		assertEquals(2, imagens.size());
		
	}
	
	
	@Test
	@Order(8)
	public void apagarCliente() {
		Cliente cli = service.recuperarClientePorCpf("00933707339");
		service.exluirCliente(cli);
		
		assertEquals( service.getRepoCliente().count(), listClientes.size()-1);
	}	
	
	
	
	
	
	
	
	private String getBase64Imagem(String nome) {

		File file = new File("/home/luis/Downloads/" + nome);
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
