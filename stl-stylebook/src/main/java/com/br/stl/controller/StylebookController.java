package com.br.stl.controller;

import java.awt.Image;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	
	@GetMapping("/findall")
	public List<Cliente> findAll() {
		return service.recuperarClientes();
	}
	
	@PostMapping("/save")
	public void salvarCliente( @RequestBody Cliente cli) {
		//System.out.println("Cliente: " + cli.getNomeCompleto());
		//System.out.println("Cliente: " + cli.getImagemBase64());
		service.salvarCliente(cli);
	}


}
