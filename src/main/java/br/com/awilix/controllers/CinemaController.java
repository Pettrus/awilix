package br.com.awilix.controllers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.awilix.dto.HorariosDTO;
import br.com.awilix.models.FilmeEmCartaz;
import br.com.awilix.services.CinemaService;

@RestController
@RequestMapping("/cinema")
public class CinemaController {

	@Autowired
	private CinemaService service;
	
	@GetMapping
	public Set<FilmeEmCartaz> filmesEmCartaz() {
		return service.listarFilmes();
	}
	
	@GetMapping("/{imdb}")
	public HorariosDTO horariosFilme(@PathVariable String imdb) {
		return service.horariosFilme(imdb);
	}
	
	@GetMapping("/tmdb-id/{imdb}")
	public int tmdbId(@PathVariable String imdb) {
		return service.retornaIdTmdb(imdb);
	}
}
