package com.br.stl.test;

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

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class StylebookTest {

	@Autowired
	StylebookService service;

	private static List<Cliente> listClientes = new ArrayList<Cliente>();

	
	@Test
	@Order(1)
	public void apagarBase() {
		service.apagarBase();
	}
	
	
	@Test
	@Order(2)
	public void inserirClientes() {

		listClientes.add( Cliente.createInstance("Luis Maini Fortes", "00933707339", "41996448025"));
		listClientes.add(Cliente.createInstance("Afeo D Bem", "00933707340", "41996448030"));
		listClientes.add(Cliente.createInstance("Fernando William", "00933707341", "41996448035"));
		
		listClientes.forEach( (cli) -> service.salvarCliente(cli) );
	}

	@Test
	@Order(3)
	public void inseriImagens() {

		String imbBase64 = getBase64Imagem();
		Imagem img = new Imagem();
		img.setImagem(imbBase64);

		service.salvarImagemDoCliente(listClientes.get(0), img);

	}

	private String getBase64Imagem() {

		File file = new File("/temp/sap.png");
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
