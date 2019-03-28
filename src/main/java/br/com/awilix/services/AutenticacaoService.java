package br.com.awilix.services;

import java.io.IOException;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

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
	
	@Autowired
	private EmailService email;
	
	@Transactional
	public Usuario logar(Usuario auth) {
		Optional<Usuario> usuario = repository.findByEmail(auth.getEmail());
		
		if(usuario.isPresent() &&
				bcryptEncoder.matches(auth.getSenha(), usuario.get().getSenha())) {
			
			if(!usuario.get().getEmailConfirmado())
				throw new GeneralException("Email ainda não confirmado, acesse seu email para confirmar");
				
			usuario.get().setToken(TokenAuthenticationService.gerarJwt(usuario.get()));
			
			repository.save(usuario.get());
			
			return usuario.get();
		}
		
		throw new GeneralException("Email ou senha incorretos.");
	}
	
	public boolean isTokenConfirmacaoValido(String token) {
		Optional<Usuario> usuario = repository.findByTokenConfirmacao(token);
		
		if(usuario.isPresent()) {
			usuario.get().setDataConfirmacao(new Date());
			usuario.get().setEmailConfirmado(true);
			
			repository.save(usuario.get());
			
			return true;
		}
		
		return false;
	}
	
	@Transactional
	public void cadastrar(Usuario usuario) throws IOException {
		Optional<Usuario> usuarioExistente = repository.findByEmail(usuario.getEmail());
		
		if(usuarioExistente.isPresent()) throw new GeneralException("Já existe um usuário cadastrado com esse email");
		
		usuario.setDataCadastro(new Date());
		usuario.setSenha(bcryptEncoder.encode(usuario.getSenha()));
		usuario.setTokenConfirmacao(UUID.randomUUID().toString());
		
		repository.save(usuario);
		
		//email.cadastroUsuario(usuario);
	}
}