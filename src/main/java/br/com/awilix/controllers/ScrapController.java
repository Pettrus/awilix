package br.com.awilix.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.awilix.dto.ScrapDTO;
import br.com.awilix.services.ScrapService;

@RestController
@RequestMapping("/scrap")
public class ScrapController {
	
	@Autowired
	private ScrapService service;

	@PostMapping
	public boolean verificarNovoESalvar(@RequestBody ScrapDTO scrap) {
		service.verificarSalvarFilme(scrap.getFilmes(), scrap.getCinemas(), scrap.getLinguagem(), scrap.getCidade());
		
		return true;
	}
}
