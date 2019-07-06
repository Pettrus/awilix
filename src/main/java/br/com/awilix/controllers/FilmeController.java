package br.com.awilix.controllers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.awilix.models.FilmeEmCartaz;
import br.com.awilix.services.FilmeService;

@RestController
@RequestMapping("/filmes")
public class FilmeController {

	@Autowired
	private FilmeService service;
	
	@GetMapping
	public Set<FilmeEmCartaz> filmesEmCartaz() {
		return service.listarFilmes();
	}
}
