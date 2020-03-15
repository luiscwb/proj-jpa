package com.br.stl.controller;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.stl.model.entity.Caracteristica;
import com.br.stl.model.entity.Cliente;
import com.br.stl.model.entity.Consultora;
import com.br.stl.service.StylebookService;

@RestController
@RequestMapping("/stylebook")
public class StylebookController {

	@Autowired
	StylebookService service;
	

	@PostMapping("/consultora/save")
	public void salvarConultora(@RequestBody Consultora cons) {
		service.salvarConsultora(cons);
	}
	
	
	@PostMapping("/cliente/save")	
	public void salvarCliente( @RequestBody Cliente cli) {
		service.salvarCliente(cli);
	}

	@GetMapping("/caracteristica/findall")
	public List<Caracteristica> recuperarCaracteristicas() {
		return service.recuperarCaracteristicas();
	}


}
