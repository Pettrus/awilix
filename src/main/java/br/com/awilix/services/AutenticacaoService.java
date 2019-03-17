package br.com.awilix.services;

import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.awilix.exception.GeneralException;
import br.com.awilix.models.Usuario;
import br.com.awilix.repository.UsuarioRepository;

@Service
public class AutenticacaoService {
	
	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
    private BCryptPasswordEncoder bcryptEncoder;

	@Transactional
	public Usuario logar(Usuario auth) {
		Optional<Usuario> usuario = repository.findByEmail(auth.getEmail());
		
		if(usuario.isPresent() &&
				bcryptEncoder.matches(auth.getSenha(), usuario.get().getSenha())) {
			usuario.get().setToken(TokenAuthenticationService.gerarJwt(usuario.get()));
			
			repository.save(usuario.get());
			
			return usuario.get();
		}
		
		return null;
	}
	
	public void cadastrar(Usuario usuario) {
		Optional<Usuario> usuarioExistente = repository.findByEmail(usuario.getEmail());
		
		if(usuarioExistente.isPresent()) throw new GeneralException("Já existe um usuário cadastrado com esse email");
		
		usuario.setDataCadastro(new Date());
		usuario.setSenha(bcryptEncoder.encode(usuario.getSenha()));
		
		repository.save(usuario);
	}
}