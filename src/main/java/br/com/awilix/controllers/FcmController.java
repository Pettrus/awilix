package br.com.awilix.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.awilix.services.FcmService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/fcm")
@RequiredArgsConstructor
public class FcmController {

	private final FcmService service;
	
	@PostMapping
	public ResponseEntity<?> salvarToken(@RequestBody String token) {
		service.salvarToken(token);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
