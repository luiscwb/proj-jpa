package com.loclab;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

import com.loclab.entity.Cliente;
import com.loclab.entity.Locacao;
import com.loclab.entity.Local;
import com.loclab.entity.TipoLocal;
import com.loclab.persist.ClienteRepository;
import com.loclab.persist.LocacaoRepository;
import com.loclab.persist.LocalRepository;
import com.loclab.persist.TipoLocalRepository;
import com.loclab.service.LocacaoService;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class LocacaoApplicationTest {

	@Autowired
	ClienteRepository clienteRepository;

	@Autowired
	LocalRepository localRepository;

	@Autowired
	LocacaoRepository locacaoRepository;

	@Autowired
	TipoLocalRepository tipoLocalRepository;

	@Autowired
	LocacaoService locacaoService;

	
	private static ArrayList<Cliente> clientes = new ArrayList<>();
	private static ArrayList<Local> locais = new ArrayList<>();
	private static ArrayList<Locacao> locacoes = new ArrayList<>();
	private static ArrayList<TipoLocal> tipoLocais = new ArrayList<>();
	
	@Test
	@Order(1)
	public void inicializar() {

		locacaoRepository.deleteAll();
		clienteRepository.deleteAll();
		localRepository.deleteAll();
		tipoLocalRepository.deleteAll();

		clientes.add(Cliente.createInstance("Luis Maini 1",    "00933707339"));
		clientes.add(Cliente.createInstance("Danieal Diaz",    "00933707340"));
		clientes.add(Cliente.createInstance("Pedro Almodovar", "00933707341"));
		clientes.add(Cliente.createInstance("Oliver Bierhoff", "00933707342"));
		clientes.forEach(cli -> clienteRepository.save(cli));

		tipoLocais.add(TipoLocal.createInstance("Tipo 1"));
		tipoLocais.add(TipoLocal.createInstance("Tipo 2"));
		tipoLocais.add(TipoLocal.createInstance("Tipo 3"));
		tipoLocais.add(TipoLocal.createInstance("Tipo 4"));
		tipoLocais.forEach( t -> tipoLocalRepository.save(t));
		
		
		locais.add(Local.cretateInstance("Av. Presidente Kennedy 3080", tipoLocais.get(0), 230.50));
		locais.add(Local.cretateInstance("Rua Maranhao 1234", tipoLocais.get(1), 500));
		locais.add(Local.cretateInstance("Av. Republica Argentina 2751", tipoLocais.get(0), 480.30 ));
		locais.add(Local.cretateInstance("Cardal 1492", tipoLocais.get(2), 300));
		locais.forEach(loc -> localRepository.save(loc));
		
		assertTrue(true);

	}

	@Test
	@Order(2)
	public void saveReservas() throws Exception {
		
		locacoes.add(Locacao.createInstance(clientes.get(0), locais.get(0), gerarData("01/02/2020"), gerarData("07/02/2020")));
		locacoes.add(Locacao.createInstance(clientes.get(0), locais.get(1), gerarData("22/02/2020"), gerarData("02/03/2020")));
		locacoes.add(Locacao.createInstance(clientes.get(0), locais.get(2), gerarData("02/03/2020"), gerarData("11/03/2020")));
		locacoes.add(Locacao.createInstance(clientes.get(1), locais.get(0), gerarData("06/03/2020"), gerarData("08/03/2020")));
		locacoes.add(Locacao.createInstance(clientes.get(1), locais.get(1), gerarData("04/03/2020"), gerarData("01/04/2020")));
		locacoes.add(Locacao.createInstance(clientes.get(1), locais.get(2), gerarData("03/04/2020"), gerarData("30/04/2020")));
		locacoes.add(Locacao.createInstance(clientes.get(1), locais.get(3), gerarData("15/04/2020"), gerarData("02/05/2020")));
		locacoes.forEach( l -> locacaoRepository.save(l));
		
		assertEquals(7, locacaoRepository.count());
	}
	
	@Test
	@Order(3)
	public void saveReservaDuplicada() throws Exception {

		//Violação de chave primaria ( cliente, local)
		assertThrows(DataIntegrityViolationException.class, () -> {
			locacaoRepository.save(Locacao.createInstance(clientes.get(0), locais.get(1), gerarDataRandom(), gerarDataRandom()));			
		});
	}

	@Test
	@Order(4)
	public void checkAvailability1() throws Exception {

		
		List<Locacao> loc = locacaoRepository.checkAvailability(
				locais.get(2).getId(), //ocupado de 2 a 11 do 3
				gerarData("01/03/2020"), 
				gerarData("15/03/2020"));
		
		assertEquals(1, loc.size());
	}

	@Test
	@Order(5)
	public void checkAvailability2() throws Exception {

		List<Locacao> loc = locacaoRepository.checkAvailability(locais.get(1).getId(), gerarData("01/01/2020"), gerarData("04/02/2020"));
		
		assertEquals(0, loc.size());
	}

	@Test
	@Order(6)
	public void checkAvailability3() throws Exception {

		List<Locacao> loc = locacaoRepository.checkAvailability(locais.get(2).getId(), gerarData("01/01/2020"), gerarData("04/03/2020"));
		
		assertNotEquals(0, loc.size());
		
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
		assertEquals(false, locacaoRepository.existsById(locacoes.get(3).getId()));
	}

	@Test
	@Order(9)
	public void alterarDatasReserva() throws Exception {

		//Locacao locacao = locacaoRepository.findById(22L).get();
		Locacao locacao = locacoes.get(1);
		Locacao alterarDatasReserva = locacaoService.alterarDatasReserva(locacao, gerarData("01/02/2020"), gerarData("09/03/2020"));

		assertNotNull(alterarDatasReserva);
	}
	
	
	@Test
	@Order(10)
	public void findLocationBetween() throws Exception {
		List<Locacao> locbetween = locacaoRepository.findLocationsBetween(
				locais.get(2).getId(), 
				gerarData("11/03/2020"), 
				gerarData("01/05/2020"));
		locbetween.forEach( l -> System.out.println( l.getDataInicial() + " ate " + l.getDataFinal()) );
		assertNotEquals(0, locbetween.size());
	}
	
	
	@Test
	@Order(11)
	public void findByCpf() throws Exception {
		Cliente cliente = clienteRepository.findByCpf("00933707342");
		assertEquals("Oliver Bierhoff", cliente.getNome());
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
