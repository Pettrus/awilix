package br.com.awilix.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.awilix.models.Usuario;
import br.com.awilix.services.AutenticacaoService;

@RestController
@RequestMapping("/autenticacao")
public class AutenticacaoController {
	
	@Autowired
	private AutenticacaoService service;

	@PostMapping("/login")
	public ResponseEntity<?> logar(@RequestBody Usuario usuario) {
		Usuario logado = service.logar(usuario);
		
		if(logado == null)
			return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);

		return ResponseEntity.ok(logado);
	}
	
	@PostMapping(produces="application/json")
	public void cadastro(@Valid @RequestBody Usuario usuario) {
		service.cadastrar(usuario);
	}
}