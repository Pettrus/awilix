package br.com.awilix.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.awilix.models.Usuario;
import br.com.awilix.services.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioService service;

	@PostMapping("/login")
	public ResponseEntity<?> logar(@RequestParam String email, @RequestParam String senha) {
		String token = service.logar(email, senha);
		
		if(token == null)
			return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);

		return ResponseEntity.ok(token);
	}
	
	@PostMapping
	public void cadastro(@Valid @RequestBody Usuario usuario) {
		service.cadastrar(usuario);
	}
}