package br.com.awilix.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.awilix.enums.Linguagem;
import br.com.awilix.models.Filme;
import br.com.awilix.services.FilmeService;

@RestController
@RequestMapping("/filmes")
public class FilmeController {

	@Autowired
	private FilmeService service;
	
	@GetMapping("{linguagem}")
	public List<Filme> filmesEmCartaz(@PathVariable Linguagem linguagem) {
		return service.listarFilmes(linguagem);
	}
}
