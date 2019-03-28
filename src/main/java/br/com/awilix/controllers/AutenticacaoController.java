package br.com.awilix.controllers;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

		return ResponseEntity.ok(logado);
	}
	
	@GetMapping("/{token}")
	public boolean verificarConfirmacaoToken(@PathVariable String token) {
		return service.isTokenConfirmacaoValido(token);
	}
	
	@PostMapping(produces="application/json")
	public void cadastro(@Valid @RequestBody Usuario usuario) throws IOException {
		service.cadastrar(usuario);
	}
}