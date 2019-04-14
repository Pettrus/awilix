package br.com.awilix.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mashape.unirest.http.exceptions.UnirestException;

import br.com.awilix.services.FilmeService;
import info.movito.themoviedbapi.model.MovieDb;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/filmes")
@RequiredArgsConstructor
public class FilmeController {

	private final FilmeService service;
	
	@GetMapping("/pesquisa/{keyword}")
	public String pesquisa(@PathVariable String keyword) throws UnirestException {
		return service.pesquisar(keyword).getBody();
	}
	
	@GetMapping("/{pagina}")
	public String listarFilmes(@PathVariable Integer pagina) throws UnirestException {
		return service.consultarFilmes(pagina).getBody();
	}
	
	@GetMapping("/{pagina}/{genero}")
	public String listarFilmesPorGenero(@PathVariable Integer pagina, @PathVariable String genero) throws UnirestException {
		return service.consultarFilmesPorGenero(pagina, genero).getBody();
	}
	
	@GetMapping("/imdb/{idImdb}")
	public MovieDb detalhar(@PathVariable String idImdb) {
		return service.detalhar(idImdb);
	}
}