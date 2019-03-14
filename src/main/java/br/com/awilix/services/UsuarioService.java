package br.com.awilix.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.awilix.models.Usuario;
import br.com.awilix.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
    private BCryptPasswordEncoder bcryptEncoder;

	public String logar(String email, String senha) {
		Optional<Usuario> usuario = repository.findByEmail(email);
		
		if(usuario.isPresent() &&
				bcryptEncoder.matches(senha, usuario.get().getSenha())) {
			return TokenAuthenticationService.gerarJwt(usuario.get());
		}
		
		return null;
	}
	
	public void cadastrar(Usuario usuario) {
		repository.save(usuario);
	}
}