package br.com.awilix.services;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.sendgrid.SendGrid;

import br.com.awilix.config.SetupTest;
import br.com.awilix.models.Usuario;

@RunWith(MockitoJUnitRunner.class)
public class EmailServiceTest extends SetupTest {
	
	@Mock
	private SendGrid sendGrid;
	
	@InjectMocks
	private EmailService service;

	@Test
	public void cadastroUsuarioTest() throws IOException {
		service.cadastroUsuario(mock(Usuario.class));
		verify(sendGrid).api(Mockito.any());
	}
}