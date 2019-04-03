package br.com.awilix.controllers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.awilix.models.Horarios;
import br.com.awilix.services.FilmeEmCartazService;

@RestController
@RequestMapping("/filme-em-cartaz")
public class FilmeEmCartazController {

	@Autowired
	private FilmeEmCartazService service;
	
	@GetMapping("/{imdb}")
	public Set<Horarios> pesquisar(@PathVariable String imdb) {
		return service.pesquisarHorariosFilme(imdb);
	}
}