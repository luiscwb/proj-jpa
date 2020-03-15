package com.br.stl.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.br.stl.model.entity.Caracteristica;
import com.br.stl.model.entity.Cliente;
import com.br.stl.model.entity.Consultora;
import com.br.stl.model.entity.Imagem;
import com.br.stl.service.StylebookService;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class StylebookTest {

	@Autowired
	StylebookService service;

	private static List<Cliente> 	listClientes 	= new ArrayList<>();
	private static List<Imagem>  	listImagems  	= new ArrayList<>();
	private static List<Consultora> listConsultoras = new ArrayList<>();

	
	@Test
	@Order(1)
	public void apagarBase() {
		
		service.apagarBase();
		boolean ok = service.getRepoCliente().count()==0 && service.getRepoImagem().count()==0;
		assertTrue(ok);
	}
	
	
	@Test
	@Order(2)
	public void inserirCaracteristicas() {

		int quantidade = 43
				;
		service.salvarCaracteristica( Caracteristica.createInstance(1L,"Força/Credibilidade"));
		service.salvarCaracteristica( Caracteristica.createInstance(2L,"Sofisticação"));
		service.salvarCaracteristica( Caracteristica.createInstance(3L,"Elegancia"));
		service.salvarCaracteristica( Caracteristica.createInstance(4L,"Confiança"));
		service.salvarCaracteristica( Caracteristica.createInstance(5L,"Leveza/Acessibilidade/Alegria"));
		service.salvarCaracteristica( Caracteristica.createInstance(6L,"Agilidade/Atitude"));
		service.salvarCaracteristica( Caracteristica.createInstance(7L,"Equilbrio/Realista"));
		service.salvarCaracteristica( Caracteristica.createInstance(8L,"Pequena"));
		service.salvarCaracteristica( Caracteristica.createInstance(9L,"Media"));
		service.salvarCaracteristica( Caracteristica.createInstance(10L,"Grande"));
		service.salvarCaracteristica( Caracteristica.createInstance(11L,"Mais tronco"));
		service.salvarCaracteristica( Caracteristica.createInstance(12L,"Mais perna"));
		service.salvarCaracteristica( Caracteristica.createInstance(13L,"Proporcional"));
		service.salvarCaracteristica( Caracteristica.createInstance(14L,"Baixo"));
		service.salvarCaracteristica( Caracteristica.createInstance(15L,"Medio"));
		service.salvarCaracteristica( Caracteristica.createInstance(16L,"Alto"));
		service.salvarCaracteristica( Caracteristica.createInstance(17L,"Neutra"));
		service.salvarCaracteristica( Caracteristica.createInstance(18L,"Fria"));
		service.salvarCaracteristica( Caracteristica.createInstance(19L,"Quente"));
		service.salvarCaracteristica( Caracteristica.createInstance(20L,"Clara"));
		service.salvarCaracteristica( Caracteristica.createInstance(21L,"Media"));
		service.salvarCaracteristica( Caracteristica.createInstance(22L,"Escura"));
		service.salvarCaracteristica( Caracteristica.createInstance(23L,"Suave"));
		service.salvarCaracteristica( Caracteristica.createInstance(24L,"Intensa"));
		service.salvarCaracteristica( Caracteristica.createInstance(25L,"verão suave"));
		service.salvarCaracteristica( Caracteristica.createInstance(26L,"Verão Frio"));
		service.salvarCaracteristica( Caracteristica.createInstance(27L,"Verão Claro"));
		service.salvarCaracteristica( Caracteristica.createInstance(28L,"Primavera Clara"));
		service.salvarCaracteristica( Caracteristica.createInstance(29L,"Primavera Quente"));
		service.salvarCaracteristica( Caracteristica.createInstance(30L,"Primavera Brilhante"));
		service.salvarCaracteristica( Caracteristica.createInstance(31L,"Inverno Brilhante"));
		service.salvarCaracteristica( Caracteristica.createInstance(32L,"Inverno Frio"));
		service.salvarCaracteristica( Caracteristica.createInstance(33L,"Inverno Escuro"));
		service.salvarCaracteristica( Caracteristica.createInstance(34L,"Outono Escuro"));
		service.salvarCaracteristica( Caracteristica.createInstance(35L,"Outono Quente"));
		service.salvarCaracteristica( Caracteristica.createInstance(36L,"Outono Suave"));
		service.salvarCaracteristica( Caracteristica.createInstance(37L,"Natural"));
		service.salvarCaracteristica( Caracteristica.createInstance(38L,"Tradicional/Classica"));
		service.salvarCaracteristica( Caracteristica.createInstance(39L,"Sofisticada/Minimalista"));
		service.salvarCaracteristica( Caracteristica.createInstance(40L,"Criativa"));
		service.salvarCaracteristica( Caracteristica.createInstance(41L,"Romantica"));
		service.salvarCaracteristica( Caracteristica.createInstance(42L,"Sensual"));
		service.salvarCaracteristica( Caracteristica.createInstance(43L,"Dramática"));
		
		assertEquals(quantidade, service.getRepoCaracteristica().count());
	}	
	
	@Test
	@Order(2)
	public void inserirConultoras() {
		
		int quantidade = 2;
		
		service.salvarConsultora(Consultora.createInstance("Tatiana Yagui", "tatiyag"));
		service.salvarConsultora(Consultora.createInstance("Angelina Perez", "angper"));
		
		assertEquals(quantidade, service.getRepoConsultora().count());
	}
		
	
	@Test
	@Order(3)
	public void inserirClientes() {

		int quantidade = 2;
		Cliente cli;
		
		cli = Cliente.createInstance("Luis Maini Fortes", "00933707339", "41996448025");
		cli.setEmail("luis.maini@gamil.com");
		service.salvarCliente(cli);
		
		cli = Cliente.createInstance("Afeu D Bem", "00933707340", "41996448030");
		cli.setEmail("alfeu.bem@gamil.com");
		service.salvarCliente(cli);

		
		assertEquals( quantidade, service.getRepoCliente().count() );
	}
	

	@Test
	@Order(4)
	public void inserirAssociarImagems() {
		
		String pathBase = "/home/luis/Downloads/imagens/";
		List<File> listArquivos;

		
		
		listArquivos = recuperarArquivos(pathBase + "1");
		listArquivos.forEach( (file) -> {
			Imagem img = service.salvarImagem(Imagem.createInstance("Força e Credibilidade", getBase64Imagem(file) , 1));
			service.salvarImagemDeCaracteristica(service.recuperarCaracteristicaPorId(1L), img);
			} 
		);
		
		listArquivos = recuperarArquivos(pathBase + "2");
		listArquivos.forEach( (file) -> {
			Imagem img = service.salvarImagem(Imagem.createInstance("Sofisticação", getBase64Imagem(file) , 2));
			service.salvarImagemDeCaracteristica(service.recuperarCaracteristicaPorId(2L), img);
		}
		
		);	
		listArquivos = recuperarArquivos(pathBase + "37");
		listArquivos.forEach( (file) -> {
			Imagem img = service.salvarImagem(Imagem.createInstance("Natural", getBase64Imagem(file) , 37));
			service.salvarImagemDeCaracteristica(service.recuperarCaracteristicaPorId(37L), img);
		}
		);
		
		listArquivos = recuperarArquivos(pathBase + "38");
		listArquivos.forEach( (file) -> {
			Imagem img = service.salvarImagem(Imagem.createInstance("Classico",getBase64Imagem(file) , 38));
			service.salvarImagemDeCaracteristica(service.recuperarCaracteristicaPorId(38L), img);
		}
		);
		
		listArquivos = recuperarArquivos(pathBase + "39");
		listArquivos.forEach( (file) -> {
			Imagem img = service.salvarImagem(Imagem.createInstance("Sofisticada", getBase64Imagem(file) , 39));
			service.salvarImagemDeCaracteristica(service.recuperarCaracteristicaPorId(39L), img);
		}
		);
		
		listArquivos = recuperarArquivos(pathBase + "3");
		listArquivos.forEach( (file) -> {
			Imagem img = service.salvarImagem(Imagem.createInstance("Elegância ", getBase64Imagem(file) , 3));
			service.salvarImagemDeCaracteristica(service.recuperarCaracteristicaPorId(3L), img);
		}
		);
		
		listArquivos = recuperarArquivos(pathBase + "40");
		listArquivos.forEach( (file) -> {
			Imagem img = service.salvarImagem(Imagem.createInstance("criativa", getBase64Imagem(file) , 40));
			service.salvarImagemDeCaracteristica(service.recuperarCaracteristicaPorId(40L), img);
		}
		);
				
		listArquivos = recuperarArquivos(pathBase + "41");		
		listArquivos.forEach( (file) -> {
			Imagem img = service.salvarImagem(Imagem.createInstance("romantica", getBase64Imagem(file) , 41));
			service.salvarImagemDeCaracteristica(service.recuperarCaracteristicaPorId(41L), img);
		}
		);
		
		listArquivos = recuperarArquivos(pathBase + "42");
		listArquivos.forEach( (file) -> {
			Imagem img = service.salvarImagem(Imagem.createInstance("Sensual", getBase64Imagem(file) , 42));
			service.salvarImagemDeCaracteristica(service.recuperarCaracteristicaPorId(42L), img);
		}
		);
		
		listArquivos = recuperarArquivos(pathBase + "43");				
		listArquivos.forEach( (file) -> {
			Imagem img = service.salvarImagem(Imagem.createInstance("Dramatica", getBase64Imagem(file) , 43));
			service.salvarImagemDeCaracteristica(service.recuperarCaracteristicaPorId(43L), img);
		}
		);
		
		listArquivos = recuperarArquivos(pathBase + "4");				
		listArquivos.forEach( (file) -> {
			Imagem img = service.salvarImagem(Imagem.createInstance("Confiança", getBase64Imagem(file) , 4));
			service.salvarImagemDeCaracteristica(service.recuperarCaracteristicaPorId(4L), img);
		}
		);
		
		listArquivos = recuperarArquivos(pathBase + "5");
		listArquivos.forEach( (file) -> {
			Imagem img = service.salvarImagem(Imagem.createInstance("Leveza", getBase64Imagem(file) , 5));
			service.salvarImagemDeCaracteristica(service.recuperarCaracteristicaPorId(5L), img);
		}
		);
		
		listArquivos = recuperarArquivos(pathBase + "6");
		listArquivos.forEach( (file) -> {
			Imagem img = service.salvarImagem(Imagem.createInstance("Agilidade", getBase64Imagem(file) , 6));
			service.salvarImagemDeCaracteristica(service.recuperarCaracteristicaPorId(6L), img);
		}
		);
		
		listArquivos = recuperarArquivos(pathBase + "7");
		listArquivos.forEach( (file) -> {
			Imagem img = service.salvarImagem(Imagem.createInstance("Equilibrio", getBase64Imagem(file) , 7));
			service.salvarImagemDeCaracteristica(service.recuperarCaracteristicaPorId(7L), img);
		}
		);
		
		
		assertTrue(true);
	}


//	
//	@Test
//	@Order(8)
//	public void recuperarImagemById() {
//		
//		final int imagenTest = 5;
//		
//		Long idImagem = listImagems.get(imagenTest-1).getId();
//		Optional<Imagem> foundedImage = service.getRepoImagem().findById( idImagem);
//		Boolean iguais = false;
//		
//		if ( foundedImage.isPresent() ) {
//			iguais = foundedImage.get().getBase64().equals(getBase64Imagem("aurora"+imagenTest+".jpeg"));
//		}
//		
//		assertTrue(iguais);
//		
//	}	
//	
//	@Test
//	@Order(9)
//	public void apagarCliente() {
//		Cliente cli = service.recuperarClientePorCpf("00933707339");
//		service.exluirCliente(cli);
//		
//		assertEquals( service.getRepoCliente().count(), listClientes.size()-1);
//	}	
	
	
	
	
	private List<File> recuperarArquivos(String pasta) {
		
		List<File> files = null;
		
		try (Stream<Path> walk = Files.walk(Paths.get(pasta))) {

			files = walk.filter(Files::isRegularFile).map(x -> x.toFile()).collect(Collectors.toList());
					
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
		return files;
	}
	
	
	private String getBase64Imagem(String nome) {

		File file = new File(nome);
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

	private String getBase64Imagem(File file) {

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
