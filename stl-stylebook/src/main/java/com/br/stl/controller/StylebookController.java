package com.br.stl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.stl.model.entity.Cliente;
import com.br.stl.service.StylebookService;

@RestController
@RequestMapping("/api")
public class StylebookController {

	@Autowired
	StylebookService service;
	
	
//	@GetMapping("/findall")
//	public List<Cliente> findAll() {
//		return service.recuperarClientes();
//	}
	
	@PostMapping("/save")
	public void salvarCliente( @RequestBody Cliente cli) {
		//System.out.println("Cliente: " + cli.getNomeCompleto());
		//System.out.println("Cliente: " + cli.getImagemBase64());
		service.salvarCliente(cli);
	}

//	@PostMapping("/saveimg")
//	public void salvarClienteImagem( @RequestBody ClienteImagem cliImg) {
//		//System.out.println("Cliente: " + cli.getNomeCompleto());
//		//System.out.println("Cliente: " + cli.getImagemBase64());
//		service.salvarImagem(cliImg);
//	}


}
