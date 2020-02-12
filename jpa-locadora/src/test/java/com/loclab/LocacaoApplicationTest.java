package com.loclab;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import com.loclab.model.Cliente;
import com.loclab.model.Espaco;
import com.loclab.model.Locacao;
import com.loclab.persist.ClienteRepository;
import com.loclab.persist.EspacoRepository;
import com.loclab.persist.LocacaoRepository;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class LocacaoApplicationTest {

	@Autowired
	ClienteRepository clienteRepository;

	@Autowired
	EspacoRepository espacoRepository;

	@Autowired
	LocacaoRepository locacaoRepository;

	@Autowired
	LocacaoService locacaoService;

	
	private static ArrayList<Cliente> clientes = new ArrayList<>();
	private static ArrayList<Espaco> locais = new ArrayList<>();
	private static ArrayList<Locacao> locacoes = new ArrayList<>();

	@Test
	@Order(1)
	public void inicializar() {

		locacaoRepository.deleteAll();
		clienteRepository.deleteAll();
		espacoRepository.deleteAll();

		clientes.add(Cliente.createInstance("Luis Maini 1", "00933707339"));
		clientes.add(Cliente.createInstance("Danieal Diaz", "00933707340"));
		clientes.add(Cliente.createInstance("Pedro Almodovar", "00933707341"));
		clientes.add(Cliente.createInstance("Oliver Bierhoff", "00933707342"));
		clientes.forEach(cli -> clienteRepository.save(cli));

		locais.add(Espaco.cretateInstance("Av. Presidente Kennedy 3080", 1));
		locais.add(Espaco.cretateInstance("Rua Maranhao 1234", 1));
		locais.add(Espaco.cretateInstance("Av. Republica Argentina 2751", 1));
		locais.add(Espaco.cretateInstance("Cardal 1492", 1));
		locais.forEach(loc -> espacoRepository.save(loc));

	}

	@Test
	@Order(2)
	public void saveReservas() throws Exception {
		
		locacoes.add(Locacao.createInstance(clientes.get(0), locais.get(0), gerarDataRandom(), gerarDataRandom()));
		locacoes.add(Locacao.createInstance(clientes.get(0), locais.get(1), gerarDataRandom(), gerarDataRandom()));
		locacoes.add(Locacao.createInstance(clientes.get(0), locais.get(2), gerarDataRandom(), gerarDataRandom()));
		locacoes.add(Locacao.createInstance(clientes.get(1), locais.get(0), gerarDataRandom(), gerarDataRandom()));
		locacoes.add(Locacao.createInstance(clientes.get(1), locais.get(1), gerarDataRandom(), gerarDataRandom()));
		locacoes.add(Locacao.createInstance(clientes.get(1), locais.get(2), gerarDataRandom(), gerarDataRandom()));
		locacoes.add(Locacao.createInstance(clientes.get(1), locais.get(3), gerarDataRandom(), gerarDataRandom()));
		locacoes.forEach( l -> locacaoRepository.save(l));
		
		assertEquals(locacaoRepository.count(), 7);
	}
	
	@Test
	@Order(3)
	public void saveReservaDuplicada() throws Exception {

		//Vilocaçoao de chave primaria ( cliente, local)
		assertThrows(DataIntegrityViolationException.class, () -> {
			locacaoRepository.save(Locacao.createInstance(clientes.get(0), locais.get(1), gerarDataRandom(), gerarDataRandom()));			
		});
	}

	@Test
	@Order(4)
	public void checkAvailability1() throws Exception {

		int idxlocal = 3;
		locacaoRepository.save(Locacao.createInstance(clientes.get(2), locais.get(idxlocal), gerarData("05/02/2020"), gerarData("25/02/2020")));
		
		List<Locacao> loc = locacaoRepository.checkAvailability(locais.get(idxlocal).getId(), gerarData("01/01/2020"), gerarData("15/02/2020"));
		
		assertEquals(loc.size(), 1);
	}

	@Test
	@Order(5)
	public void checkAvailability2() throws Exception {

		int idxlocal = 3;
		List<Locacao> loc = locacaoRepository.checkAvailability(locais.get(idxlocal).getId(), gerarData("01/01/2020"), gerarData("04/02/2020"));
		
		assertEquals(loc.size(), 0);
	}

	@Test
	@Order(6)
	public void checkAvailability3() throws Exception {

		int idxlocal = 3;
		List<Locacao> loc = locacaoRepository.checkAvailability(locais.get(idxlocal).getId(), gerarData("01/01/2020"), gerarData("04/03/2020"));
		
		assertEquals(loc.size(), 1);
	}

	@Test
	@Order(7)
	public void realizarReservaComRepeticaco() throws Exception {
		
		
		assertThrows(DataIntegrityViolationException.class, () -> {
			locacaoService.realizarReserva(
					locais.get(0),
					clientes.get(0), 
					 gerarData("01/01/2002"), 
					 gerarData("10/01/2002"));		});
	}

	@Test
	@Order(8)
	public void cancelarReserva() throws Exception {
		locacaoService.cancelarReserva(locacoes.get(3));
		assertEquals(locacaoRepository.existsById(locacoes.get(3).getId()), false);
	}

	@Test
	@Order(9)
	public void alterarDatasReserva() throws Exception {

		//Locacao locacao = locacaoRepository.findById(22L).get();
		Locacao locacao = locacoes.get(2);
		Locacao alterarDatasReserva = locacaoService.alterarDatasReserva(locacao, gerarData("01/02/2020"), gerarData("09/03/2020"));

		assertNotNull(alterarDatasReserva);
	}

	private Date gerarDataRandom() {

		Random rand = new Random(); // instance of random class
		Calendar myCalendar = new GregorianCalendar(2000+rand.nextInt(20), 1+rand.nextInt(12), 1+rand.nextInt(30));
		return myCalendar.getTime();

	}

	private Date gerarData(String strdata) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			return sdf.parse(strdata);
		} 
		catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Date();
		}
	}
}
