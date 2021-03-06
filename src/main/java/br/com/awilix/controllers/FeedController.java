package br.com.awilix.controllers;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.awilix.models.Feed;
import br.com.awilix.services.FeedService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/feed")
@RequiredArgsConstructor
public class FeedController {

	private final FeedService service;
	
	@GetMapping("/cancelar-inscricao/{email}")
	public ResponseEntity<?> cancelarInscricao(@PathVariable String email) {
		service.removerEmail(email);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> salvar(@Valid @RequestBody Feed feed) {
		service.salvar(feed);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
}