package br.com.awilix.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.awilix.services.FilmeService;
import info.movito.themoviedbapi.model.Genre;
import info.movito.themoviedbapi.model.MovieDb;

@RestController
@RequestMapping("/filmes")
public class FilmeController {
	
	@Autowired
	private FilmeService service;
	
	@GetMapping("/nos-cinemas/{pagina}")
	public List<MovieDb> nosCinemas(@PathVariable Integer pagina) {
		return service.nosCinemas(pagina);
	}
	
	@GetMapping("/{filmeId}")
	public MovieDb detalhes(@PathVariable Integer filmeId) {
		return service.detalhes(filmeId);
	}
	
	@GetMapping("/generos")
	public List<Genre> generos() {
		return service.consultarGeneros();
	}
	
	@GetMapping("/genero/{id}/{pagina}")
	public List<MovieDb> listarPorGenero(@PathVariable Integer id, @PathVariable Integer pagina) {
		return service.pesquisarPorGenero(id, pagina);
	}
}
