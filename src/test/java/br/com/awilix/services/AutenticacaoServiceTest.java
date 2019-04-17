package br.com.awilix.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.awilix.config.SetupTest;
import br.com.awilix.exception.GeneralException;
import br.com.awilix.models.Usuario;
import br.com.awilix.repository.UsuarioRepository;

@RunWith(MockitoJUnitRunner.class)
public class AutenticacaoServiceTest extends SetupTest {

	@Mock
	private UsuarioRepository usuarioRepository;
	
	@Mock
	private BCryptPasswordEncoder bcryptEnconder;
	
	@Mock
	private EmailService emailService;
	
	@InjectMocks
	private AutenticacaoService service;
	
	private Usuario userNovo = new Usuario(new Long(1), "teste@teste.com", 
			"1234567890", "teste", null, null, new Date(), null, false, null);
	
	@Test
	void logarTest() {
		assertThrows(GeneralException.class, () -> service.logar(mock(Usuario.class)));
		Optional<Usuario> op = Optional.of(userNovo);
		
		when(usuarioRepository.findByEmail(userNovo.getEmail())).thenReturn(op);
		when(bcryptEnconder.matches(userNovo.getSenha(), userNovo.getSenha())).thenReturn(true);
		
		assertThrows(GeneralException.class, () -> service.logar(userNovo));
		
		userNovo.setEmailConfirmado(true);
		
		service.logar(userNovo);
		verify(usuarioRepository).save(Mockito.any());
	}
	
	@Test
	void isTokenConfirmacaoValidoTest() {
		when(usuarioRepository.findByTokenConfirmacao(Mockito.anyString())).thenReturn(Optional.of(mock(Usuario.class)));
		assertThat(service.isTokenConfirmacaoValido(Mockito.anyString())).isTrue();
		
		when(usuarioRepository.findByTokenConfirmacao(Mockito.anyString())).thenReturn(Optional.empty());
		assertThat(service.isTokenConfirmacaoValido(Mockito.anyString())).isFalse();
	}
	
	@Test
	void cadastrarTest() throws IOException {
		when(usuarioRepository.findByEmail(userNovo.getEmail())).thenReturn(Optional.of(mock(Usuario.class)));
		assertThrows(GeneralException.class, () -> service.cadastrar(userNovo));
		
		when(usuarioRepository.findByEmail(userNovo.getEmail())).thenReturn(Optional.empty());
		service.cadastrar(userNovo);
		verify(usuarioRepository).save(Mockito.any());
	}
}
