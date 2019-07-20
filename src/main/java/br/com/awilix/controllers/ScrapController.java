package br.com.awilix.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.awilix.dto.ScrapDTO;
import br.com.awilix.models.Filme;
import br.com.awilix.services.EmailService;
import br.com.awilix.services.ScrapService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/scrap")
@RequiredArgsConstructor
public class ScrapController {
	
	private final ScrapService service;
	
	private final EmailService emailService;

	@PostMapping
	public ResponseEntity<?> verificarNovoESalvar(@RequestBody ScrapDTO scrap) {
		List<Filme> filmesNovos = service.encontrarFilmesNovos(scrap.getFilmes(), scrap.getLinguagem());
		
		service.atualizarFilmes(scrap);
		emailService.notificarFilmesNovos(filmesNovos, scrap.getCidade());
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
}